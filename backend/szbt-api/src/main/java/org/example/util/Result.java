package org.example.util;

import org.example.enums.StatusCode;

import java.util.HashMap;

public class Result {
    public static HashMap<String,Object> success(StatusCode code, String msg){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code.getCode());
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

    public static HashMap<String,Object> send(StatusCode code, Object data){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code.getCode());
//        result.put("msg","");
        result.put("data",data);
        return result;
    }

    public static HashMap<String,Object> fail(StatusCode code,String msg){
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",code.getCode());
        result.put("msg",msg);
        result.put("data","");
        return result;
    }
}
