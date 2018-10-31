package org.leo.test.redistest;

import static org.hamcrest.core.IsEqual.equalTo;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.leo.test.redistest.cmd.RedisKeysCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisKeysCmdService {
	@Autowired
    private RedisKeysCmdService redisCmdService;

//    @Test
//    public void getOne() throws Exception {
//    	String key = "foo";
//        String value = redisCmdService.get(key);
//        Assert.assertNull(value);
//    }
	@Test
	public void setOne() throws Exception {
	  	String key = "Name";
	    String result = redisCmdService.set(key, "Test");
	    System.out.println("setOne(), result:" + result);
	    Assert.assertThat(result, equalTo("OK1"));
	}
    @Test
    public void getNotOne() throws Exception {
    	String key = "Name";
        String value = redisCmdService.get(key);
        Assert.assertNotNull(value);
    }
    
    @Test
    public void getTime() throws Exception {
    	BigInteger bi = new BigInteger("1355292000000");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Date:" + sd.format(new Date(bi.longValue())));
    }
    
    @Test
    public void getInfo() throws Exception {
//    	String info = redisCmdService.info();
    	String section = "Memory";//Clients
    	String info = redisCmdService.info(section);
    	System.out.println("Memory info:" + info);
    	section = "clients";//Clients
    	info = redisCmdService.info(section);
    	System.out.println("Clients info:" + info);
    }
    
    @Test
    public void getScan() throws Exception {
//    	String info = redisCmdService.info();
    	String cursor = "0";//Clients
    	ScanResult<String> sr = redisCmdService.scan(cursor);
//    	System.out.println("getCursor:"+sr.getCursor());
    	System.out.println("getStringCursor:"+sr.getStringCursor());
    	System.out.println("getResult size:"+sr.getResult().size());
    	for (String s : sr.getResult()) {
    		System.out.println("getResult:"+s);
    	}
    }
    @Test
    public void getScanParams() throws Exception {
//    	String info = redisCmdService.info();
    	String cursor = "0";//Clients
    	ScanParams params = new ScanParams();
		params.count(10);
		String pattern = "n*";//n?me/*
		params.match(pattern);
    	ScanResult<String> sr = redisCmdService.scanParams(cursor, params);
//    	System.out.println("getCursor:"+sr.getCursor());
    	System.out.println("getScanParams getStringCursor:"+sr.getStringCursor());
    	System.out.println("getScanParams getResult size:"+sr.getResult().size());
    	for (String s : sr.getResult()) {
    		System.out.println("getScanParams getResult:"+s);
    	}
    	
    }
}
