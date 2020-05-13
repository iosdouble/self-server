package com.nh.server.servlet;

import com.nh.server.request.Request;
import com.nh.server.response.Response;

/**
 * @Classname Servlet
 * @Description TODO
 * @Date 2020/5/13 11:08 AM
 * @Created by nihui
 */
public interface Servlet {
    public void service(Request request, Response response);
}
