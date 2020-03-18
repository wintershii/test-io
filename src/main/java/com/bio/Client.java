package com.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            //socket.connect(new InetSocketAddress(8080));
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            socket.getOutputStream().write(s.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
