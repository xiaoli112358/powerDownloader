package com.itcast.learn;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static void main(String[] args) {
        //获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);//获取一个线程
        //延时2秒 每隔3秒执行一次
        s.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis());
            //模拟耗时操作
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 2, 3, TimeUnit.SECONDS);
    }

    public static void schedule() {
        //获取对象
        ScheduledExecutorService s = Executors.newScheduledThreadPool(1);//获取一个线程
        //延时2秒后执行
        s.schedule(() -> System.out.println(Thread.currentThread().getName()), 2, TimeUnit.SECONDS);
        //关闭
        s.shutdown();
    }
}
