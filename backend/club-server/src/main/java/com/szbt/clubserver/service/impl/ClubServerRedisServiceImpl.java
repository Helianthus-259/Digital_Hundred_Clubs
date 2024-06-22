package com.szbt.clubserver.service.impl;

import com.szbt.clubserver.service.ClubServerRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClubServerRedisServiceImpl implements ClubServerRedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public boolean addIntoRedis(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
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
}
