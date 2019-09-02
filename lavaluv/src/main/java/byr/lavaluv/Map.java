package byr.lavaluv;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Map集合的特点：
 * 键映射到值，一个映射不包含重复的键，一个键最多映射一个值
 * 
 * Map和collection的区别：
 * Map存储元素成对出现，键唯一，值可以重复
 * collection元素单独出现，set值唯一，list值可重复
 * 
 * HashMap(HashTable为线程安全,每个方法用synchronized修饰，效率低),ConcurrentHashMap,TreeMap,LinkedHashMap
 * 
 * HashMap:
 * 在散列表中有装载因子这么一个属性，当装载因子*初始容量小于散列表元素时，该散列表会再散列，扩容2倍！

	装载因子的默认值是0.75，无论是初始大了还是初始小了对我们HashMap的性能都不好

	装载因子初始值大了，可以减少散列表再散列(扩容的次数)，但同时会导致散列冲突的可能性变大(散列冲突也是耗性能的一个操作，要得操作链表(红黑树)！
	装载因子初始值小了，可以减小散列冲突的可能性，但同时扩容的次数可能就会变多！
	初始容量的默认值是16，它也一样，无论初始大了还是小了，对我们的HashMap都是有影响的：

	初始容量过大，那么遍历时我们的速度就会受影响~
	初始容量过小，散列表再散列(扩容的次数)可能就变得多，扩容也是一件非常耗费性能的一件事~
	从源码上我们可以发现：HashMap并不是直接拿key的哈希值来用的，它会将key的哈希值的高16位进行异或操作，使得我们将元素放入哈希表的时候增加了一定的随机性。
	
	如果key值为类，需要同时重写该类的hashCode()方法和它的equals()方法
 * 如果要使用线程安全的HashMap:
 * ConurrentHashMap 或
 * Map m = Collections.synchronizedMap(new HashMap(...));
 * 
 * LinkedHashMap:
 * 底层为散列表和双向链表
 * 遍历复杂度与初始容量无关，遍历的是内部双向链表
 * 
 * TreeMap:
 * 底层为红黑树
 * 自然有序，通过compare和compareTo判断key排序
 * 非线程同步
 */
public class Map {
	public static void main(String[] args)throws Exception{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 123);
		map.put(2, 123);
		System.out.println(map.containsKey(1));
		System.out.println(map.containsValue(1));
		Set<Entry<Integer, Integer>> set = map.entrySet();
		set.forEach(s->{
			System.out.println(s);
		});
		System.out.println("get k=1: "+map.get(1));
		Set<Integer> keySet = map.keySet();
		Collection<Integer> valueCollection = map.values();
		keySet.forEach(k->{
			System.out.println(k);
		});
		valueCollection.forEach(v->{
			System.out.println(v);
		});
		
		System.out.println("\nTreeMap:");
		TreeMap<Student, String> treeMap = new TreeMap<Map.Student, String>((Student s1,Student s2)->{
			int num = s1.getAge() - s2.getAge();
			int num2 = num == 0? s1.getName().compareTo(s2.getName()):num;
			return num2;
		});
		treeMap.put(new Student("hbq", 23), "some String");
		treeMap.put(new Student("wx", 22), "other");
		Set<Entry<Student, String>> set2 = treeMap.entrySet();
		set2.forEach(t -> {
			System.out.println(t);
		});
		
		System.out.println("\nConcurrentHashMap:");
		ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
	}
	static class Student{
		private String name;
		private int age;
		public Student(String name,int age) {
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
	}
}
