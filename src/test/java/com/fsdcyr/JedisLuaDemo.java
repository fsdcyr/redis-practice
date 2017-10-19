package com.fsdcyr;

import redis.clients.jedis.Jedis;

/**
 * Created by fsdcyr on 2017/10/12
 */
public class JedisLuaDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("39.108.116.253", 6379);
//        jedis.auth("foobared");
        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script, 1, key);
        System.out.println(result);

        String scriptSha = jedis.scriptLoad(script);
        System.out.println(scriptSha);
        System.out.println(jedis.evalsha(scriptSha, 1, key));
    }
}
