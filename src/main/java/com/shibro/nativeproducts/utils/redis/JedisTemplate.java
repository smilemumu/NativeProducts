/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.shibro.nativeproducts.utils.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.io.Serializable;
import java.util.*;


public class JedisTemplate {

    /**
     * 默认redis锁存活时间，10秒
     */
    private static final int DEFAULT_LOCK_EXPIRE_TIME = 10;

    private JedisPool jedisPool;

    public JedisTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * Execute with a call back action with result.
     */
    public <T> T execute(JedisAction<T> jedisAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedisAction.action(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Execute with a call back action without result.
     */
    public void execute(JedisActionNoResult jedisAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedisAction.action(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Execute with a call back action with result in pipeline.
     */
    public <T> List<T> execute(PipelineAction<T> pipelineAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            return pipelineAction.action(pipeline);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Execute with a call back action without result in pipeline.
     */
    public void execute(PipelineActionNoResult pipelineAction) throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            pipelineAction.action(pipeline);
            pipeline.sync();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * Return the internal JedisPool.
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }


    /// Keys ///


    /**
     * @see Jedis#del(String...)
     */
    public long del(final String... keys) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    /**
     * @see Jedis#del(String)
     */
    public long del(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * @see Jedis#del(byte[]...)
     */
    public long del(final byte[]... keys) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    /**
     * @see Jedis#del(byte[])
     */
    public long del(final byte[] key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    /**
     * @see Jedis#expire(String, int)
     */
    public long expire(final String key, final int seconds) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    /**
     * @see Jedis#expire(byte[], int)
     */
    public long expire(final byte[] key, final int seconds) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    /**
     * @see Jedis#expireAt(String, long)
     */
    public long expireAt(final String key, final long unixTime) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        });
    }

    /**
     * @see Jedis#expireAt(byte[], long)
     */
    public long expireAt(final byte[] key, final long unixTime) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        });
    }

