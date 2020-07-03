package com.wyc.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuMi
 * @version V1.0
 * @Package com.aisino.social.common.utils
 * @Description: http远程请求工具类
 * @date 2019/9/17 -   15:12
 */
public class HttpPostRequest {

    public static String get(String urlStr) throws Exception{
        HttpURLConnection conn = null;
        try {
            //创建URL对象
            URL url = new URL(urlStr);
            //获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            //设置通用的请求属性
            setHttpUrlConnection(conn, "GET");
            //建立实际的连接
            conn.connect();
            //获取响应的内容
            return readResponseContent(conn.getInputStream());
        } finally {
            if(null!=conn) conn.disconnect();
        }
    }

    public static String post(String urlStr, Map<String, Object> paramMap) throws Exception{
        HttpURLConnection conn = null;
        PrintWriter writer = null;
        try {
            // 创建URL对象
            URL url = new URL(urlStr);
            // 获取请求参数
            String param = getParamString(paramMap);
            // 获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            //设置通用请求属性
            setHttpUrlConnection(conn, "POST");
            //建立实际的连接
            conn.connect();
            //将请求参数写入请求字符流中
            writer = new PrintWriter(conn.getOutputStream());
            writer.print(param);
            writer.flush();
            //读取响应的内容
            return readResponseContent(conn.getInputStream());
        } finally {
            if(null!=conn) conn.disconnect();
            if(null!=writer) writer.close();
        }
    }

    private static String readResponseContent(InputStream in) throws IOException {
        Reader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            reader = new InputStreamReader(in);
            char[] buffer = new char[1024];
            int head = 0;
            while( (head = reader.read(buffer)) > 0 ){
                content.append(new String(buffer, 0, head));
            }
            return content.toString();
        } finally {
            if(null!=in) in.close();
            if(null!=reader) reader.close();
        }
    }

    /**
     * 设置Http连接属性
     * @param conn  http连接
     * @param requestMethod
     * @throws ProtocolException
     */
    private static void setHttpUrlConnection(HttpURLConnection conn, String requestMethod) throws ProtocolException {
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
        if(null != requestMethod && "POST".equals(requestMethod)){
            conn.setDoOutput(true);
            conn.setDoInput(true);
        }
    }

    /**
     * 将参数转为路径字符串
     * @param paramMap 参数
     * @return
     */
    private static String getParamString(Map<String, Object> paramMap){
        if(null == paramMap || paramMap.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(String key : paramMap.keySet() ){
            builder.append("&")
                    .append(key).append("=").append(paramMap.get(key));
        }
        return builder.deleteCharAt(0).toString();
    }

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accessKey", "heZ8m3P4izwmOaJSIe0i2VUyQvMbwkEj");
        paramMap.put("mobile", "13931157213");
        paramMap.put("password", "a83981816");
        paramMap.put("type", "login");
        try {
            JSONObject result= JSON.parseObject(HttpPostRequest.post("http://www.htxxpx.com/interfaces/getToken", paramMap));
            if("success".equals(result.getString("operRes"))){
                //获得的令牌
                String token=result.getString("token");
                System.out.println(token);
                //response.sendResirect(“ http://<domain>/interfaces/login?token= fd5tEMl5gYbSi9g9dgd3m8u2Oe1J7fkA&mobile=18600863155&timestamp=1468485098135&schoolId=1&productId=199&companyId=888”)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
