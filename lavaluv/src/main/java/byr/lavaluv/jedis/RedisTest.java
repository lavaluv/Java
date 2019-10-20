package byr.lavaluv.jedis;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
/*
 * flushDB():清空当前数据库keys
 * exists(String k):判断k是否存在
 * set(String k,String v):设置k的v
 * setnx(String k,String v):如果k不存在，设置k的v
 * mset((String ... k, String ... v));设置多个k,v
 * append(String k,String v):在k的v后添加v
 * get(String k):获取k的v
 * mget(String[] keys):获取多个k,v 返回list
 * keys(String pattern):按pattern
 * del(String k) || del(String[] keys):
 * expire(String k, int sec):
 * ttl(String k)
 * persist(String k)
 * type(String k)
 */
public class RedisTest {
	Jedis jedis = new Jedis("localhost");
	@Test
	public void KeyOperate() 
	   { 
	       System.out.println("======================key=========================="+jedis.getDB()); 
	       // 清空数据 
	       System.out.println("清空库中所有数据："+jedis.flushDB());
	       // 判断key否存在 
	       System.out.println("判断key999键是否存在："+jedis.exists("key999")); 
	       System.out.println("新增key001,value001键值对："+jedis.set("key001", "value001")); 
	       jedis.append("key001", "add");
	       System.out.println("判断key001是否存在："+jedis.exists("key001"));
	       System.out.println("获取值key001:"+jedis.get("key001"));
	       // 输出系统中所有的key
	       System.out.println("新增key002,value002键值对："+jedis.set("key002", "value002"));
	       System.out.println("系统中所有键如下：");
	       Set<String> keys = jedis.keys("k*"); 
	       Iterator<String> it=keys.iterator() ;   
	       while(it.hasNext()){   
	           String key = it.next();   
	           System.out.println(key);   
	       }
	       // 删除某个key,若key不存在，则忽略该命令。
	       System.out.println("系统中删除key002: "+jedis.del("key002"));
	       System.out.println("判断key002是否存在："+jedis.exists("key002"));
	       // 设置 key001的过期时间
	       System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
	       try{ 
	           Thread.sleep(2000); 
	       } 
	       catch (InterruptedException e){ 
	       } 
	       // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
	       System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
	       // 移除某个key的生存时间
	       System.out.println("移除key001的生存时间："+jedis.persist("key001"));
	       System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
	       // 查看key所储存的值的类型
	       System.out.println("查看key所储存的值的类型："+jedis.type("key001"));
	       /*
	        * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
	        *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
	        */
	   }
}
