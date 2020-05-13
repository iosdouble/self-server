package com.nh.server.web;

import com.nh.server.servlet.Servlet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @Classname WebApp
 * @Description TODO
 * @Date 2020/5/13 11:09 AM
 * @Created by nihui
 */
public class WebApp {
    private static WebContext context;

    static {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            WebHandler handler = new WebHandler();

            parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml"),handler);
            System.out.println("加载配置文件");
            context = new WebContext(handler.getEntitys(),handler.getMappings());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("解析文件错误");
        }
    }

    public static Servlet getServlet(String url){
        String name = context.getClz("/"+url);
        if (name == null){
            return null;
        }
        Class clz;
        try{
            clz = Class.forName(name);
            Servlet ser = (Servlet) clz.newInstance();
            return ser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
