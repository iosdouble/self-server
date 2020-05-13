package com.nh.server.dispatcher;

import com.nh.server.request.Request;
import com.nh.server.response.Response;
import com.nh.server.servlet.Servlet;
import com.nh.server.web.WebApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Classname Dispatcher
 * @Description TODO
 * @Date 2020/5/13 10:29 AM
 * @Created by nihui
 */
public class Dispatcher implements Runnable {

    private Socket socket;
    private Request request;
    private Response response;


    public Dispatcher(Socket socket) {
        this.socket = socket;
        try{
            request = new Request(socket);
            response = new Response(socket);
        } catch (Exception e) {
            e.printStackTrace();
            this.releals();
        }
    }


    public void run() {

        try{
            if (request.getUrl()==null||request.getUrl().equals("")){
                response.print(this.getHtml("index.html"));
                response.pushToBrowse(200);
                return ;
            }
            Servlet servlet = WebApp.getServlet(request.getUrl());
            if (servlet!=null){
                servlet.service(request,response);
                response.pushToBrowse(200);
            }else {
                String data = this.getHtml("Error.html");
                response.print(data);
                response.pushToBrowse(404);
            }
        } catch (IOException e) {
            try{
                response.print("服务器正在修理中....");
                response.pushToBrowse(505);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        this.releals();
    }

    public String getHtml(String path){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        byte base[]  = new byte[1024*2];
        int len;
        try{
            len = is.read(base);
            String data = new String(base,0,len);
            is.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void releals() {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
