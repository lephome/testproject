package org.leo.test.redistest.cmd;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.leo.test.redistest.cmd.itf.IRedisHashCmdSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

@Service
public class RedisHashCmdService implements IRedisHashCmdSerive {
	private final static Logger log = LoggerFactory.getLogger(RedisHashCmdService.class);

	String redisIp = "192.168.254.128";
	int redisPort = 6379;
	String passwd = "leo123456";
	Jedis jedis;
	public RedisHashCmdService() {
		this.jedis = new Jedis(redisIp,redisPort);
		jedis.auth(passwd);
		jedis.echo("connect success!");
		log.info("jedis auth success!");
	}
//	@Override
//	public String get(String key) {
//		return jedis.get(key);
//	}
//	@Override
//	public String set(String key, String value) {
//		return jedis.set(key, value);
//	}
	
	public void hashcmd(String key, String value) {
		String field = "";
		Long result = 						jedis.hset(key, field, value);
		value = 							jedis.hget(key, field);
		result = 							jedis.hdel(key, field);
		Map<String, String> fieldKeyValue = jedis.hgetAll(key);
		boolean isExists = 					jedis.hexists(key, field);
		Set<String> fileds = 				jedis.hkeys(key);
		Long len = 							jedis.hlen(key);
		long incrValue = 0;
		long fieldValue = 					jedis.hincrBy(key, field, incrValue);
		double incrFloadV = 0;
		double incrFieldFloadV = 			jedis.hincrByFloat(key, field, incrFloadV);
		
		String rlt = 						jedis.hmset(key, fieldKeyValue);
		List<String> filedValueList = 		jedis.hmget(key, field);
		//If the field already exists, 0 is returned, otherwise if a new field is created 1 isreturned.
		result = 							jedis.hsetnx(key, field, value);
		filedValueList = 					jedis.hvals(key);
		String cursor = "";
		ScanResult<Entry<String, String>> s = jedis.hscan(key, cursor);
		
	}
	
	
}
