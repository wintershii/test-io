package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QQNio {

    static byte[] bytes = new byte[1024];
    static List<SocketChannel> list = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);

            while (true) {

                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel == null) {
                    Thread.sleep(1000);
                    System.out.println("no conn");
                } else {
                    System.out.println("---conn---");
                    socketChannel.configureBlocking(false);
                    list.add(socketChannel);
                }

                for (SocketChannel client : list) {
                    int k = client.read(byteBuffer);
                    if (k > 0) {
                        byteBuffer.flip();
                        System.out.println(Charset.forName("utf-8").decode(byteBuffer));
                    }
                }

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
