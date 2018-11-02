package org.leo.test.redistest.cmd.itf;


public interface IRedisStringCmdSerive {
	public String get(String key);
	public String set(String key, String value);
	
	
}
