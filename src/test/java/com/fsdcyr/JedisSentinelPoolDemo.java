package com.fsdcyr;

import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fsdcyr on 2017/10/16
 */
public class JedisSentinelPoolDemo {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        String masterName = "mymaster";
        Set<String> sentinels = Sets.newHashSet("39.108.116.253:26379", "39.108.116.253:26380", "39.108.116.253:26381");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig);
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            List<Map<String,String>> slaves = jedis.sentinelSlaves(masterName);
            slaves.forEach(map -> {
                System.out.println(map.get("name"));
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }
}
