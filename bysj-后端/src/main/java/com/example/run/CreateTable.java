package com.example.run;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTable{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/tyc";
        String user = "root";
        String password = "1234";
        Scanner sc= new Scanner(System.in);
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
        System.out.println(sql);

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
}