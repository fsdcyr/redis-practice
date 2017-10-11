package com.fsdcyr;

import com.fsdcyr.domain.Club;
import com.fsdcyr.domain.ProtostuffSerializer;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Created by fsdcyr on 2017/10/11
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("39.108.116.253", 6379);
            // 1.string
            jedis.get("hello");
            jedis.set("hello", "world");
            jedis.incr("counter");

            // 2.hash
            jedis.hset("myhash", "f1", "v1");
            jedis.hset("myhash", "f2", "v2");
            jedis.hgetAll("myhash");

            // 3.list
            jedis.rpush("mylist", "1");
            jedis.rpush("mylist", "2");
            jedis.rpush("mylist", "3");
            jedis.lrange("mylist", 0, -1);

            // 4.set
            jedis.sadd("myset", "a");
            jedis.sadd("myset", "b");
            jedis.sadd("myset", "a");
            jedis.smembers("myset");

            // 5.zset
            jedis.zadd("myzset", 99, "tom");
            jedis.zadd("myzset", 66, "peter");
            jedis.zadd("myzset", 33, "james");
            jedis.zrangeWithScores("myzset", 0, -1);

            ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
            String key = "club:1";
            Club club = new Club(1, "AC", "米兰", new Date(), 1);
            byte[] clubBytes = protostuffSerializer.serialize(club);
            jedis.set(key.getBytes(), clubBytes);
            byte[] resultBytes = jedis.get(key.getBytes());
            Club resultClub = protostuffSerializer.deserialize(resultBytes);
            System.out.println(resultClub);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
