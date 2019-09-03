package byr.lavaluv.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * 线程池可以看做是线程的集合。
 * 在没有任务时线程处于空闲状态，当请求到来：线程池给这个请求分配一个空闲的线程，任务完成后回到线程池中等待下次任务(而不是销毁)。这样就实现了线程的重用。
 * 可以提供限定和管理线程的手段
 * 
 * 不用线程池的坏处：
 * 线程生命周期的开销非常高。每个线程都有自己的生命周期，创建和销毁线程所花费的时间和资源可能比处理客户端的任务花费的时间和资源更多，并且还会有某些空闲线程也会占用资源。
   程序的稳定性和健壮性会下降，每个请求开一个线程。如果受到了恶意攻击或者请求过多(内存不足)，程序很容易就奔溃掉了
 * 
 * java使用Executor框架提供线程池实现。
 * Executor将任务提交和执行分离解耦。
 * 常用类为ThreadPoolExecutor,ScheduledThreadPoolExecutor,ForkJoinPool
 * 
 * Callable比Runnable多了返回值和异常抛出
 * Future用于表示任务的生命周期，以及获取返回值
 * 
 * ThreadPoolExecutor用corePoolSize和MaximunPoolSize调整线程池大小
 * 如果运行的线程数小于corePoolSize，则创建新的线程来处理请求，即使有空闲的线程
 * 大于core小于max，则只有当排队队列满时创建新线程处理请求。
 * 如果core=max，则创建固定大小的线程池
 * 使用工厂方法创建新线程
 * 当线程数大于core且线程空闲时间大于keepAliveTime，则销毁
 * 
 * 三种排队策略：
 * 同步移交：直接移交给线程执行，如果没有线程执行者创建新线程，适用于无界限线程池
 * 无界限策略：当所有核心线程池都在运行时，新任务会进入无界限队列等待，没有新线程创建
 * 有界限策略：可以避免资源耗尽，但吞吐量较小
 * 
 * 拒绝请求：
 * 当线程数和队列满时，会拒绝请求，抛出异常
 * 四种拒绝策略：抛出异常，用调用者的线程执行任务，丢弃任务，丢弃最旧的任务
 * 
 * 线程的状态：
 * 以int的高三位标识：
 * 111 RUNNING：能够接受新任务，能够执行任务
 * 000 SHUTDOWN：不接受新任务，能执行已添加的任务
 * 001 STOP：不接受新任务，不执行已添加的任务，尝试中断正在执行的任务
 * 010 TIDYING：执行任务的数量为零时，由000和001转为010
 * 011 TERMINATED：线程池终止状态
 * 
 * 三种线程池实现：
 * newFixedThreadPool core==max的固定大小线程池
 * newCachedThreadPool 弹性线程池，请求没有空闲线程时，创建新线程
 * newSingleThreadPool 单线程
 * 
 * shutdown(),shutdownNow():
 * 前者状态转为SHUTDOWN，后者为STOP
 */
public class ThreadPool implements Callable<Integer>{
	private int i;
	public ThreadPool(int i) {
		this.i = i;
	}
	public Integer call()throws Exception{
		int k = 0;
		for (int i = 0; i < this.i; i++) {
			k += 1;
		}
		return k; 
	}
	public static void main(String[] args)throws InterruptedException,ExecutionException{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		Future<Integer> future = executorService.submit(new ThreadPool(200));
		Future<Integer> future2 = executorService.submit(new ThreadPool(300));
		
		System.out.println(future.get());
		System.out.println(future2.get());
		
		executorService.shutdown();
	}
}
