package com.example.run;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class FolderZipper {

    public static void main(String[] args) {
        // 源文件夹路径，包含.java和.xml文件
        Path sourceDir = Paths.get("D:\\javaworkspace\\bysj\\src\\main\\java\\com\\example\\springbootcodegenerator");
        // 目标ZIP文件路径
        Path zipFilePath = Paths.get("D:\\javaworkspace\\bysj\\download\\folder.zip");

        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            Files.walk(sourceDir)
                    .filter(Files::isRegularFile) // 过滤出常规文件（不包括目录）
                    .filter(path -> path.toString().endsWith(".java") || path.toString().endsWith(".xml")) // 过滤出.java和.xml文件
                    .forEach(path -> {
                        try {
                            // 获取相对于源目录的相对路径
                            Path relativePath = sourceDir.relativize(path);
                            // 将文件添加到ZIP输出流中
                            addToZip(zipOut, path, relativePath.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToZip(ZipOutputStream zipOut, Path file, String fileName) throws IOException {
        try (InputStream in = Files.newInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                zipOut.write(buffer, 0, len);
            }

            zipOut.closeEntry();
        }
    }
}