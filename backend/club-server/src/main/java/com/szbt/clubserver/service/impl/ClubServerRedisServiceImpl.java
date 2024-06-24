package com.szbt.clubserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szbt.clubserver.service.ClubServerRedisService;
import org.example.entity.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public <T> T getFromRedisMapClass(String key, Class<T> elementType){
        String jsonTextFromRedis = (String)redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        if (jsonTextFromRedis != null){
            try {
                T tFromRedis = mapper.readValue((String)jsonTextFromRedis, elementType);
                return tFromRedis;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public <T> boolean addIntoRedisMapClass(String key, T t) {
        if(t==null) return false;
        ObjectMapper mapper = new ObjectMapper();
        // java对象转换为json字符
        try {
            String clubInfoJsonText =  mapper.writeValueAsString(t);
            redisTemplate.opsForValue().set(key, clubInfoJsonText,1, TimeUnit.HOURS);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T> List<T> getListFromRedis(String key, Class<T> elementType) {
        String jsonTextFromRedis = (String) redisTemplate.opsForValue().get(key);
        System.out.println(jsonTextFromRedis);
        ObjectMapper mapper = new ObjectMapper();
        if (jsonTextFromRedis != null) {
            try {
                List<T> listFromRedis = mapper.readValue(jsonTextFromRedis, mapper.getTypeFactory().constructCollectionType(List.class, elementType));
                return listFromRedis;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public <T> boolean addListToRedis(String key, List<T> list) {
        if (list == null || list.isEmpty()) return false;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonText = mapper.writeValueAsString(list);
            redisTemplate.opsForValue().set(key, jsonText, 1, TimeUnit.HOURS);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
