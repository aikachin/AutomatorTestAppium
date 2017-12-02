package com.redis.javaDemo;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: Aikachin
 * @Description:
 * @Date: Created in 10:06 2017/11/29 0029.
 * @Modified by :
 */
public class RedisJavaDemo {
    public static void main(String[] args) {
        RedisKeyJava();
    }

    // 连接到Redis服务
    public static void RedisJava() {
        // 连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Redis链接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行：" + jedis.ping());
    }

    // Redis Java Strign(字符串) 实例
    public static void RedisStringJava() {
        // 连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Redis链接成功");
        // 设置Redis字符串数据
        jedis.set("runoobkey", "www.runoobkey.com");
        System.out.println("Redis存储的字符串为：" + jedis.get("runoobkey"));
    }

    // Redis Java List(列表) 实例
    public static void RedisListJava() {
        // 连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Redis链接成功");
        // 存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Baidu");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为：" + list.get(i));
        }
    }

    // Redis Java key 实例
    public static void RedisKeyJava() {
        // 连接本地Redis服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("Redis链接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }
}
