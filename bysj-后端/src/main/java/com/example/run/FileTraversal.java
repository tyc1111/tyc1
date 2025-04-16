package com.example.run;

import java.io.File;

public class FileTraversal {

    public static void main(String[] args) {
        File directory = new File("D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator"); // 替换为你的目录路径
        traverseDirectory(directory);
    }

    public static void traverseDirectory(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 如果是目录，则递归遍历
                        traverseDirectory(file);
                    } else {
                        System.out.println(file.getAbsolutePath());
                        String relativePath = extractRelativePathAfterTyc(file.getAbsolutePath());
                        System.out.println(relativePath);// 如果是文件，则处理文件

                    }
                }
            }
        } else {
            System.out.println("给定的路径不是一个有效的目录！");
        }
    }
    public static String extractRelativePathAfterTyc(String filePath) {
        // 找到tyc在路径中首次出现的位置
        int tycIndex = filePath.indexOf("tyc\\"); // 注意这里使用双斜杠，因为Java字符串中的反斜杠是转义字符
        if (tycIndex == -1) {
            // 如果没有找到tyc，则返回空字符串或抛出异常
            return "";
        }

        // 截取从tyc开始到文件末尾的部分
        return filePath.substring(tycIndex); // 直接返回从tyc开始的部分，因为路径已经是Windows风格的
    }
}