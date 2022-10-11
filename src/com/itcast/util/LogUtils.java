package com.itcast.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 日志工具类
 */
public class LogUtils {

    public static void info(String msg, Object... args) {
        print(msg, "-info-", args);
    }

    public static void error(String msg, Object... args) {
        print(msg, "-error-", args);
    }

    private static void print(String msg, String level, Object... args) {//参数个数不确定就可以按照args这种方式写
        if (args != null && args.length > 0) {
            // %s为占位符，String.format会将args拼接到msg后
            msg = String.format(msg.replace("{}", "%s"), args);
        }
//        获取当前线程的名字
        String name = Thread.currentThread().getName();
        //打印当前时间、名字、级别、信息
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + "   " + name + level + msg);

    }
}
