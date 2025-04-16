package com.example.springbootcodegenerator.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;//响应码 1 成功 0 失败
    private String msg; //相应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功相应
    public static Result success(){
        return new Result(1,"success",null);
    }

    //查询 成功相应

    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    //失败相应

    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
