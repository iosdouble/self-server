package com.nh.server.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * @Classname Request
 * @Description TODO
 * @Date 2020/5/13 10:30 AM
 * @Created by nihui
 */
public class Request {

    private String resquestInfo;

    private String method;

    private String queryStr;

    private String url;

    private Map<String,List<String>> queryStrMap = new HashMap<String, List<String>>();

    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request(InputStream is){
        byte datas[] = new byte[1024*1024*1024];
        int len ;
        try{
            len = is.read(datas);
            if (len>0){
                resquestInfo = new String(datas,0,len);
            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        paresRequestInfo();
    }

    private void paresRequestInfo(){
        System.out.println("---------分解---------");
        int index = resquestInfo.indexOf("/");
        int endIndex = resquestInfo.indexOf("HTTP/");
        method = resquestInfo.substring(0,index).toLowerCase().trim();
        this.url = resquestInfo.substring(index+1,endIndex).trim();

        index = url.indexOf("?");
        if (index>=0){
            String urlsp[] = url.split("\\?");
            url = urlsp[0];
            queryStr = urlsp[1];
        }

        if (method.equals("post")){
            String qStr = resquestInfo.substring(resquestInfo.lastIndexOf("\r\n")).trim();
            if (queryStr == null){
                queryStr = qStr;
            }else {
                if (!qStr.equals("")){
                    queryStr += "&"+qStr;
                }
            }
        }
        queryStr = queryStr ==null?"":queryStr;

        System.out.println(url+"--->"+method+"--->"+queryStr);

        this.getMap();
    }

    private void getMap() {
        String queryStrs[] = queryStr.split("&");
        for (String qStr : queryStrs){
            String kv[] =qStr.split("=");
            kv = Arrays.copyOf(kv,2);
            String key = kv[0];
            String value = kv[1]==null?null:this.decoing(kv[1],"utf-8");
            if (!queryStrMap.containsKey(key)){
                queryStrMap.put(key,new ArrayList<>());
            }
            queryStrMap.get(key).add(value);
        }
    }

    public String[] getValues(String key){
        List<String> values = queryStrMap.get(key);
        if (values == null||values.size()<=0){
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getValue(String key){
        String s[] = getValues(key);
        return s ==null?null:s[0];
    }

    public String getMethod(){
        return method;
    }

    public String getQueryStr(){
        return queryStr;
    }
    public String getUrl(){
        return url;
    }
    private String decoing(String value,String enc){
        try{
            return java.net.URLDecoder.decode(value,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
