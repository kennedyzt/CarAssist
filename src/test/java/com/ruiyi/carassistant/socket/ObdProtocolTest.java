package com.ruiyi.carassistant.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lenovo on 2017/1/9.
 */
public class ObdProtocolTest {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2404);
            System.out.println("SocketServer have been started.[" + serverSocket + "]");

            while (true) {
                Socket clntSock = serverSocket.accept();
                try {
                    // TODO
                    clntSock.setSoTimeout(20000);
                    // ThreadPoolTaskExecutor.invoke(new ObdProtocol(clntSock), TimeUnit.SECONDS, 3);
                    ThreadPoolTaskExecutor.invoke(new ObdProtocol(clntSock));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
