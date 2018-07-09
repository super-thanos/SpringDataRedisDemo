package com.xt.study.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableRedisRepositories(basePackages = "com.xt.study.spring.redis")
public class RedisDAO {
  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  public long incr(String key, long delta) {
    return redisTemplate.opsForValue().increment(key, delta);
  }

  public Object get(String key) {
    BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
    return boundValueOperations.get();
  }

  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }
}
