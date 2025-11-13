package com.mjc813.network.firstteaching;

import java.util.Scanner;

public class ServerScannerThread extends Thread {
    private Scanner scanner = null;
    private ISendSockets sendSockets = null;
    private IClose close = null;

    public ServerScannerThread(Scanner scanner, ISendSockets sendSockets, IClose close) {
        this.scanner = scanner;
        this.sendSockets = sendSockets;
        this.close = close;
    }
    @Override
    public void run() {
        while(true) {
            try {
                String str = scanner.nextLine();
                if("quit".equals(str)) {
                    this.sendSockets.sendAll("exit!@#$app");
                    this.close.close();
                    break;
                }
                this.sendSockets.sendAll(str);
            } catch (Throwable e) {
                System.out.println("client disconnted");
            }
        }
    }
}