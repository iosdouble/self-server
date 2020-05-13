package com.nh.server.response;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Classname Response
 * @Description TODO
 * @Date 2020/5/13 10:54 AM
 * @Created by nihui
 */
public class Response {

    private BufferedWriter bw;

    StringBuilder content;

    StringBuilder headInfo;

    private int len;

    private String blank = " ";
    private String CRLF = "\r\n";

    public Response(){
        content = new StringBuilder();
        headInfo = new StringBuilder();
        len = 0;
    }

    public Response(Socket client){
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response(OutputStream os){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    public Response println(String msg){
        len += (msg+CRLF).getBytes().length;
        content.append(msg).append(CRLF);
        return this;
    }

    public Response print(String msg){
        len += msg.getBytes().length;
        content.append(msg);
        return this;
    }

    public void pushToBrowse(int code) throws IOException {
        if (headInfo == null){
            code = 505;
        }
        headInfo(code);
        bw.append(headInfo.toString());
        bw.append(content.toString());
        bw.flush();
    }

    private void headInfo(int code) {
        headInfo.append("HTTP/1.1").append(blank);
        headInfo.append(code).append(blank);
        switch (code){
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("NOT FOUND").append(CRLF);
                break;
            case 505:
                headInfo.append("SERVER ERROR").append(CRLF);
                break;

        }
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("shsxt Server/0.0.1:charset=utf-8").append(CRLF);
        headInfo.append("Content-type:").append("text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        headInfo.append(CRLF);
        headInfo.append(content.toString());
    }
}
