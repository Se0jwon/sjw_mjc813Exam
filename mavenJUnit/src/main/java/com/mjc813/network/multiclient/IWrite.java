package com.mjc813.network.multiclient;

public interface IWrite {
    public void sendAllTargets(String str);
    public void deleteTarget(ClientSocketBuffer deletObject);
}
