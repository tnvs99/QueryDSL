package com.example.jwt_study.global.service;

import com.example.jwt_study.global.handler.RedisHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisHandler redisHandler;

    public void saveValue(String key, Object value) {
        redisHandler.execute(() -> {
            redisHandler.getValueOperations().set(key, value);
        });
    }

    public void saveValue(String key, Object value, long timeout) {
        redisHandler.execute(() -> {
            redisHandler.getValueOperations().set(key, value, timeout, TimeUnit.MILLISECONDS);
        });
    }

    public Object getValue(String key) {
        return redisHandler.getValueOperations().get(key);
    }

    public void saveHashMap(String key, HashMap<String, Object> map) {
        redisHandler.execute(() -> {
            redisHandler.getHashOperations().putAll(key, map);
        });
    }

    public void saveHashMap(String key, HashMap<String, Object> map, long timeout) {
        redisHandler.execute(() -> {
            redisHandler.getHashOperations().putAll(key, map);
            redisHandler.setExpire(key, timeout);
        });
    }

    public Map<String, Object> getHashMap(String key) {
        return redisHandler.getHashOperations().entries(key)
                .entrySet()
                .stream()
                .map(entry -> {
                    String field = (String) entry.getKey();
                    Object value = entry.getValue();
                    return Map.entry(field, value);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void saveList(String key, List<?> list) {
        redisHandler.execute(() -> {
            for (Object item : list) {
                redisHandler.getListOperations().rightPush(key, item);
            }
        });
    }

    public void saveList(String key, List<?> list, long timeout) {
        redisHandler.execute(() -> {
            for (Object item : list) {
                redisHandler.getListOperations().rightPush(key, item);
            }
            redisHandler.setExpire(key, timeout);
        });
    }

    public List<?> getList(String key) {
        return redisHandler.getListOperations().range(key, 0, -1);
    }

    public void remove(String key) {
        redisHandler.delete(key);
    }
}
