package com.example.bysj.service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.bysj.entity.Menu;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.example.bysj.entity.Menu.getFilePathTree;

@Service
public class TycServiceImpl implements TycService {

    // 数据库 URL
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/tyc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // 数据库驱动
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "root";
    // @author 值
    private static final String AUTHOR = "tyc";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.example.springbootcodegenerator";
    // xml文件路径
    private static final String XML_PACKAGE_URL = "/src/main/resources/mapper/";
    // xml 文件模板
    private static final String XML_MAPPER_TEMPLATE_PATH = "generator1/templates/mapper.xml";
    // mapper 文件模板
    private static final String MAPPER_TEMPLATE_PATH = "generator1/templates/mapper.java";
    // entity 文件模板
    private static final String ENTITY_TEMPLATE_PATH = "generator1/templates/entity.java";
    // service 文件模板
    private static final String SERVICE_TEMPLATE_PATH = "generator1/templates/service.java";
    // serviceImpl 文件模板
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "generator1/templates/serviceImpl.java";
    // controller 文件模板
    private static final String CONTROLLER_TEMPLATE_PATH = "generator1/templates/controller.java";

    private String tablename;

    private static String[] fileList = new String[1000];
    private static int temp = 0;
    public void define(String[] lists) {


        AutoGenerator generator = new AutoGenerator();
        // 配置自定义代码模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        templateConfig.setXml("");
        templateConfig.setMapper("");
        templateConfig.setEntity("");
        for (int i = 0; i < lists.length; i++) {


            if (lists[i].equals("controller")) {
                templateConfig.setController(CONTROLLER_TEMPLATE_PATH);
            } else if (lists[i].equals("service")) {
                templateConfig.setService(SERVICE_TEMPLATE_PATH);
                templateConfig.setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH);
            } else if (lists[i].equals("mapper")) {
                templateConfig.setXml(XML_MAPPER_TEMPLATE_PATH);
                templateConfig.setMapper(MAPPER_TEMPLATE_PATH);
            } else if (lists[i].equals("entity")) {
                templateConfig.setEntity(ENTITY_TEMPLATE_PATH);
            }
        }
        generator.setTemplate(templateConfig);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("tyc");
        packageConfig.setParent(BASE_PACKAGE_URL);
        generator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tablename.toLowerCase(Locale.FRANCE));
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");
        generator.setStrategy(strategy);

        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

    @Override
    public void download() {
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

    @Override
    public void createtable(String sql,String tableName) {
        this.tablename = tableName;
        String url = "jdbc:mysql://localhost:3306/tyc";
        String user = "root";
        String password = "root";
        /*Scanner sc= new Scanner(System.in);
        System.out.println("请输入创建表的sql语句:");

        StringBuilder sqlBuilder = new StringBuilder();
        String line;
        // 循环读取用户的每一行输入，直到用户输入"end"为止
        while (!(line = sc.nextLine()).equalsIgnoreCase("end")) {
            sqlBuilder.append(line).append(System.lineSeparator());
        }

        // 去除最后的换行符（如果有的话）
        String sql = sqlBuilder.toString().trim();

        System.out.println("你输入的SQL语句是：");
        System.out.println(sql);*/

        /*String sql = sc.next();
        String Sql = sql.replaceAll("\\n|\\s+", " ");
        System.out.println(Sql);*/
        /*String sql = "CREATE TABLE IF NOT EXISTS Employees (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "first_name VARCHAR(40), " +
                "last_name VARCHAR(40));";*/

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TreeNode> createTree() {
        File directory = new File("D:/javaworkspace/bysj/src/main/java/com/example/springbootcodegenerator");
        traverseDirectory(directory);
        List<Menu> collect = getFilePathTree(Arrays.asList(fileList));
        System.out.println("-------转json输出结果-------");
        System.out.println(JSON.toJSON(collect));
        return (List<TreeNode>) JSON.toJSON(collect);
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


    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (null != ipt && !"".equals(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
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
//                        System.out.println(file.getAbsolutePath());
                        String relativePath = extractRelativePathAfterTyc(file.getAbsolutePath());
                        fileList[temp++] = relativePath;
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
        int tycIndex = filePath.indexOf("springbootcodegenerator\\"); // 注意这里使用双斜杠，因为Java字符串中的反斜杠是转义字符
        if (tycIndex == -1) {
            // 如果没有找到tyc，则返回空字符串或抛出异常
            return "";
        }

        // 截取从springbootcodegenerator开始到文件末尾的部分
        return filePath.substring(tycIndex); // 直接返回从tyc开始的部分，因为路径已经是Windows风格的
    }
}