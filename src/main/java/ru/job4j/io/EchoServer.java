package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("1.1 200 OK \r\n\r\n".getBytes());
                    out.write("Hello, dear friend\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.matches("^GET /\\?msg=Exit .+$")) {
                            server.close();
                        } else if (str.matches("^GET /\\?msg=Hello .+$")) {
                            out.write("Hello\r\n".getBytes());
                            break;
                        } else if (str.matches("^GET /\\?msg=.* .+$")) {
                            out.write("What\r\n".getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in log", e);
        }
    }
}
