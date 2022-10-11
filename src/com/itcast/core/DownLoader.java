package com.itcast.core;

import com.itcast.constant.Constant;
import com.itcast.util.HttpUtil;
import com.itcast.util.LogUtils;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * 下载器
 */
public class DownLoader {

    public void downLoader(String url) {
        //获取文件名
        String httpFileName = HttpUtil.getHttpFileName(url);
        //文件下载路径
        httpFileName = Constant.PATH + httpFileName;
        //获取链接
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = HttpUtil.getHttpURLConnection(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                InputStream in = httpURLConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(in);
                FileOutputStream fos = new FileOutputStream(httpFileName);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int len = -1;
            while ((len = bis.read()) != -1) {
                bos.write(len);
            }

        } catch (FileNotFoundException e) {
            //System.out.println("下载的文件不存在");
            LogUtils.error("下载的文件不存在{}",url);
        } catch (Exception e) {
            //System.out.println("下载失败！");
            LogUtils.error("下载失败！");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
