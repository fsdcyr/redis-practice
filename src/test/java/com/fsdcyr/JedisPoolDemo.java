package com.fsdcyr;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by fsdcyr on 2017/10/11
 */
public class JedisPoolDemo {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "39.108.116.253", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get("hello");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
