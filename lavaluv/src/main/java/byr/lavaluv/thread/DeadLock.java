package byr.lavaluv.thread;
/*
 * 死锁的条件：
 * 当前线程拥有其他线程需要的资源
   当前线程等待其他线程已拥有的资源
   都不放弃自己拥有的资源
 * 
 * 死锁的种类：
 * 锁顺序死锁：两线程以不同顺序获取相同的锁
 * 动态锁顺序死锁：
 * 协作对象之间发生死锁：两对象之间同时需要获取两个对象的锁，在调用某个方法时需要持有锁，并且在方法内部也调用了其他带锁的方法
 * 
 * 避免死锁：
 * 固定加锁顺序
 * 开放调用：调用方法时不需要持有锁，同步代码块只包括涉及共享操作的代码
 * 锁定时，使用显示lock，tryLock()
 * 
 * 死锁检测：jconsole,jstack
 */
public class DeadLock {
	private final Object object1 = new Object();
	private final Object object2 = new Object();
	public static void main(String[] args)throws Exception{
	 DeadLock deadLock = new DeadLock();
	 //锁顺序死锁
	 Thread thread = new Thread(()->{
		synchronized (deadLock.object1) {
			System.out.println(Thread.currentThread().getName()+" get 1");
			synchronized (deadLock.object2) {
				System.out.println(Thread.currentThread().getName()+" get 2");
			}
		} 
	 });
	 Thread thread2 = new Thread(()->{
		synchronized (deadLock.object2) {
			System.out.println(Thread.currentThread().getName()+" get 2");
			synchronized (deadLock.object1) {
				System.out.println(Thread.currentThread().getName()+" get 1");
			}
		} 
	 });
//	 thread.start();
//	 thread2.start();
	 //动态锁顺序死锁
	 Thread thread3 = new Thread(()-> deadLock.fromTo(deadLock.object1, deadLock.object2));
	 Thread thread4 = new Thread(()-> deadLock.fromTo(deadLock.object2, deadLock.object1));
	 thread3.start();
	 thread4.start();
	}
	public void fromTo(Object from,Object to) {
		//--
		final Object objectLock = new Object();
		final int fromHash = System.identityHashCode(from);
		final int toHash = System.identityHashCode(to);
		//固定加锁顺序
		if (fromHash > toHash) {
			synchronized (from) {
				System.out.println(Thread.currentThread().getName()+" get from");
				synchronized (to) {
					System.out.println(Thread.currentThread().getName()+" get to");
				}
			}
		}
		else if (fromHash < toHash) {
			synchronized (to) {
				System.out.println(Thread.currentThread().getName()+" get to");
				synchronized (from) {
					System.out.println(Thread.currentThread().getName()+" get from");
				}
			}
		}
		else {
			synchronized (objectLock) {
				System.out.println(Thread.currentThread().getName()+" get lock");
				synchronized (to) {
					System.out.println(Thread.currentThread().getName()+" get to");
					synchronized (from) {
						System.out.println(Thread.currentThread().getName()+" get from");
					}
				}
			}
		}
	}
}
