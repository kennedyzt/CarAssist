package com.ruiyi.carassistant.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.ruiyi.carassistant.entity.SocketRequest;
import com.ruiyi.carassistant.utils.LoggerUtil;

public class GetGPSThread implements Runnable {
    private Socket socket;
    private String userID;

    public GetGPSThread() {
        super();
    }

    public GetGPSThread(Socket socket, String userID) {
        super();
        this.socket = socket;
        this.userID = userID;
    }

    @Override
    public void run() {
        // 请求obd数据
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userID);
            SocketRequest socketRequest = new SocketRequest("info_local", params);
            LoggerUtil.debug("向OBD发送获取位置请求：" + socketRequest.getJsonObject());
            out.println(socketRequest.getJsonObject());
            // 获取obd返回值
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
