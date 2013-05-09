package com.test;


public class Test {

    public static void main(String[] args) {
        String absPath = "E:\\temp\\111.rar";
        // 文件绝对目录
        String toPath = "E:\\temp\\";
        // 文件目录
        boolean flag = Decompress.unrarFiles(absPath, toPath);
        System.out.println("flag ---" + flag);
    }

}