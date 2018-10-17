package com.redis.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 把对象进行序列化和反序列化
 * @author Administrator
 * @date 2018年10月16日
 */
public class SerializeUtil {
	
	public static byte[] serialize(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			//序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
