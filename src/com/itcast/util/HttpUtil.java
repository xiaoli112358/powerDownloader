package com.itcast.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    /**
     * 获取httpURLConnection连接对象
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        //向所在服务器发送标识信息
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Mobile Safari/537.36");
        return httpURLConnection;
    }

    /**
     * 获取下载文件名字
     *
     * @param url
     * @return
     */
    public static String getHttpFileName(String url) {
        int i = url.lastIndexOf('/');
        String name = url.substring(i + 1);
        return name;
    }
}
