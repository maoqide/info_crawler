package com.mao.infocrawler.utils;

import redis.clients.jedis.*;

import java.util.Iterator;


/**
 * Created by mao on 2016/3/12.
 */

public class RedisUtil {

    private static Jedis jedis;//非切片客户端连接
    private static JedisPool jedisPool;//非切片连接池


    public RedisUtil() {
        initialPool();
    }

    private void initialPool() {

        if (jedisPool == null) {
            //配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(500);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(1000 * 100);
            config.setTestOnBorrow(true);

            jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        }
    }

    /**
     * 清空数据
     */
    public void flushDB() {

        try {
            jedis = jedisPool.getResource();
            jedis.flushDB();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 存储数据
     *
     * @param key
     * @param val
     * @return
     */
    public boolean setStringVal(String key, String val) {

        try {
            jedis = jedisPool.getResource();
            return "OK".equals(jedis.set(key, val));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public String getStringVal(String key) {
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 遍历数据
     *
     * @return
     */
    public Iterator keysSet() {
        try {
            jedis = jedisPool.getResource();
            return jedis.keys("*").iterator();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 得到爬到的总条数
     *
     * @return
     */
    public int totalSize() {
        try {
            jedis = jedisPool.getResource();
            return jedis.keys("*").size();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void main(String[] args) {
        RedisUtil r = new RedisUtil();
        r.setStringVal("1463312670728", " ");
//        Iterator it = r.keysSet();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            String value = r.getStringVal(key);
//            System.out.println(key + value);
//        }

        //r.flushDB();
    }
}