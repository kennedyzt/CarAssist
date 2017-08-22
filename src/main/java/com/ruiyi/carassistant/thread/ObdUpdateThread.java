package com.ruiyi.carassistant.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.ruiyi.carassistant.entity.SocketRequest;
import com.ruiyi.carassistant.utils.LoggerUtil;

public class ObdUpdateThread implements Runnable {
    private Socket socket;

    public ObdUpdateThread() {
        super();
    }

    public ObdUpdateThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        // 请求obd数据
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            SocketRequest socketRequest = new SocketRequest("obd_update");
            LoggerUtil.debug("向OBD发送固件升级请求：" + socketRequest.getJsonObject());
            out.println(socketRequest.getJsonObject());
            // 获取obd返回值
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
