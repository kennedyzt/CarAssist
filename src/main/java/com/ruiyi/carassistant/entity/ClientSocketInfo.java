package com.ruiyi.carassistant.entity;

import java.net.Socket;

public class ClientSocketInfo {
    private String trailStr; // 轨迹字符串
    private Socket socket; // 当前socket

    public ClientSocketInfo() {
        super();
    }

    public ClientSocketInfo(Socket socket) {
        super();
        this.socket = socket;
    }

    public String getTrailStr() {
        return trailStr;
    }

    public void setTrailStr(String trailStr) {
        this.trailStr = trailStr;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientSocketInfo socket(Socket clientSocket) {
        ClientSocketInfo clientSocketInfo = new ClientSocketInfo(clientSocket);
        clientSocketInfo.setTrailStr(trailStr);
        return clientSocketInfo;
    }

}
