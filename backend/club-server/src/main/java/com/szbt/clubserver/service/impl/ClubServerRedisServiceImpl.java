package com.szbt.clubserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szbt.clubserver.service.ClubServerRedisService;
import org.example.entity.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ClubServerRedisServiceImpl implements ClubServerRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean addIntoRedis(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value,1, TimeUnit.HOURS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteFromRedis(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getFromRedisMapClass(String key, Class thisClass){
        String jsonTextFromRedis = (String)redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        if (jsonTextFromRedis != null){
            try {
                Object objectFromRedis = mapper.readValue((String)jsonTextFromRedis, thisClass);
                return objectFromRedis;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean addIntoRedisMapClass(String key, Object o) {
        if(o==null) return false;
        ObjectMapper mapper = new ObjectMapper();
        // java对象转换为json字符
        try {
            String clubInfoJsonText =  mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, clubInfoJsonText,1, TimeUnit.HOURS);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
