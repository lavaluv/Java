package byr.lavaluv.thread;
/*
 * java有两种锁，synchronized和lock
 * 都为非公平锁，请求可以插队获取锁
 * 
 * synchronized特性为：
 * 将修饰的代码块（方法）加锁，保证原子性和可见性
 * 互斥锁，一次只允许一个线程进入
 * 内置锁（java对象内都有内置锁/监视锁/锁标志）
 * 
 * 底层为monitor对象实现，标识哪个线程持有
 * 当修饰的代码执行完时，自动释放锁
 * 当抛出异常时，自动释放锁
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Locks extends Thread{
	public int i = 1;
	public static void main(String[] args)throws Exception{
		//实例不同，分别加锁，不冲突
		Locks locks = new Locks();
		Locks locks2 = new Locks();
		locks.start();
		locks2.start();
		Thread.sleep(3000);
		locks.i = 2;
	}
	public void run() {
		test1();
	}
	//使用类Locks的内置锁/对象锁
	public synchronized void test1() {
		while (i == 1) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void test2() {
		synchronized (this) {}
	}
	//使用Locks类锁,与对象锁不冲突
	public synchronized static void test3() {}
	//内置锁的可重入,线程已持有锁时，不需要再申请锁
	public class Test {
		public synchronized void dosomething() {};
	}
	public class Test2 extends Test{
		public synchronized void dosomething() {
			super.dosomething();
		}
	}
	//使用客户端锁,将list和方法耦合，调用方法时其他线程不可操作list
	public List<Integer> arrayList = Collections.synchronizedList(new ArrayList<Integer>());
	public void putIfAbsent(int i) {
		synchronized (arrayList) {
			if (!arrayList.contains(i)) {
				arrayList.add(i);
			}
		}
	}
	//错误使用,list的锁为list内置锁，不是Locks类的锁
	public List<Integer> arrayList2 = Collections.synchronizedList(new ArrayList<Integer>());
	public synchronized void putIfAbsent2(int i) {
		if (arrayList2.contains(i)) {
			arrayList2.add(i);
		}
	}
	//组合法(装饰器)加锁,传入list的引用，并用final修饰，当调用加锁的方法时，其他线程可以访问list
	class ImprovedList<E> implements List<E>{
		private final List<E> list;
		public ImprovedList(List<E> list){
			this.list = list;
		}
		public synchronized void putIfAbsent(E i) {
			if (!list.contains(i)) {
				list.add(i);
			}
		}
		@Override
		public int size() {
			return list.size();
		}
		@Override
		public boolean add(E e) {
			return list.add(e);
		}
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean addAll(Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public E set(int index, E element) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void add(int index, E element) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public E remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public int indexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int lastIndexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public ListIterator<E> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<E> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
