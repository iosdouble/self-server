package com.nh.server.servlet;

import com.nh.server.request.Request;
import com.nh.server.response.Response;

/**
 * @Classname LoginServlet
 * @Description TODO
 * @Date 2020/5/13 11:51 AM
 * @Created by nihui
 */
public class LoginServlet implements Servlet {

    @Override
    public void service(Request request, Response response) {
        System.out.println(request.toString());
        response.print("<html>");
        response.print("<head>");
        response.print("<meta />");
        response.print("<title>");

        response.print("LoginServlet");

        response.print("</title>");

        response.print("</head>");
        response.print("<body>");
        //内容
        response.print("Login");
        response.print("</body>");
        response.print("</html>");
    }
}
