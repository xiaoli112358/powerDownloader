package com.itcast.core;

import com.itcast.constant.Constant;

/**
 * 展示下载信息
 */
public class DownLoadInfoThread implements Runnable {
    //下载文件总大小
    private long httpFileContentLength;
    //本地已经下载文件大小
    public double finishedSize;
    //本次累计下载大小
    public volatile double downSize;
    //前一次下载的大小
    public double prevSize;

    public DownLoadInfoThread(long httpFileContentLength) {
        this.httpFileContentLength = httpFileContentLength;
    }

    @Override
    public void run() {
        //计算文件总大小 MB
        String httpFileSize = String.format("%.2f", httpFileContentLength / Constant.MB);
        //计算每秒下载速率 kb
        int speed =(int)((downSize - prevSize) / 1024d);
        prevSize=downSize;
        //剩余文件大小
        double remainSize = httpFileContentLength - finishedSize - downSize;
        //剩余时间
        String remainTime = String.format("%.1f",remainSize / 1024d / speed);
        //如果计算出的时间无限大，则显示为-
        if("Infinity".equals(remainTime)){
            remainTime="-";
        }
        //已下载大小
        String currentFileSize = String.format("%.2f", (downSize - finishedSize) / Constant.MB);

        String downInfo = String.format("已下载 %smb/%smb,速度 %s,剩余时间 %s",
                currentFileSize, httpFileSize, speed, remainTime);

        System.out.print("/r");
        System.out.print(downInfo);
    }
}
