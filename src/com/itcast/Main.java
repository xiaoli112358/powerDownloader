package com.itcast;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url=null;
        if (args==null || args.length==0){
//            如果没有输入就写一个死循环
            for (;  ; ) {
                System.out.println("请输入下载地址");
                Scanner scanner = new Scanner(System.in);
                url = scanner.next();
                if (url!=null){
                    break;
                }
            }
        }else {
            url=args[0];
        }
        System.out.println(url);
    }
}
