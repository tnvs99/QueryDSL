package com.example.jwt_study.global.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    public ValueOperations<String, Object> getValueOperations() {
        return redisTemplate.opsForValue();
    }

    public HashOperations<String, Object, Object> getHashOperations() {
        return redisTemplate.opsForHash();
    }

    public ListOperations<String, Object> getListOperations() {
        return redisTemplate.opsForList();
    }

    public void setExpire(String key, long timeout) {
        if(Boolean.TRUE.equals(hasKey(key))) {
            redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
        }
    }

    public void delete(String key) {
        if(Boolean.TRUE.equals(hasKey(key))) {
            redisTemplate.delete(key);
        }
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void execute(Runnable operation) {
        try {
            operation.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
