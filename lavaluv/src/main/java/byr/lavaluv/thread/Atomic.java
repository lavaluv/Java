package byr.lavaluv.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 比较并交换(compare and swap, CAS)，是原子操作的一种，可用于在多线程编程中实现不被打断的数据交换操作，
 * 从而避免多线程同时改写某一数据时由于执行顺序不确定性以及中断的不可预知性产生的数据不一致问题。 
 * 该操作通过将内存中的值与指定数据进行比较，当数值一样时将内存中的数据替换为新的值
 * 
 * cas有三个操作数：
 * 内存值V，旧的预期值A，新的值B
 * 当多个线程执行修改时，只有一个线程可以成功修改，其他线程则会告知竞争失败，什么都不做或重试（更新预期值，并执行）
 * cas存在ABA问题，即当一个线程修改A为B后，另一线程将B改回A，那么和第一个线程同时的线程是不知道的
 * 可以使用AtomicStampedReference和AtomicMarkableReference解决这个问题
 * 
 * Atomic包的类的实现绝大调用Unsafe的方法，而Unsafe底层实际上是调用C代码，C代码调用汇编，最后生成出一条CPU指令cmpxchg，完成操作。这也就为啥CAS是原子性的，因为它是一条CPU指令，不会被打断。
 */
public class Atomic {
	public static void main(String[] args)throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		Test test = new Test();
		for (int i = 0; i < 100; i++) {
			executorService.submit(()->{
				test.atomicAdd();
			});
		}
		executorService.shutdown();
		System.out.println(test.atomicGet());
	}
	static class Test{
		private int i = 0;
		private AtomicInteger j = new AtomicInteger(0);
		//加上synchronized即可保证线程安全
		public void add() {
			//不是原子操作，有读取、计算、赋值三步
			this.i += 1;
		}
		public int get() {
			return i;
		}
		public AtomicInteger atomicGet() {
			return j;
		}
		public void atomicAdd() {
			this.j.incrementAndGet();
		}
	}
}
