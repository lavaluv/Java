package byr.lavaluv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * ArrayList默认容量为10
 * 每次插入操作判断容量大小，超出则扩容capacity*1.5，用System.copyof实现
 * 插入和删除使用System.arraycopy实现
 * 删除元素不减少容量，需要trimToSize函数来减少
 * List<Integer> list = Collections.synchronizedList(arrayList)实现线程同步
 * 
 * Vector方法都是线程安全，有性能损失
 * 扩容时为capacity*2，占用内存
 * 
 * 查询多用ArrayList，增删多用LinkedList(极端情况下不绝对)
 */
public class Lists {
	public static void main(String args[])throws Exception{
		int i = 4;
		i |= i >>> 1;
		i |= i >>> 2;
		i |= i >>> 3;
		ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(1,2,3));
		List<Integer> list = Collections.synchronizedList(arrayList);
		System.out.println(i);
	}
}
