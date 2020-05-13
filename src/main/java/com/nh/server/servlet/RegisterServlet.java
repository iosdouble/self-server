package com.nh.server.servlet;

import com.nh.server.request.Request;
import com.nh.server.response.Response;

/**
 * @Classname RegisterServlet
 * @Description TODO
 * @Date 2020/5/13 11:56 AM
 * @Created by nihui
 */
public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta http‐equiv=\"content‐type\" content=\"text/html;charset=utf‐8\">");
        response.print("<title>");

        response.print("第一个响应网页");

        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        //内容
        response.print("我响应啦~~");
        response.print("</body>");
        response.print("</html>");
    }
}
