package org.leo.test.redistest.cmd.itf;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public interface IRedisStringCmdSerive {
	public String get(String key);
	public String set(String key, String value);
	Long del(String key);
	Boolean exists(String key);
	ScanResult<String> scan(String cursor);
	String info();
	String info(String section);
	ScanResult<String> scanParams(String cursor, ScanParams params);
	
}
