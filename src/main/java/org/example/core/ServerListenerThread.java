package org.example.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
//        ServerListenerThread is accepting the connection by accepting socket, and then it passes socket to a worker thread
//        Worker thread then does all the other work, while ServerListenerThread continues to accept more connections and create more worker threads
        try {
//            Main connection loop
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
//           serverSocket.accept() prompts server socket to accept any connections that arise
//            Socket is direct means of communicating with entity that connected to the server socket
//            .accept stops the code to wait for the connection
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted: " + socket.getInetAddress());
                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();

            }
        } catch (IOException e) {
            LOGGER.error("Problem with setting socket", e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }
        }

    }
}
