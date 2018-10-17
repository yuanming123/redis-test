package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import com.redis.test.entity.Person;
import com.redis.test.util.SerializeUtil;

import redis.clients.jedis.Jedis;

public class Test {
	
	private Jedis jedis;
	
	@Before
	public void before() {
		jedis = new Jedis();
	}

	@org.junit.Test
	public void testString() {
		jedis.set("name", "张三");
		System.out.println(jedis.get("name"));
		System.out.println("=================");
	}
	
	@org.junit.Test
	public void testMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("A", "小李");
		map.put("B", "小华");
		map.put("C", "小陈");
		jedis.hmset("map", map);
		//获取jedis的键和值
		System.out.println(jedis.hgetAll("map"));
		System.out.println("===================");
		//获取jedis的键
		System.out.println(jedis.hkeys("map"));
		System.out.println("====================");
		//获取jedis的值
		System.out.println(jedis.hvals("map"));
		System.out.println("=====================");
		//遍历jedis中的key
		Iterator<String> iterator = jedis.hkeys("map").iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		System.out.println("======================");
		//遍历jedis中的value
		Iterator<String> iteratorvalue = jedis.hvals("map").iterator();
		while(iteratorvalue.hasNext()) {
				System.out.print(iteratorvalue.next()+" ");
		}
		System.out.println("======================");
	}
	
	@org.junit.Test
	public void testList() {
		jedis.lpush("framework", "Spring");
		jedis.lpush("framework", "Struts");
		jedis.lpush("framework", "Mybatis");
		List<String> list = jedis.lrange("framework", 0, -1);
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		System.out.println("=======================");
	}
	
	@org.junit.Test
	public void testObject() {
		//将对象写入redis
		Person person = new Person(100,"刘德华");
		jedis.set("person".getBytes(), SerializeUtil.serialize(person));
		//取出redis中的对象
		byte[] personId = jedis.get("person".getBytes());
		Person person1 = (Person)SerializeUtil.unserialize(personId);
		System.out.println(person1.toString());
	}
}

