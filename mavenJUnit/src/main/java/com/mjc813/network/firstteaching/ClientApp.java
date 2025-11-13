package com.mjc813.network.firstteaching;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClientApp {
    private Socket sck = null;
    //private OutputStream os = null;
    private BufferedWriter bw = null;
    public ClientApp() {
        this.sck = new Socket();    // 클라이언트소켓 생성
    }
    public void init(String ipAddr, int port) throws IOException {
        SocketAddress sa = new InetSocketAddress(ipAddr, port);
        this.sck.connect(sa);
        this.bw = new BufferedWriter(
                new OutputStreamWriter(this.sck.getOutputStream())
        );
        BufferedReader br = new BufferedReader(
                new InputStreamReader(this.sck.getInputStream())
        );
        Thread readSocketThread = new Thread(new ClientReadSocketThread(br));
        readSocketThread.start();
        // Ip 주소와 포트로 접속한다.
    }
    public void send(String str) throws IOException {
        // 통신소켓의 OutputStream (출력용도스트림)을 쓰기버퍼에 연결한다.
        this.bw.write(str);  // 문자열을 통신소켓으로 전송한다.
        this.bw.newLine();  // 이 문장이 없으면 소켓전송을 제대로 못하는 현상이 발생함.
        this.bw.flush();     // 전송 flush 한다.

//        this.os = this.sck.getOutputStream();
//        this.os.write(str.getBytes());   // 통신소켓에 데이터를 전송한다.
//        this.os.flush();
    }
    public void close() throws IOException {
        this.bw.close();     // 쓰기버퍼를 닫는다.
        this.sck.close();   // 소켓을 닫는다.
    }

    public static void main(String[] args) {
        System.out.println("Client start");
        Scanner scanner = new Scanner(System.in);
        ClientApp ca = new ClientApp();
        try {
            ca.init("127.0.0.1", 19999);
            ca.send("한글되나요 ?");
            while(true) {
                String str = scanner.nextLine();
                if("quit".equals(str)) {
                    ca.send("exit!@#$app");
                    break;
                }
                ca.send(str);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        } finally {
            try {
                ca.close();
            } catch (Throwable e) {
            }
        }
    }
}
