package com.ruiyi.carassistant.web.listener;

import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ruiyi.carassistant.socket.ObdProtocol;
import com.ruiyi.carassistant.socket.ThreadPoolTaskExecutor;

@WebListener
public class ServerSocketListener implements ServletContextListener {

    private SocketThread socketThread;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (socketThread != null && !socketThread.isInterrupted()) {
            socketThread.closeServerSocket();
            socketThread.interrupt();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("Server contextInitialized over");
        if (socketThread == null) {
            socketThread = new SocketThread(servletContext);
            socketThread.start();
        }
    }

    private class SocketThread extends Thread {
        private ServletContext servletContext;
        private ServerSocket serverSocket;

        public SocketThread(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        public void run() {
            try {
                String port = servletContext.getInitParameter("socketport");
                if (port == null && "".equals(port))
                    return;
                serverSocket = new ServerSocket(Integer.parseInt(port));
                System.out.println("SocketServer have been started.[" + serverSocket + "]");

                while (true) {
                    Socket clntSock = serverSocket.accept();
                    try {
                        clntSock.setSoTimeout(60000); // 设置一分钟超时
                        System.out.println(clntSock.getSoTimeout());
                        ThreadPoolTaskExecutor.invoke(new ObdProtocol(clntSock));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void closeServerSocket() {
            try {
                serverSocket.close();
                System.out.println("SocketServer have been stoped.");
            } catch (Exception ex) {
                System.out.println("SocketThread err:" + ex.getMessage());
            }
        }
    }
}
