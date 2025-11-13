package com.mjc813.network.firstteaching;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket extends Thread {
    private Socket sck = null;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private ISendSockets sendSockets = null;

    public ClientSocket(Socket sck, ISendSockets sendSockets) throws IOException {
        this.sck = sck;
        this.sendSockets = sendSockets;
        this.br = new BufferedReader(
                new InputStreamReader(sck.getInputStream())
        );  // 소켓의 입력을 InputStream 에서 받아서 읽기버퍼로 전달한다
        this.bw = new BufferedWriter(
                new OutputStreamWriter(sck.getOutputStream())
        );
    }

    @Override
    public void run() {
        this.recieveSocket();
    }

    public void recieveSocket() {
        try {
            while(true) {
                String str = this.br.readLine(); // 읽기버퍼에서 읽는다. 블로킹 상태
                if( "exit!@#$app".equals(str) ) {
                    this.br.close();
                    this.sendSockets.deleteSocket(this);
                    break;
                }
                System.out.printf("from Client[%s] : %s\n", this.sck.getInetAddress(), str);
                if ( this.sendSockets != null ) {
                    this.sendSockets.sendAll(str);
                }
            }
        } catch (Exception e) {
            this.sendSockets.deleteSocket(this);
            System.out.println("socket disconnected");
        } finally {
            this.close();
        }
    }

    public void send(String msg) throws IOException {
        this.bw.write(msg);
        this.bw.newLine();
        this.bw.flush();
    }

    public void close() {
        try {
            this.bw.close();
        } catch (Throwable e) {
        }
        try {
            this.br.close();
        } catch (Throwable e) {
        }
        try {
            this.sck.close();
        } catch (Throwable e) {
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.sck.toString(), this.getName());
    }
}
