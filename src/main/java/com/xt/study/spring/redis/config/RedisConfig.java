package com.xt.study.spring.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:properties/redis.properties")
@EnableRedisRepositories
public class RedisConfig {

  @Bean
  public JedisPoolConfig jedisPoolConfig(@Value("${redis.maxIdle}") int maxIdle,
      @Value("${redis.maxTotal}") int maxTotal,
      @Value("${redis.maxWaitMillis}") int maxWaitMillis,
      @Value("${redis.blockWhenExhausted}") boolean blockwhenExhausted,
      @Value("${redis.testOnBorrow}") boolean testOnBorrow) {
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxIdle(maxIdle);
    poolConfig.setMaxTotal(maxTotal);
    poolConfig.setMaxWaitMillis(maxWaitMillis);
    poolConfig.setBlockWhenExhausted(blockwhenExhausted);
    poolConfig.setTestOnBorrow(testOnBorrow);
    return poolConfig;
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory(@Autowired JedisPoolConfig jedisPoolConfig,
      @Value("${redis.host}") String host,
      @Value("${redis.port}") int port,
      @Value("${redis.password}") String password,
      @Value("${redis.timeout}") int timeout) {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(host);
    jedisConnectionFactory.setPort(port);
    jedisConnectionFactory.setTimeout(timeout);
    jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate stringRedisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory) {
    return new StringRedisTemplate(jedisConnectionFactory);
  }

}
