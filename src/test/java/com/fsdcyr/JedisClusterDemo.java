package com.fsdcyr;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fsdcyr on 2017/10/17
 */
public class JedisClusterDemo {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("39.108.116.253", 6379));
        jedisClusterNode.add(new HostAndPort("39.108.116.253", 6380));
        jedisClusterNode.add(new HostAndPort("39.108.116.253", 6385));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, 1000, 1000, 5, poolConfig);
        jedisCluster.set("hello", "world");
        System.out.println(jedisCluster.get("hello"));
    }
}
