package com.mjc813.network.firstteaching;

import java.net.Socket;
import java.util.LinkedList;

public interface ISendSockets {
    public void sendAll(String msg);
    public void deleteSocket(ClientSocket sck);
}
