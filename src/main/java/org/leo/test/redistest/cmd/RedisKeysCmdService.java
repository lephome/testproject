package org.leo.test.redistest.cmd;


import java.util.Set;

import org.leo.test.redistest.cmd.itf.IRedisKeysCmdSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

@Service
public class RedisKeysCmdService implements IRedisKeysCmdSerive {
	private final static Logger log = LoggerFactory.getLogger(RedisKeysCmdService.class);

	String redisIp = "192.168.254.128";
	int redisPort = 6379;
	String passwd = "leo123456";
	Jedis jedis;
	public RedisKeysCmdService() {
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
	@Override
	public Long del(String key) {
		 return jedis.del(key);
	}
	@Override
	public Boolean exists(String key) {
		return jedis.exists(key);
	}
	public Set<String> keys(String pattern) {
		return jedis.keys(pattern);
	}
	
	public Long expire(String key, int seconds) {
		return jedis.expire(key, seconds);
	}
	/**
	 设置成功，返回 1
	key 不存在或设置失败，返回 0
	 */
	public Long pexpire(String key, long milliseconds) {
		return jedis.pexpire(key, milliseconds);
	}
	
	public Long expireAt(String key, long unixTime) {
		return jedis.expireAt(key, unixTime);
	}
	
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		return jedis.pexpireAt(key, millisecondsTimestamp);
	}
	
	public Long ttl(String key) {
		return jedis.ttl(key);
	}
	/**
	 毫秒为单位
	 */
	public Long pttl(String key) {
		return jedis.pttl(key);
	}
	/**
	 * 当生存时间移除成功时，返回 1 .
	 如果 key 不存在或 key 没有设置生存时间，返回 0 .
	 */
	public Long persist(String key) {
		return jedis.persist(key);
	}
	
	/**
	 从当前数据库返回一个随机的key
	 */
	public String randomKey() {
		return jedis.randomKey();
	}
	
	/**
	 将key重命名为newkey，如果key与newkey相同，将返回一个错误。如果newkey已经存在，则值将被覆盖
	 返回值：OK
	 */
	public String rename(String oldkey, String newkey) {
		return jedis.rename(oldkey, newkey);
	}
	/**
	 当且仅当 newkey 不存在时，将 key 改名为 newkey 。
当 key 不存在时，返回一个错误。

修改成功时，返回 1 。
如果 newkey 已经存在，返回 0 。
	 */
	public Long renamenx(String oldkey, String newkey) {
		return jedis.renamenx(oldkey, newkey);
	}
	
	/**
	 序列化
	 如果 key 不存在，那么返回 nil。</br> 否则，返回序列化之后的值。
	 */
	public byte[] dump(String key) {
		return jedis.dump(key);
	}
	/**
	 反序列化给定的序列化值，并将它和给定的 key 关联。

参数 ttl 以毫秒为单位为 key 设置生存时间；如果 ttl 为 0 ，那么不设置生存时间。

RESTORE 在执行反序列化之前会先对序列化值的 RDB 版本和数据校验和进行检查，如果 RDB 版本不相同或者数据不完整的话，那么 RESTORE 会拒绝进行反序列化，并返回一个错误。

如果键 key 已经存在， 并且给定了 REPLACE 选项， 那么使用反序列化得出的值来代替键 key 原有的值； 相反地， 如果键 key 已经存在， 但是没有给定 REPLACE 选项， 那么命令返回一个错误。
	 */
	public String restore(String key, int ttl, byte[] serializedValue) {
		return jedis.restore(key, ttl, serializedValue);
	}
	@Override
	public ScanResult<String> scan(String cursor) {
//		byte[] keys = new byte[1];
//		ScanResult r = jedis.scan(keys);
//		r.getResult();
////		jedis.scan(cursor, params)
//		jedis.scan(cursor);
//		ScanParams params = new ScanParams();
//		params.count(100);
//		String pattern = "";
//		params.match(pattern);
//		jedis.scan(cursor, params);
//		jedis.scan
		return jedis.scan(cursor);
	}
	@Override
	public ScanResult<String> scanParams(String cursor,ScanParams params) {
		return jedis.scan(cursor, params);
	}
	@Override
	public String info() {
		return jedis.info();
	}
	@Override
	public String info(String section) {
		return jedis.info(section);
	}
	/*
	 none (key不存在)
string (字符串)
list (列表)
set (集合)
zset (有序集)
hash (哈希表)
	 */
	public String type(String key) {
		return jedis.type(key);
	}
	public String select(int index) {
		return jedis.select(index);
	}
	/*
	 返回给定 key 引用所储存的值的次数。此命令主要用于除错
	 */
	public Long objectRefcount(String key) {
		return jedis.objectRefcount(key);
	}
	/*
	 返回给定 key 自储存以来的空闲时间(idle， 没有被读取也没有被写入)，以秒为单位。
	 */
	public Long objectIdletime(String key) {
		return jedis.objectIdletime(key);
	}
	/*
	 返回给定 key 锁储存的值所使用的内部表示(representation)。
	 */
	public String objectEncoding(String key) {
		return jedis.objectEncoding(key);
	}
//	public String migrate(String key) {
//		return jedis.migrate(host, port, key, destinationDb, timeout);
//	}
	
	
}
