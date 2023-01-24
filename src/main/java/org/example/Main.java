package org.example;

import org.example.config.Configuration;
import org.example.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using Webroot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
//           serverSocket.accept() prompts server socket to accept any connections that arise
//            Socket is direct means of communicating with entity that connected to the server socket
//            .accept stops the code to wait for the connection
            Socket socket = serverSocket.accept();

//            InputStream to read request from the browser
            InputStream inputStream = socket.getInputStream();
//            OutputStream write to the socket
            OutputStream outputStream = socket.getOutputStream();

//            Reading
//              Currently disregarding reading from the browser

//            Writing
//            Defining page to send to the browser
            String html = "<html><head><title>Simple title</title></head><body><h1>Test header</h1></body></html>";

            final String CRLF = "\r\n";  //13, 10

            String response =
                    "HTTp/1.1 200 OK" + CRLF + //Status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF +//HEADER
                            CRLF +    //Signify that headers finished
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}