package com.ruiyi.carassistant.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.alibaba.fastjson.JSONObject;
import com.ruiyi.carassistant.entity.SocketResult;
import com.ruiyi.carassistant.utils.LoggerUtil;
import com.ruiyi.carassistant.utils.ParseJson;

public class ObdProtocol implements Runnable {
    private Socket clientSocket; // Socket connect to client

    public ObdProtocol(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void handleObdClient(Socket clientSocket) {
        try {
            LoggerUtil.debug("Client socket:" + clientSocket.getInetAddress() + " connected Client ThreadId-" + Thread.currentThread().getId() + " poolSize-" + ThreadPoolTaskExecutor.getPoolSize());

            // Get the input and output I/O streams from socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                String line = null;
                StringBuilder param = new StringBuilder();
                while (!"###".equals(line = in.readLine())) {
                    if (null == line) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    param.append(line);
                }
                String paramStr = param.toString();
                LoggerUtil.debug("Server Received:[" + paramStr + "]");
                SocketResult socketResult = ParseJson.ParseTerminalData(paramStr, clientSocket);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", socketResult.getResult());
                jsonObject.put("method", socketResult.getMethod());
                jsonObject.put("success", socketResult.getSuccess());
                out.println(jsonObject);
                out.flush();
                LoggerUtil.debug("Server Result:[" + socketResult.toString() + "]");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LoggerUtil.warn("Exception in Obd protocol" + ex.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                LoggerUtil.warn("Exception in Obd protocol", e);
            }
        }
    }

    public void run() {
        handleObdClient(this.clientSocket);
    }

}
