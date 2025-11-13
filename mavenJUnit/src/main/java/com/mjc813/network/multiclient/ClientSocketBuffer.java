package com.mjc813.network.multiclient;

import com.mjc813.network.readthread.ServerJavaPgm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

@Getter
@Setter
@AllArgsConstructor
public class ClientSocketBuffer extends Thread {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private IWrite iWrite;

    @Override
    public void run() {
        try {
            while(true) {
                String str = this.br.readLine();
                if(ServerJavaPgm.exitCommand.equals(str)) {
                    this.close();
                    System.out.printf("terminate chatting\n");
                } else {
                    System.out.printf("From [%s] : %s\n", this.socket.getRemoteSocketAddress(), str);
                    this.iWrite.sendAllTargets(String.format("[%s] %s", this.socket.getRemoteSocketAddress(), str));
                }
            }
        } catch (IOException e) {
            System.out.println("socket 종료");
        }
    }

    public void close() {
        try {
            this.iWrite.deleteTarget(this);
            this.bw.close();
            this.br.close();
            this.socket.close();
        } catch (IOException e) {
        }
    }

    public void write(String str) throws IOException {
        this.bw.write(str);
        this.bw.newLine();
        this.bw.flush();
    }
}
