package com.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class QQServer {

    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try {
            //listener
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            while (true) {
                //在不使用多线程的情况下，BIO无法处理并发
                System.out.println("wait connect");
                //阻塞，放弃CPU资源
                Socket socket = serverSocket.accept();
                System.out.println("conn success");

                //阻塞, 返回值表示读了多少字节
                int read = socket.getInputStream().read(bytes);

                String content = new String(bytes);
                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
