package com.szbt.clubserver.service;

public interface ClubServerRedisService {
    boolean addIntoRedis(String key, Object o);
    boolean deleteFromRedis(String key);
    Object getFromRedis(String key);

    Object getFromRedisMapClass(String key, Class thisClass);
}
