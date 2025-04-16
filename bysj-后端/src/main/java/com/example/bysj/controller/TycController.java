package com.example.bysj.controller;


import com.example.bysj.entity.table;
import com.example.bysj.service.TycService;
import com.example.springbootcodegenerator.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.swing.tree.TreeNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/springbootcodegenerator")
public class TycController {
    @Autowired
    private TycService tycService;



    @GetMapping("/tyc/controller/{filename}")
    public ResponseEntity<Resource> getController(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/controller";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tyc/entity/{filename}")
    public ResponseEntity<Resource> getEntity(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/entity";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tyc/mapper/{filename}")
    public ResponseEntity<Resource> getMapper(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/mapper";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tyc/mapper/xml/{filename}")
    public ResponseEntity<Resource> getMapperXml(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/mapper/xml";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tyc/service/{filename}")
    public ResponseEntity<Resource> getService(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/service";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/tyc/service/impl/{filename}")
    public ResponseEntity<Resource> getServiceImpl(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/tyc/service/impl";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/util/{filename}")
    public ResponseEntity<Resource> getResult(@PathVariable String filename) {
        String UPLOAD_DIR = "D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator/util";
        Path file = Paths.get(UPLOAD_DIR, filename);

        try {
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                // 使用条件运算符设置Content-Type
                String contentType = Files.probeContentType(file);
                if (contentType == null) {
                    contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 使用MediaType中的常量
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // 使用MediaType.parseMediaType解析contentType
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }




    @GetMapping("/tyc/define")
    public Result define(@RequestParam("cities[]") String[] lists){
        System.out.println(Arrays.toString(lists));
        tycService.define(lists);
        return Result.success();
    }

    @GetMapping("/tyc/download")
    public Result download(){
        tycService.download();

        return Result.success();
    }
    @GetMapping("/tyc/download/file")
    public ResponseEntity<Resource> downloadFile() throws Exception {
        tycService.download();
        // 假设文件存储在项目的某个目录下，这里需要替换为实际的文件路径
        String filePath = "D:/javaworkspace/bysj/download/folder.zip";
        Path path = Paths.get(filePath);
        Resource resource = new FileSystemResource(path);

        if (resource.exists() || resource.isReadable()) {
            String contentType = "application/octet-stream"; // 可以根据文件类型设置更具体的MIME类型
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/tyc/createtable")
    public Result createtable(@RequestBody table table1){
        try {
            String sql = table1.getSql();
            String tableName = table1.getTableName();
            // 这里应该添加对sql的验证和清理逻辑，以防止SQL注入
            tycService.createtable(sql,tableName);
            return Result.success();
        } catch (Exception e) {
            System.out.println("error");// 处理异常，并返回相应的错误信息
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/tyc/createTree")
    public List<TreeNode> createTree(){
        List<TreeNode> treeData = tycService.createTree();
        return  treeData;
    }



}
