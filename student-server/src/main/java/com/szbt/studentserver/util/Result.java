package com.szbt.studentserver.util;

import java.util.HashMap;

public class Result {
    public static HashMap<String,Object> success(int code, String msg){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code);
        result.put("msg",msg);
        result.put("data","");
        return result;
    }
    public static HashMap<String,Object> success(Object data){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",200);
//        result.put("msg","");
        result.put("data",data);
        return result;
    }

    public static HashMap<String,Object> send(int code, Object data){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code);
//        result.put("msg","");
        result.put("data",data);
        return result;
    }

    public static HashMap<String,Object> fail(int code,String msg){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code);
        result.put("msg",msg);
        result.put("data","");
        return result;
    }
}
