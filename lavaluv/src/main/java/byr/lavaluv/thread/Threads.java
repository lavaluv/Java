package byr.lavaluv.thread;
/*
 * 进程是系统进行资源分配和调度的独立单位。每一个进程都有它自己的内存空间和系统资源
 * 进程作为资源分配的基本单位
   线程作为资源调度的基本单位，是程序的执行单元，执行路径(单线程：一条执行路径，多线程：多条执行路径)。是程序使用CPU的最基本单位。
   线程基本状态：（新建）、就绪、执行、阻塞、（结束）
   在运行时调用sleep(Time)、join(Time!=0)会计时等待，等待结束后回到就绪状态
   调用yield()会让出执行权(不保证)，回到就绪装填
   调用join(0)会等该线程执行完毕后，再执行其他线程
   过时方法stop(Thread)会立即终止线程，不安全
   应用interrupt()代替，该方法不会终止线程，只是发送终止消息，需要接收的线程处理，当接收的线程处于阻塞状态会抛出错误
 *
 * 多线程的存在，不是提高程序的执行速度。其实是为了提高应用程序的使用率，程序的执行其实都是在抢CPU的资源，CPU的执行权
 * 
 * 并行：
	并行性是指同一时刻内发生两个或多个事件。
	并行是在不同实体上的多个事件

   并发：
	并发性是指同一时间间隔内发生两个或多个事件。
	并发是在同一实体上的多个事件
	
   由此可见：并行是针对进程的，并发是针对线程的。
   
 * java有两种实现多线程的方法：继承Thread，或是实现Runnable接口
 * run():仅仅是封装被线程执行的代码，直接调用是普通方法
   start():首先启动了线程，然后再由jvm去调用该线程的run()方法。
 * 一般选择实现Runnable接口，可以避免java中的单继承的限制。
   应该将并发运行任务和运行机制解耦，因此我们选择实现Runnable接口这种方式！
 *
 *  setPriority()可以设置线程优先级，高优先级的线程更可能获取cpu时间（不保证）
 *  优先级有1~10，默认为5
 *  不同操作系统对优先级的实现不同
 */
public class Threads {
	public static void main(String[] args)throws Exception{
		TestThread testThread = new TestThread(200);
		TestThread testThread2 = new TestThread(200);
		TestRun testRun = new TestRun(200);
		Thread thread = new Thread(testRun,"thred0");
		Runnable runnable = () -> task(200);
		
		testThread.setName("thread1");
		testThread2.setName("thread2");
		thread.setName("thread3");
		testThread.start();
		testThread2.start();
		thread.start();
		new Thread(runnable).start();
	}
	static class TestThread extends Thread {
		private int i;
		public TestThread(int i) {
			this.i = i;
		}
		public void run() {
			for (int i = 0; i < this.i; i++) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	static class TestRun implements Runnable{
		private int i;
		public TestRun(int i) {
			this.i = i;
		}
		public void run() {
			for (int i = 0; i < this.i; i++) {
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	static public void task(int k) {
		for (int i = 0; i < k; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
}
