package org.leo.test.redistest.cmd;


import java.util.Set;

import org.leo.test.redistest.cmd.itf.IRedisSetCmdSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class RedisSetCmdService implements IRedisSetCmdSerive {
	private final static Logger log = LoggerFactory.getLogger(RedisSetCmdService.class);

	String redisIp = "192.168.254.128";
	int redisPort = 6379;
	String passwd = "leo123456";
	Jedis jedis;
	public RedisSetCmdService() {
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
	
	public void setcmd(String key, String value) {
		/*
		 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
		假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
		当 key 不是集合类型时，返回一个错误。
		 */
		Long setLen = 						jedis.sadd(key, value);
		setLen = 							jedis.scard(key);
		long index = 0;
		String key2 = "";
		Set<String> keydifList = 			jedis.sdiff(key,key2);
		
	}
	
	
}
