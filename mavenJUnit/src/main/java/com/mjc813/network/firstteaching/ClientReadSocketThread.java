package com.mjc813.network.firstteaching;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReadSocketThread implements Runnable {
    private BufferedReader br = null;
    public ClientReadSocketThread(BufferedReader br) {
        this.br = br;
    }
    @Override
    public void run() {
        try {
            while(true) {
                String str = this.br.readLine();
                if( "exit!@#$app".equals(str) ) {
                    this.br.close();
                    break;
                }
                System.out.printf("from Server : %s\n", str);
            }
        } catch (IOException e) {
            System.out.println("server disconnected");
        }
        System.exit(0);
    }
}
