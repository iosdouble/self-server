package com.nh.server.servlet;

import com.nh.server.request.Request;
import com.nh.server.response.Response;

/**
 * @Classname OtherServlet
 * @Description TODO
 * @Date 2020/5/13 11:55 AM
 * @Created by nihui
 */
public class OtherServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.println("其他页面");
        response.println("heihei");
        response.println("other");
    }
}
