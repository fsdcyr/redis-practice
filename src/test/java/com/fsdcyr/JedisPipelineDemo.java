package com.fsdcyr;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Created by fsdcyr on 2017/10/11
 */
public class JedisPipelineDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("39.108.116.253", 6379);
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("a", "1");
        pipeline.del("a");
        List<Object> resultList = pipeline.syncAndReturnAll();
        resultList.forEach(System.out::println);
    }
}
