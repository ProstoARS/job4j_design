package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            int count = 0;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (count == 0) {
                        out.write("Hello, dear friend.\r\n".getBytes());
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("?msg=")) {
                            if (str.contains("Hello")) {
                                out.write("Hello=).\r\n".getBytes());
                            } else if (str.contains("Exit")) {
                                out.write("server close".getBytes());
                                server.close();
                            } else {
                                out.write("What=(.\r\n".getBytes());
                            }
                        }
                        out.flush();
                    }
                    count++;
                } catch (Exception e) {
                    LOG.error("IO error :", e);
                }
            }
            System.out.println("server close");
        } catch (Exception e) {
            LOG.error("Socket error :", e);
        }
    }
}