package com.itcast.core;

import com.itcast.constant.Constant;
import com.itcast.util.FileUtils;
import com.itcast.util.HttpUtil;
import com.itcast.util.LogUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 下载器
 */
public class DownLoader {

    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void downLoader(String url) {
        //获取文件名
        String httpFileName = HttpUtil.getHttpFileName(url);
        //文件下载路径
        httpFileName = Constant.PATH + httpFileName;
        //获取本地文件大小
        long localFileLength = FileUtils.getDownFileLength(httpFileName);

        //获取连接对象
        HttpURLConnection httpURLConnection = null;
        DownLoadInfoThread downLoadInfoThread =null;
        try {
            httpURLConnection = HttpUtil.getHttpURLConnection(url);
            //获取下载文件的总大小
            int contentLength = httpURLConnection.getContentLength();
            //判断是否已经下载过
            if (contentLength >= localFileLength) {
                LogUtils.info("{}已经下载，无需下载", httpFileName);
                return;
            }
            //创建获取下载信息的任务对象
            downLoadInfoThread = new DownLoadInfoThread(contentLength);
            //将任务交给线程执行，每隔一秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(downLoadInfoThread, 1, 1, TimeUnit.SECONDS);

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
            byte[] buffer = new byte[Constant.BYTE_SIZE];
            while ((len = bis.read(buffer)) != -1) {
                downLoadInfoThread.downSize+=len;
                bos.write(buffer,0,len);
            }

        } catch (FileNotFoundException e) {
            //System.out.println("下载的文件不存在");
            LogUtils.error("下载的文件不存在{}", url);
        } catch (Exception e) {
            //System.out.println("下载失败！");
            LogUtils.error("下载失败！");
        } finally {
            System.out.print("\r");
            System.out.print("下载完成！");

            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            //关闭
            scheduledExecutorService.shutdownNow();
        }
    }
}
