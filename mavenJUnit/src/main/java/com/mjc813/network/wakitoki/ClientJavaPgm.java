package com.mjc813.network.wakitoki;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientJavaPgm {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    public void connect( String ip, int port ) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port));
        this.bw = new BufferedWriter(
                new OutputStreamWriter(this.socket.getOutputStream())
        );
        this.br = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
    }

    public void write(String str) throws IOException {
        this.bw.write(str);
        this.bw.newLine();
        this.bw.flush();
    }

    public void read() throws IOException {
        String s = this.br.readLine();
        System.out.printf("From Server : %s\n", s);
    }

    public void close() throws IOException {
        this.br.close();
        this.bw.close();
        this.socket.close();
    }

    public static void main(String[] args) {
        System.out.println("ClientJavaPgm start");
        ClientJavaPgm cjp = new ClientJavaPgm();
        try {
            cjp.connect("127.0.0.1"/*옆사람의 ip주소*/, 59999);
            cjp.write(String.format("Client[%s] %s"
                    , InetAddress.getLocalHost()
                    , "잘 접속했다."));
            cjp.read();
            cjp.write(String.format("첫번째 잘 받았다."));
            cjp.read();
            cjp.write(String.format("두번째 잘 받았다."));
            cjp.read();
            cjp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
