package byr.lavaluv.thread;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/*
 * Lock方式来获取锁支持中断、超时不获取、是非阻塞的
   提高了语义化，哪里加锁，哪里解锁都得写出来
   Lock显式锁可以给我们带来很好的灵活性，但同时我们必须手动释放锁
   支持Condition条件对象
   允许多个读线程同时访问共享资源
 * 
 * ReentrantLock,ReentrantReadWriteLock基于juc包中的abstractQueuedSychronizer(aqs同步器)实现
 * AQS其实就是一个可以给我们实现锁的框架
   内部实现的关键是：先进先出的队列、state状态（声明为volatile,用cas算法修改）
   定义了内部类ConditionObject
   拥有两种线程模式：独占模式，共享模式
   以模板方式将部分实现交由子类，如获取锁、释放锁
 * 
 * ReentrantLock支持公平锁，为互斥锁，一次只允许一个线程进入
 * ReentrantReadWriteLock为读写锁,写数据时为互斥，读数据时允许多个线程进入
 * 内部有读锁和写锁，当多线程操作多为读操作时，性能较好
 * 写锁可以获取读锁，读锁不能获取写锁
 * 写锁可以降为读锁，读锁不能升为写锁
 * 写锁支持条件对象
 * 
 * 使用读写锁修饰TreeMap的读写方法，可以提高并发性
 */
public class MoreLocks {
	private final ReentrantLock lock = new ReentrantLock();
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Object data;
	volatile boolean cachedState;
	public static void main(String[] args)throws Exception{
		MoreLocks.RWTreeMap<String, Integer> map = new RWTreeMap<String, Integer>();
		map.put("1", 1);
		TestThread<String, Integer> testThread = new TestThread<String, Integer>(map,true,"1",1);
		TestThread<String, Integer> testThread3 = new TestThread<String, Integer>(map,true,"1",1);
		TestThread<String, Integer> testThread2 = new TestThread<String, Integer>(map,false,"1",1);
		TestThread<String, Integer> testThread4 = new TestThread<String, Integer>(map,false,"1",1);
		testThread.start();
		testThread2.start();
		testThread3.start();
		testThread4.start();
	}
	public void test() {
		lock.lock();
		try {
			//access shared resources
		} finally {
			lock.unlock();
		}
	}
	public void cachedData() {
		//先获取读锁
		readWriteLock.readLock().lock();
		if (cachedState) {
			//在获取写锁前，先释放读锁
			readWriteLock.readLock().unlock();
			readWriteLock.writeLock().lock();
			try {
				//在获取数据前，先判断有没有线程先改变了状态，先进入写数据
				if (cachedState) {
					//data = 写数据
					cachedState = false;
				}
				//在释放写锁时，先降级为读锁
				readWriteLock.readLock().lock();
			} finally {
				readWriteLock.writeLock().unlock();
			}
		}
		try {
			//use data
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
	public static class RWTreeMap<K, V>{
		private final TreeMap<K, V> map = new TreeMap<K,V>();
		private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		private final ReadLock readLock = readWriteLock.readLock();
		private final WriteLock writeLock = readWriteLock.writeLock();
		public V get(K k) {
			readLock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+" get data");
				return map.get(k);
			} finally {
				readLock.unlock();
			}
		}
		public V put(K k,V v) {
			writeLock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+" put data");
				return map.put(k, v);
			} finally {
				writeLock.unlock();
			}
		}
	}
	static class TestThread<K, V> extends Thread{
		private final RWTreeMap<K, V> map;
		private boolean rw;
		private K k;
		private V v;
		public TestThread(RWTreeMap<K, V> map,boolean rw,K k,V v) {
			this.map = map;
			this.rw = rw;
			this.k = k;
			this.v = v;
		}
		public void run() {
			while (true) {
				if (rw) {
					map.get(k);
				}
				else {
					map.put(k,v);
				}
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
	}
}
