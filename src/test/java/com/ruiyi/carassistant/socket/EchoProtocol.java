package com.ruiyi.carassistant.socket;

import com.ruiyi.carassistant.utils.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoProtocol implements Runnable{

    private static final int BUFSIZE = 32; // Size (in bytes) of I/O buffer  
    private Socket clientSocket; // Socket connect to client  
  
    public EchoProtocol(Socket clientSocket) {  
        this.clientSocket = clientSocket;  
    }  
  
    public static void handleEchoClient(Socket clientSocket) {  
        try {  
            // Get the input and output I/O streams from socket  
            InputStream in = clientSocket.getInputStream();  
            OutputStream out = clientSocket.getOutputStream();  
  
            int recvMsgSize; // Size of received message  
            int totalBytesEchoed = 0; // Bytes received from client  
            byte[] echoBuffer = new byte[BUFSIZE]; // Receive Buffer  
            // Receive until client closes connection, indicated by -1  
            while ((recvMsgSize = in.read(echoBuffer)) != -1) {  
                out.write(echoBuffer, 0, recvMsgSize);  
                totalBytesEchoed += recvMsgSize;  
            }  
  
            LoggerUtil.debug("Client " + clientSocket.getRemoteSocketAddress() + ", echoed " + totalBytesEchoed + " bytes.");
              
        } catch (IOException ex) {  
            LoggerUtil.warn("Exception in echo protocol", ex);
        } finally {  
            try {  
                clientSocket.close();  
            } catch (IOException e) {  
            }  
        }  
    }  
  
    public void run() {  
        handleEchoClient(this.clientSocket);  
    }

}
