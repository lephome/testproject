package org.leo.test.redistest.cmd;


import java.util.List;

import org.leo.test.redistest.cmd.itf.IRedisStringCmdSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

@Service
public class RedisStringCmdService implements IRedisStringCmdSerive {
	private final static Logger log = LoggerFactory.getLogger(RedisStringCmdService.class);

	String redisIp = "192.168.254.128";
	int redisPort = 6379;
	String passwd = "leo123456";
	Jedis jedis;
	public RedisStringCmdService() {
		this.jedis = new Jedis(redisIp,redisPort);
		jedis.auth(passwd);
		jedis.echo("connect success!");
		log.info("jedis auth success!");
	}
	@Override
	public String get(String key) {
		return jedis.get(key);
	}
	@Override
	public String set(String key, String value) {
		return jedis.set(key, value);
	}
	/*
	 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾
	如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
	返回值:追加 value 之后， key 中字符串的长度。
	 */
	public Long append(String key, String value) {
		return jedis.append(key, value);
	}
	public Long bitcount(String key) {
		return jedis.bitcount(key);
	}
	public boolean setbit(String key, long offset, boolean value) {
		return jedis.setbit(key, offset, value);
	}
	public boolean setbit(String key, long offset, String value) {
		return jedis.setbit(key, offset, value);
	}
	public boolean getbit(String key, long offset) {
		return jedis.getbit(key, offset);
	}
	/*
	 public enum BitOP {
  AND, OR, XOR, NOT;
}
	 */
	public Long bitop(BitOP op, String destKey, String... srcKeys) {
		return jedis.bitop(op, destKey, srcKeys);
	}
	public List<Long> bitfield(String key, String... arguments) {
		return jedis.bitfield(key, arguments);
	}
	
	public Long incr(String key) {
		return jedis.incr(key);
	}
	public Long incrBy(String key,long integer) {
		return jedis.incrBy(key, integer);
	}
	public double incrByFloat(String key,double value) {
		return jedis.incrByFloat(key, value);
	}
	
	public Long decr(String key) {
		return jedis.decr(key);
	}
	
	public Long decrBy(String key,long integer) {
		return jedis.decrBy(key, integer);
	}
	/*
	 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
GETRANGE 通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求。
在 <= 2.0 的版本里，GETRANGE 被叫作 SUBSTR。
	 */
	public String getrange(String key,long startOffset,long endOffset) {
		return jedis.getrange(key, startOffset, endOffset);
	}
	/*
	 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
当 key 存在但不是字符串类型时，返回一个错误。
	 */
	public String getSet(String key,String value) {
		return jedis.getSet(key, value);
	}
	/*
	 返回所有(一个或多个)给定 key 的值。
如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。因此，该命令永不失败。
	 */
	public List<String> mget(String... keys) {
		return jedis.mget(keys);
	}
	public String mset(String... keysvalues) {
		return jedis.mset(keysvalues);
	}
	public Long msetnx(String... keysvalues) {
		return jedis.msetnx(keysvalues);
	}
	public String setex(String key, int seconds, String value) {
		return jedis.setex(key, seconds, value);
	}
	/*
	 将 key 的值设为 value ，当且仅当 key 不存在。
若给定的 key 已经存在，则 SETNX 不做任何动作。
SETNX 是『SET if Not eXists』(如果不存在，则 SET)的简写。
	 */
	public Long setnx(String key, String value) {
		return jedis.setnx(key, value);
	}
	/*
	 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。
	 */
	public String psetex(String key, long milliseconds, String value) {
		return jedis.psetex(key, milliseconds, value);
	}
	public Long setrange(String key, long offset, String value) {
		return jedis.setrange(key, offset, value);
	}
	public Long strlen(String key) {
		return jedis.strlen(key);
	}
}
