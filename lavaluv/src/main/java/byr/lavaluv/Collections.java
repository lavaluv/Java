package byr.lavaluv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Collection:set,list
 * 集合只能存储引用类型(你存储的是简单的int，它会自动装箱成Integer)
 * Collection继承Iterable,子类以内部类来实现接口
 * 
 * List集合的特点就是：有序(存储顺序和取出顺序一致),可重复
 * ListIterator可以往前遍历，添加元素，设置元素
 * ArrayList 底层数据结构是数组。线程不安全
 * LinkedList底层数据结构是链表。线程不安全
 * Vector底层数据结构是数组。线程安全
 * 
 * Set集合的特点是：元素不可重复
 * HashSet集合A:底层数据结构是哈希表(是一个元素为链表的数组)
 * 散列表为每个对象计算出一个整数，称为散列码。根据这些计算出来的整数(散列码)保存在对应的位置上
 * TreeSet集合A:底层数据结构是红黑树(是一个自平衡的二叉树)B:保证元素的排序方式
 * LinkedHashSet集合A:：底层数据结构由哈希表和链表组成。
 */
public class Collections 
{
    public static void main( String[] args )
    {
    	//int需要装箱成Integer
    	ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1,2,3));
    	Iterator<Integer> iterator = arrayList.iterator();
    	ListIterator<Integer> listIterator = arrayList.listIterator();
        while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
        while (listIterator.hasNext()) {
        	listIterator.next();
		}
        while (listIterator.hasPrevious()) {
			Integer integer = (Integer) listIterator.previous();
			System.out.println(integer);
		}
    }
}

