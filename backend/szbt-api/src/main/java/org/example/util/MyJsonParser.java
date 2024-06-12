package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class MyJsonParser {
    public static Object parserJsonText(Object jsonText)
    {
        if(jsonText==null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            if (jsonText.toString().startsWith("["))
            {
                List<Map<String, Object>> list = objectMapper.readValue((String) jsonText, List.class);
                return list;
            }
            Map<String, Object> map = objectMapper.readValue((String) jsonText, Map.class);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
