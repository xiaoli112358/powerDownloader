package com.itcast.util;

import java.io.File;

public class FileUtils {
    /**
     * 获取本地文件大小
     *
     * @param path
     * @return
     */
    public static long getDownFileLength(String path) {
        File file = new File(path);
        return file.exists() && file.isFile() ? file.length() : 0;
    }
}