    /**
     * @see Jedis#exists(String)
     */
    public boolean exists(final String key) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    /**
     * @see Jedis#exists(byte[])
     */
    public boolean exists(final byte[] key) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    public long exists(final String... keys) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.exists(keys);
            }
        });
    }

    public long exists(final byte[]... keys) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.exists(keys);
            }
        });
    }

    /**
     * @see Jedis#ttl(String)
     */
    public long ttl(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    /**
     * @see Jedis#ttl(byte[])
     */
    public long ttl(final byte[] key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }


    /// Strings ///

    /**
     * @see Jedis#get(String)
     */
    public String get(final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    /**
     * @see Jedis#get(byte[])
     */
    public byte[] get(final byte[] key) {
        return execute(new JedisAction<byte[]>() {

            @Override
            public byte[] action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    /**
     * Get the value of the specified key as Long.If the key does not exist null is returned.
     *
     * @see Jedis#get(String)
     */
    public Long getAsLong(final String key) {
        String result = get(key);
        return result != null ? Long.valueOf(result) : null;
    }

    /**
     * Get the value of the specified key as Integer.If the key does not exist null is returned.
     *
     * @see Jedis#get(String)
     */
    public Integer getAsInt(final String key) {
        String result = get(key);
        return result != null ? Integer.valueOf(result) : null;
    }

    /**
     * 获取redis中存储的值，该值为string形式的json字符串，取出后反序列化为对象。
     * 返回对象
     *
     * @see Jedis#get(String)
     */
    public <T> T getBin(final String key, final Class<T> type) {
        return execute(new JedisAction<T>() {
            @Override
            public T action(Jedis jedis) {
                return jsonDecode(jedis.get(key), type);
            }
        });
    }

    /**
     * 获取redis中存储的值，该值为string形式的json字符串，取出后反序列化为对象列表。
     * 返回对象列表
     *
     * @see Jedis#get(String)
     */
    public <T> List<T> getBinList(final String key, final Class<T> type) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                return jsonDecodeArray(jedis.get(key), type);
            }
        });
    }

    /**
     * @see Jedis#mget(String...)
     */
    public List<String> mget(final String... keys) {
        return execute(new JedisAction<List<String>>() {

            @Override
            public List<String> action(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
    }

    /**
     * 一次获取多个key的值。该值为string形式的json字符串，每个值取出后反序列化为对象。
     * 返回对象列表，如果某个key获取不到值，则返回项中该位置的值为null
     *
     * @see Jedis#mget(byte[]...)
     */
    public <T extends Serializable> List<T> mgetBin(final Class<T> type, final String... keys) {
        if (keys == null || keys.length == 0) {
            return Collections.emptyList();
        }
        return execute(new PipelineAction<T>() {
            @Override
            public List<T> action(Pipeline pipeline) {
                for (String key : keys) {
                    pipeline.get(key);
                }
                List<Object> responses = pipeline.syncAndReturnAll();
                List<T> result = new ArrayList<T>(responses.size());
                for (Object response : responses) {
                    String resp = (String) response;
                    result.add(resp == null ? null : jsonDecode(resp, type));
                }
                return result;
            }
        });
    }


    /**
     * @see Jedis#incr(String)
     */
    public Long incr(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    /**
     * @see Jedis#incrBy(String, long)
     */
    public Long incrBy(final String key, final long integer) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.incrBy(key, integer);
            }
        });
    }

    /**
     * @see Jedis#incrByFloat(byte[], double)
     */
    public Double incrByFloat(final String key, final double value) {
        return execute(new JedisAction<Double>() {
            @Override
            public Double action(Jedis jedis) {
                return jedis.incrByFloat(key, value);
            }
        });
    }

    /**
     * @see Jedis#decr(String)
     */
    public Long decr(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    /**
     * @see Jedis#decrBy(String, long)
     */
    public Long decrBy(final String key, final long decrement) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.decrBy(key, decrement);
            }
        });
    }


    /**
     * @see Jedis#set(String, String)
     */
    public String set(final String key, final String value) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    /**
     * @see Jedis#set(String, String, String, String, int)
     */
    public boolean set(final String key, final String value, final String nxxx,
                       final String expx, final int time) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                String result = jedis.set(key, value, nxxx, expx, time);
                return JedisUtils.isStatusOk(result);
            }
        });
    }

    /**
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
     * GB).
     *
     * @param key   key
     * @param value value
     * @param nxxx  NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     *              if it already exist.
     * @param expx  EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time  expire time in the units of <code>expx</code>
     * @return true or false
     * @see Jedis#set(String, String, String, String, long)
     */
    public boolean set(final String key, final String value, final String nxxx,
                       final String expx, final long time) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                String result = jedis.set(key, value, nxxx, expx, time);
                return JedisUtils.isStatusOk(result);
            }
        });
    }

    /**
     * 保存对象至redis，把对象序列化为json字符串，以字符串形式存储至redis
     *
     * @see Jedis#set(String, String)
     */
    public String setBin(final String key, final Object object) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.set(key, jsonEncode(object));
            }
        });
    }

    /**
     * 保存对象至redis，把对象序列化为json字符串，以字符串形式存储至redis
     *
     * @param hash    key value键值对 value为对象
     * @param seconds 过期时间，单位秒
     * @see Jedis#setex(String, int, String)
     */
    public List<String> msetBin(final Map<String, Object> hash, final int seconds) {
        List<Response<String>> resp = execute(new PipelineAction<Response<String>>() {
            @Override
            public List<Response<String>> action(Pipeline pipeline) {
                List<Response<String>> result = new ArrayList<Response<String>>();
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    Response<String> response = pipeline.setex(entry.getKey(), seconds, jsonEncode(entry.getValue()));
                    result.add(response);
                }
                return result;
            }
        });
        List<String> respResult = new ArrayList<String>();
        for (Response<String> response : resp) {
            respResult.add(response.get());
        }
        return respResult;
    }

    /**
     * @see Jedis#setex(String, int, String)
     */
    public String setex(final String key, final String value, final int seconds) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    /**
     * 保存对象至redis，把对象序列化为json字符串，以字符串形式存储至redis, 并设置超时时间
     *
     * @see Jedis#setex(String, int, String)
     */
    public String setexBin(final String key, final Object object, final int seconds) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.setex(key, seconds, jsonEncode(object));
            }
        });
    }

    /**
     * 将key设置值为value，如果key不存在，这种情况下等同SET命令。 当key存在时，什么也不做。SETNX是”SET if Not eXists”的简写。
     * true 如果key被设置了
     * false 如果key没有被设置
     *
     * @see Jedis#setnx(String, String)
     */
    public boolean setnx(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.setnx(key, value) == 1;
            }
        });
    }

    /**
     * The command is exactly equivalent to the following group of commands: setex sexnx.
     * The operation is atomic.
     * NX – Only set the key if it does not already exist.
     * EX seconds – Set the specified expire time, in seconds.
     *
     * @see Jedis#set(String, String, String, String, int)
     */
    public boolean setnxex(final String key, final String value, final int seconds) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                String result = jedis.set(key, value, "NX", "EX", seconds);
                return JedisUtils.isStatusOk(result);
            }
        });
    }

    /**
     * GETSET is an atomic set this value and return the old value command. Set
     * key to the string value and return the old value stored at key. The
     * string can't be longer than 1073741824 bytes (1 GB).
     *
     * @see Jedis#getSet(String, String)
     */
    public String getSet(final String key, final String value) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }


    /// Hashes ///

    /**
     * If key holds a hash, retrieve the value associated to the specified field.
     * <p>
     * If the field is not found or the key does not exist, a special 'nil' value is returned.
     * <p>
     * <b>Time complexity:</b> O(1)
     *
     * @param key   key
     * @param field field
     * @return Bulk reply
     */
    public String hget(final String key, final String field) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    /**
     * Retrieve the values associated to the specified fields.
     * <p>
     * If some of the specified fields do not exist, nil values are returned. Non existing keys are
     * considered like empty hashes.
     * <p>
     * <b>Time complexity:</b> O(N) (with N being the number of fields)
     *
     * @param key    key
     * @param fields fields
     * @return Multi Bulk Reply specifically a list of all the values associated with the specified
     * fields, in the same order of the request.
     */
    public List<String> hmget(final String key, final String... fields) {
        return execute(new JedisAction<List<String>>() {
            @Override
            public List<String> action(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }

    /**
     * Return all the fields and associated values in a hash.
     * <p>
     * <b>Time complexity:</b> O(N), where N is the total number of entries
     *
     * @param key
     * @return All the fields and values contained into a hash.
     */
    public Map<String, String> hgetAll(final String key) {
        return execute(new JedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    /**
     * @see Jedis#hset(String, String, String)
     */
    public Long hset(final String key, final String field, final String value) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    /**
     * @see Jedis#hmset(String, Map)
     */
    public String hmset(final String key, final Map<String, String> map) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.hmset(key, map);
            }
        });
    }

    /**
     * If the field already exists, false is returned, otherwise if a new field is created true is
     * returned.
     *
     * @see Jedis#hsetnx(String, String, String)
     */
    public boolean hsetnx(final String key, final String fieldName, final String value) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.hsetnx(key, fieldName, value) == 1;
            }
        });
    }

    /**
     * @see Jedis#hincrBy(String, String, long)
     */
    public Long hincrBy(final String key, final String fieldName, final long increment) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hincrBy(key, fieldName, increment);
            }
        });
    }

    /**
     * @see Jedis#hincrByFloat(String, String, double)
     */
    public Double hincrByFloat(final String key, final String fieldName, final double increment) {
        return execute(new JedisAction<Double>() {
            @Override
            public Double action(Jedis jedis) {
                return jedis.hincrByFloat(key, fieldName, increment);
            }
        });
    }

    /**
     * @see Jedis#hdel(String, String...)
     */
    public Long hdel(final String key, final String... fieldsNames) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hdel(key, fieldsNames);
            }
        });
    }

    /**
     * @see Jedis#hexists(String, String)
     */
    public Boolean hexists(final String key, final String fieldName) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.hexists(key, fieldName);
            }
        });
    }

    /**
     * @see Jedis#hkeys(String)
     */
    public Set<String> hkeys(final String key) {
        return execute(new JedisAction<Set<String>>() {
            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    /**
     * @see Jedis#hlen(String)
     */
    public Long hlen(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.hlen(key);
            }
        });
    }


    /**
     * hget命令，额外执行反序列化动作转为对象。存储为json字符串
     *
     * @see Jedis#hget(String, String)
     */
    public <T> T hgetBin(final String key, final String field, final Class<T> type) {
        return execute(new JedisAction<T>() {
            @Override
            public T action(Jedis jedis) {
                return jsonDecode(jedis.hget(key, field), type);
            }
        });
    }

    /**
     * hset命令，存储前额外执行序列化动作转为对象。存储为json字符串
     *
     * @see Jedis#hget(String, String)
     */
    public Long hsetBin(final String key, final String fieldName, final Object value) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.hset(key, fieldName, jsonEncode(value));
            }
        });
    }

    /**
     * hmset命令，存储前把对象序列化为json字符串
     *
     * @see Jedis#hget(String, String)
     */
    public String hmsetBin(final String key, final Map<String, Object> hash) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                Map<String, String> values = new HashMap<String, String>();
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    values.put(entry.getKey(), jsonEncode(entry.getValue()));
                }
                return jedis.hmset(key, values);
            }
        });
    }

    /**
     * hgetAll命令，额外执行反序列化动作转为对象。存储为json字符串
     *
     * @see Jedis#hgetAll(String)
     */
    public <T> Map<String, T> hgetAllBin(final String key, final Class<T> type) {
        Map<String, String> result = execute(new JedisAction<Map<String, String>>() {
            @Override
            public Map<String, String> action(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
        Map<String, T> resultList = new LinkedHashMap<String, T>();
        for (Map.Entry<String, String> entry : result.entrySet()) {
            resultList.put(entry.getKey(), jsonDecode(entry.getValue(), type));
        }
        return resultList;
    }

    /**
     * hvals命令封装，额外支持回源的区分。
     * <p>
     * 如果redis HASH里存储的只有一个值，且值为空字符串。表示该项目已回源过，数据为null。
     * 用于和未回源过的逻辑区分开
     *
     * @param key  key
     * @param type type
     * @param <T>  T
     * @return null=表示无任何数据（未回源），空list=回源的数据为空。过滤掉为空字符串的项目
     */
    public <T> List<T> hvalsBinSpec(final String key, final Class<T> type) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                List<String> list = jedis.hvals(key);
                if (list == null || list.size() == 0) {
                    return null;
                }
                List<T> result = new ArrayList<T>();
                if (list.size() == 1 && list.get(0).isEmpty()) {
                    return result;
                }
                for (String s : list) {
                    if (!s.isEmpty()) {
                        result.add(jsonDecode(s, type));
                    }
                }
                return result;
            }
        });
    }


    // / List Actions ///


    public Long lpush(final String key, final String... values) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.lpush(key, values);
            }
        });
    }

    public <T> List<T> listRangeBin(final String key, final Class<T> type, final long start, final long end) {
        return execute(new JedisAction<List<T>>() {
            @Override
            public List<T> action(Jedis jedis) {
                List<String> list = jedis.lrange(key, start, end);
                return fromRangListBin(list, type);
            }
        });
    }

    public Long listAddBin(final String key, final int time, final int maxSize, final Object... objects) {
        if (objects == null || objects.length == 0) {
            return 0L;
        }
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                final String[] jsonStringArray = objectsToJsonArrayReverse(objects);
                Long count = jedis.lpush(key, jsonStringArray);
                if (maxSize > 0) {
                    jedis.ltrim(key, 0, maxSize + 1L);
                }
                jedis.expire(key, time);
                return count;
            }
        });
    }

    /**
     * 创建空的队列
     *
     * @param key  key
     * @param time 超时时间
     */
    public void listCreate(final String key, final int time) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.lpush(key, "");
                jedis.expire(key, time);
            }
        });
    }

    public String rpop(final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.rpop(key);
            }
        });
    }

    public String brpop(final int timeout, final String key) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                List<String> nameValuePair = jedis.brpop(timeout, key);
                if (nameValuePair != null) {
                    return nameValuePair.get(1);
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * Not support for sharding.
     */
    public String rpoplpush(final String sourceKey, final String destinationKey) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.rpoplpush(sourceKey, destinationKey);
            }
        });
    }

    /**
     * Not support for sharding.
     */
    public String brpoplpush(final String source, final String destination, final int timeout) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.brpoplpush(source, destination, timeout);
            }
        });
    }

    public Long llen(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.llen(key);
            }
        });
    }

    public String lindex(final String key, final long index) {
        return execute(new JedisAction<String>() {

            @Override
            public String action(Jedis jedis) {
                return jedis.lindex(key, index);
            }
        });
    }

    public List<String> lrange(final String key, final int start, final int end) {
        return execute(new JedisAction<List<String>>() {

            @Override
            public List<String> action(Jedis jedis) {
                return jedis.lrange(key, start, end);
            }
        });
    }

    public void ltrim(final String key, final int start, final int end) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.ltrim(key, start, end);
            }
        });
    }

    public void ltrimFromLeft(final String key, final int size) {
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.ltrim(key, 0, size - 1L);
            }
        });
    }

    public Boolean lremFirst(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                Long count = jedis.lrem(key, 1, value);
                return (count == 1);
            }
        });
    }

    public Boolean lremAll(final String key, final String value) {
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                Long count = jedis.lrem(key, 0, value);
                return (count > 0);
            }
        });
    }

    // / Set Actions ///
    public Boolean sadd(final String key, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.sadd(key, member) == 1 ? true : false;
            }
        });
    }

    public Set<String> smembers(final String key) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.smembers(key);
            }
        });
    }

    /**
     * return true for add new element, false for only update the score.
     */
    public Boolean zadd(final String key, final double score, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.zadd(key, score, member) == 1 ? true : false;
            }
        });
    }

    public Double zscore(final String key, final String member) {
        return execute(new JedisAction<Double>() {

            @Override
            public Double action(Jedis jedis) {
                return jedis.zscore(key, member);
            }
        });
    }

    public Long zrank(final String key, final String member) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zrank(key, member);
            }
        });
    }

    public Long zrevrank(final String key, final String member) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zrevrank(key, member);
            }
        });
    }

    // / Ordered Set Actions ///

    public Long zcount(final String key, final double min, final double max) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }
        });
    }

    public Set<String> zrange(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrange(key, start, end);
            }
        });
    }

    public Set<Tuple> zrangeWithScores(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrangeWithScores(key, start, end);
            }
        });
    }

    public Set<String> zrevrange(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrevrange(key, start, end);
            }
        });
    }

    public Set<Tuple> zrevrangeWithScores(final String key, final int start, final int end) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrevrangeWithScores(key, start, end);
            }
        });
    }

    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        });
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return execute(new JedisAction<Set<String>>() {

            @Override
            public Set<String> action(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        });
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return execute(new JedisAction<Set<Tuple>>() {

            @Override
            public Set<Tuple> action(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        });
    }

    public Boolean zrem(final String key, final String member) {
        return execute(new JedisAction<Boolean>() {

            @Override
            public Boolean action(Jedis jedis) {
                return jedis.zrem(key, member) == 1 ? true : false;
            }
        });
    }

    public Long zremByScore(final String key, final double start, final double end) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    public Long zremByRank(final String key, final long start, final long end) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zremrangeByRank(key, start, end);
            }
        });
    }

    public Long zcard(final String key) {
        return execute(new JedisAction<Long>() {

            @Override
            public Long action(Jedis jedis) {
                return jedis.zcard(key);
            }
        });
    }


    /// HyperLogLog ///

    /**
     * 将除了第一个参数以外的参数存储到以第一个参数为变量名的HyperLogLog结构中.
     * 这个命令的一个副作用是它可能会更改这个HyperLogLog的内部来反映在每添加一个唯一的对象时估计的基数(集合的基数).
     * 如果一个HyperLogLog的估计的近似基数在执行命令过程中发了变化， PFADD 返回1，否则返回0，如果指定的key不存在，
     * 这个命令会自动创建一个空的HyperLogLog结构（指定长度和编码的字符串）.
     * 如果在调用该命令时仅提供变量名而不指定元素也是可以的，如果这个变量名存在，则不会有任何操作，如果不存在，则会创建一个数据结构（返回1）.
     *
     * @param key     key
     * @param element element
     * @return 如果 HyperLogLog 的内部被修改了,那么返回 1,否则返回 0
     */
    public long pfadd(final String key, final String element) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.pfadd(key, element);
            }
        });
    }

    /**
     * 当参数为一个key时,返回存储在HyperLogLog结构体的该变量的近似基数，如果该变量不存在,则返回0.
     * 当参数为多个key时，返回这些HyperLogLog并集的近似基数，这个值是将所给定的所有key的HyperLoglog结构合并到一个临时的HyperLogLog结构中计算而得到的.
     * HyperLogLog可以使用固定且很少的内存（每个HyperLogLog结构需要12K字节再加上key本身的几个字节）来存储集合的唯一元素.
     * 返回的可见集合基数并不是精确值， 而是一个带有 0.81% 标准错误（standard error）的近似值.
     * 例如为了记录一天会执行多少次各不相同的搜索查询， 一个程序可以在每次执行搜索查询时调用一次PFADD， 并通过调用PFCOUNT命令来获取这个记录的近似结果.
     * 注意: 这个命令的一个副作用是可能会导致HyperLogLog内部被更改，出于缓存的目的,它会用8字节的来记录最近一次计算得到基数,所以PFCOUNT命令在技术上是个写命令.
     *
     * @param key key
     * @return PFADD添加的唯一元素的近似数量.
     */
    public long pfadd(final String key) {
        return execute(new JedisAction<Long>() {
            @Override
            public Long action(Jedis jedis) {
                return jedis.pfcount(key);
            }
        });
    }

    /**
     * 将多个 HyperLogLog 合并（merge）为一个 HyperLogLog ， 合并后的 HyperLogLog 的基数接近于所有输入 HyperLogLog 的可见集合（observed set）的并集.
     * 合并得出的 HyperLogLog 会被储存在目标变量（第一个参数）里面， 如果该键并不存在， 那么命令在执行之前， 会先为该键创建一个空的.
     *
     * @param destkey    destkey
     * @param sourcekeys sourcekeys
     * @return 这个命令只会返回 OK.
     */
    public String pfmerge(final String destkey, final String... sourcekeys) {
        return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.pfmerge(destkey, sourcekeys);
            }
        });
    }

    /// OTHERS ///


    /**
     * 释放锁
     *
     * @param lockName 锁名
     */
    public void releaseLock(final String lockName) {
        del(lockName);
    }

    /**
     * 获取锁，默认锁超时时间10秒
     *
     * @param lockName 锁名
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName) {
        return acquireLock(lockName, DEFAULT_LOCK_EXPIRE_TIME);
    }

    /**
     * 获取锁
     *
     * @param lockName       锁名
     * @param lockExpireTime 超时时间，单位秒
     * @return 是否成功
     */
    public boolean acquireLock(final String lockName, final int lockExpireTime) {
        return set(lockName, "lock", "NX", "EX", lockExpireTime);
    }

    /**
     * Object序列化为Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    public String jsonEncode(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    /**
     * Json字符串反序列化为对象
     *
     * @param input Json字符串
     * @param type  目标对象类
     * @return 对象
     */
    public <T> T jsonDecode(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseObject(input, type);
    }

    /**
     * Json字符串（是个数组 [{},{}]）反序列化为对象列表
     *
     * @param input Json字符串
     * @param type  目标对象类
     * @return 对象列表
     */
    public <T> List<T> jsonDecodeArray(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseArray(input, type);
    }

    /**
     * 对象数组序列化为json字符串数组（每个对象转为一个json字符串）
     *
     * @param objects serial list
     * @return String[]
     */
    public String[] objectsToJsonArray(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[i]);
        }
        return result;
    }

    /**
     * 对象数组序列化为json字符串数组（每个对象转为一个json字符串）
     * 对象顺序反置
     *
     * @param objects serial list
     * @return String[]
     */
    public String[] objectsToJsonArrayReverse(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[length - i - 1]);
        }
        return result;
    }

    /**
     * 联合组成redis的实际key。 实际就是执行 first+second
     *
     * @param first  string1
     * @param second string2
     * @return key String
     */
    public String key(String first, String second) {
        return first + second;
    }

    /**
     * 联合组成redis的实际key.first+s1+:+s2
     *
     * @param key string1
     * @param s1  string2
     * @param s2  string3
     * @return
     */
    public String key(String key, String s1, String s2) {
        return key + s1 + ":" + s2;
    }

    /**
     * 联合组成redis的key。first+other[0]+:+other[1]+:+other[2]...
     *
     * @param first  first
     * @param others others
     * @return key String
     */
    public String key(String first, Object... others) {
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (Object other : others) {
            sb.append(other).append(":");
        }
        if (sb.charAt(sb.length() - 1) == ':') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    private <T> List<T> fromRangListBin(List<String> list, Class<T> type) {
        List<T> result = new ArrayList<T>();
        int size = list.size();
        if (size == 0) {
            return null;// NOSONAR
        }
        if (list.get(0).isEmpty()) {
            return result;
        }
        for (String s : list) {
            if (!s.isEmpty()) {
                result.add(jsonDecode(s, type));
            }
        }
        return result;
    }


    /**
     * Callback interface for template.
     */
    public interface JedisAction<T> {
        T action(Jedis jedis);
    }

    /**
     * Callback interface for template without result.
     */
    public interface JedisActionNoResult {
        void action(Jedis jedis);
    }

    /**
     * Callback interface for template.
     */
    public interface PipelineAction<T> {
        List<T> action(Pipeline Pipeline);
    }

    /**
     * Callback interface for template without result.
     */
    public interface PipelineActionNoResult {
        void action(Pipeline Pipeline);
    }
}
