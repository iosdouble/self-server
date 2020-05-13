package com.nh.server;



import com.nh.server.dispatcher.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Classname Server
 * @Description TODO
 * @Date 2020/5/13 10:21 AM
 * @Created by nihui
 */
public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;

    public static void main(String[] args) {
        Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml");

        Server server = new Server();
        server.start();
        server.receive();
    }

    public void receive() {
        while (isRunning){
            try {
                Socket socket = serverSocket.accept();
                Dispatcher dis = new Dispatcher(socket);
                new Thread(dis).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("接收失败");
            }
        }

    }

    public void start() {
        try{
            serverSocket = new ServerSocket(8765);
            isRunning = true;
            System.out.println("服务器以启动");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
            this.stop();
        }
    }

    public void stop() {
        isRunning = false;
        try{
            this.serverSocket.close();
            System.out.println("服务器已经停止");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
