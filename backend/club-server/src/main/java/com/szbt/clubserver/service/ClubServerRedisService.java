package com.szbt.clubserver.service;

import java.util.List;

public interface ClubServerRedisService {
    boolean addIntoRedis(String key, Object o);
    boolean deleteFromRedis(String key);
    Object getFromRedis(String key);

    <T> T getFromRedisMapClass(String key, Class<T> elementType);
    <T> boolean addIntoRedisMapClass(String key, T t);

    <T> List<T> getListFromRedis(String key, Class<T> elementType);
    <T> boolean addListToRedis(String key, List<T> list);
}
