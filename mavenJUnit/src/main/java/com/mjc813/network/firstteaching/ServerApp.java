package com.mjc813.network.firstteaching;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerApp implements ISendSockets, IClose {
    private ServerSocket ss = null;
    //private InputStream is = null;
    //private BufferedReader br = null;
    private ServerScannerThread sst = null;
    private LinkedList<ClientSocket> clientlist = null;

    public ServerApp(int port) throws IOException {
        // this.ss = new ServerSocket(port);   // 포트번호로 서버소켓 생성
        this.ss = new ServerSocket();
    }

    public void init() throws IOException {
        //Socket sck = null;
        try {
            this.ss.bind(new InetSocketAddress(19999)); // 바인드 명령으로 접속받아들일 IP주소대역과 포트번호로 클라이언트 접속을 대기할 수 있다.
            this.clientlist = new LinkedList<>();
            Scanner scanner = new Scanner(System.in);
            this.sst = new ServerScannerThread(scanner, this, this);
            this.sst.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private void acceptLoop() throws IOException {
        while(true) {
            Socket sck = this.ss.accept();
            ClientSocket cs = new ClientSocket(sck, this);
            this.clientlist.add(cs);
            System.out.println(sck.toString()); // 접속되면 이 문장 실행
            cs.start();
        }
    }

    @Override
    public void close() {
        try {
            this.ss.close();
        } catch (Throwable e) {
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server start");
        ServerApp sa = new ServerApp(19999);
        sa.init();
        sa.acceptLoop();
    }

    @Override
    public void sendAll(String msg) {
        for( ClientSocket cs : clientlist ) {
            try {
                cs.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteSocket(ClientSocket sck) {
        for( ClientSocket cs : this.clientlist ) {
            if (cs == sck) {
                System.out.printf("ClientSocket[%s] 삭제\n", cs.toString()); // 접속되면 이 문장 실행
                this.clientlist.remove(sck);
            }
        }
    }
}
