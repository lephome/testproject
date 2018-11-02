package org.leo.test.redistest.cmd;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.leo.test.redistest.cmd.itf.IRedisListCmdSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

@Service
public class RedisListCmdService implements IRedisListCmdSerive {
	private final static Logger log = LoggerFactory.getLogger(RedisListCmdService.class);

	String redisIp = "192.168.254.128";
	int redisPort = 6379;
	String passwd = "leo123456";
	Jedis jedis;
	public RedisListCmdService() {
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
	
	public void listcmd(String key, String value) {
		String field = "";
		//执行 LPUSH 命令后，列表的长度
		/*
		 将一个或多个值 value 插入到列表 key 的表头
如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
当 key 存在但不是列表类型时，返回一个错误。
		 */
		Long listLen = 						jedis.lpush(key, value);
		/*
		 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
		 */
		listLen = 						jedis.lpushx(key, value);
		long index = 0;
		//列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
		value = 							jedis.lindex(key, index);
		/*
		 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。

当 pivot 不存在于列表 key 时，不执行任何操作。

当 key 不存在时， key 被视为空列表，不执行任何操作。

如果 key 不是列表类型，返回一个错误。
		 */
		String pivot = "";
		listLen = 							jedis.linsert(key, LIST_POSITION.BEFORE, pivot, value);
		listLen = 							jedis.llen(key);
		/*
		 移除并返回列表 key 的头元素。
可用版本：
>= 1.0.0
时间复杂度：
O(1)
返回值：
列表的头元素。
当 key 不存在时，返回 nil 。
		 */
		String headValue = 					jedis.lpop(key);
		long start = 0;long end = 0;
		List<String> values = 				jedis.lrange(key, start, end);
		long count = 0;
		/*
		 根据参数 count 的值，移除列表中与参数 value 相等的元素。
count 的值可以是以下几种：
count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
count = 0 : 移除表中所有与 value 相等的值。
可用版本：
>= 1.0.0
时间复杂度：
O(N)， N 为列表的长度。
返回值：
被移除元素的数量。
因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
		 */
		Long len = 							jedis.lrem(key, count, headValue);
		long incrValue = 0;
		//操作成功返回 ok ，否则返回错误信息。
		String rlt = 						jedis.lset(key, index, headValue);
		//命令执行成功时，返回 ok 。
		rlt = 								jedis.ltrim(key, start, end);
		
		value = 							jedis.rpop(key);
		String srckey = null,dstkey = null;
		int timeout = 0;
		value = 							jedis.rpoplpush(srckey, dstkey);
		String tailValue = null;
		listLen = 							jedis.rpush(key, tailValue);
		//将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
//和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
		listLen = 							jedis.rpushx(key, tailValue);
		values = 							jedis.blpop(timeout, key);
		values = 							jedis.brpop(timeout, key);
		value = 							jedis.brpoplpush(srckey, dstkey, timeout);
	}
	
	
}
