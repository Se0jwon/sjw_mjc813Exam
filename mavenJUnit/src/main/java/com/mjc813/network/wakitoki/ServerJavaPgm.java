package com.mjc813.network.wakitoki;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJavaPgm {
    private ServerSocket serverSocket;
    private BufferedWriter bw;
    private BufferedReader br;

    public Socket accept(int port) throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(port));
        Socket socket = this.serverSocket.accept();
        this.br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        this.bw = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        return socket;
    }

    public void read(Socket socket) throws IOException {
        String str = this.br.readLine();
        System.out.printf("From Client : %s\n", str);
    }

    public void close(Socket sck) throws IOException {
        this.bw.close();
        this.br.close();
        sck.close();
        this.serverSocket.close();
    }

    public void write(Socket socket, String str) throws IOException {
        this.bw.write(str);
        this.bw.newLine();
        this.bw.flush();
    }

    public static void main(String[] args) {
        System.out.println("ServerJavaPgm start");

        ServerJavaPgm sjp = new ServerJavaPgm();
        try {
            Socket sck = sjp.accept(59999);
            sjp.read(sck);
            sjp.write(sck, "서버도 보내본다.");
            sjp.read(sck);
            sjp.write(sck, "서버에서 2차로 보내본다.");
            sjp.read(sck);
            sjp.write(sck, "서버에서 3차로 보내본다.");
            sjp.close(sck);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
