package byr.lavaluv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import byr.lavaluv.Map.Student;

/**
 * 
 * ArrayList默认容量为10
 * 每次插入操作判断容量大小，超出则扩容capacity*1.5，用System.copyof实现
 * 插入和删除使用System.arraycopy实现
 * 删除元素不减少容量，需要trimToSize函数来减少
 * List<Integer> list = Collections.synchronizedList(arrayList)实现线程同步
 * 在方法内部加上synchronized
 * 
 * Vector方法都是线程安全，有性能损失，在方法声明时加上synchronized
 * 扩容时为capacity*2，占用内存
 * 
 * 以上两种线程安全的方法加锁粒度为”方法“，在遍历等操作是要加上synchronized
 * 推荐使用CopyOnWriteList,ConcurrentHashMap(cas算法，volatile声明),CopyOnWriteSet替代
 * CopyOnWrite保证数据的最终一致性，不保证数据实时一致性
 * 
 * 查询多用ArrayList，增删多用LinkedList(极端情况下不绝对)
 * Collections.sort()调用Arrays.sort(),Arrays.sort底层为归并排序
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
		ArrayList<Student> list2 = new ArrayList<Map.Student>();
		list2.add(new Student("hbq", 24));
		list2.add(new Student("ex", 20));
		for(Student student:list2) {
			System.out.println(student.getName()+student.getAge());
		}
		Collections.sort(list2, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				int i;
				i = o1.getAge() - o2.getAge();
				return i == 0?o1.getName().compareTo(o2.getName()):i;
			}
		});
		for(Student student:list2) {
			System.out.println(student.getName()+student.getAge());
		}
	}
}
