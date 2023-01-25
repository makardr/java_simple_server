package org.example.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);


    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
//            InputStream to read request from the browser
            inputStream = socket.getInputStream();
//            OutputStream write to the socket
            outputStream = socket.getOutputStream();

//            int _byte;
//            while ((_byte = inputStream.read()) >= 0) {
//                System.out.print((char) _byte);
//            }

            String html = "<html><head><title>Simple title</title></head><body><h1>Test header</h1></body></html>";

            final String CRLF = "\r\n";  //13, 10

            String response =
                    "HTTp/1.1 200 OK" + CRLF + //Status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF +//HEADER
                            CRLF +    //Signify that headers finished
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());


            LOGGER.info("Connection processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
