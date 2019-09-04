package byr.lavaluv.thread;
/*
 * 三种同步工具类：
 * CountDownLatch(闭锁)、CyclicBarrier(栅栏)、Semaphore(信号量)
 * 
 * CountDownLatch允许一个或多个线程等待其他线程完成
 * 线程调用await()等待count为零，其他线程调用countDown()减小count
 * 
 * CyclicBarrier允许一组线程互相等待，直到到达某个公共状态。
 * CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
 * CountDownLatch注重的是等待其他线程完成，CyclicBarrier注重的是：当线程到达某个状态后，暂停下来等待其他线程，所有线程均到达以后，继续执行
 * 
 * Semaphore(信号量)实际上就是可以控制同时访问的线程个数，它维护了一组"许可证"。
 * 当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
   当调用release()方法时，会添加一个许可证。
   这些"许可证"的个数其实就是一个count变量
 */

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThreadEtc {
	public static void main(String[] args)throws Exception{
		CountDownLatch countDownLatch = new CountDownLatch(5);
		new Thread(()->{
			try {
				countDownLatch.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(Thread.currentThread().getName()+" count 0");
		} ).start();
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				try {
					Thread.sleep(new Random().nextInt(500));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" count down");
				countDownLatch.countDown();
			}).start();
		}
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		for (int j = 0; j < 2; j++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+" start");
				try {
					Thread.sleep(new Random().nextInt(500));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" reach 1");
				try {
					cyclicBarrier.await();
					System.out.println(Thread.currentThread().getName() + " reach 2");
					Thread.sleep(new Random().nextInt(500));
					cyclicBarrier.await();
					System.out.println(Thread.currentThread().getName() + " reach 3");
				} catch (InterruptedException e2) {
					// TODO: handle exception
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
		Semaphore semaphore = new Semaphore(2);
		for (int k = 0; k < 5; k++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " get semaphore");
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName() + "release semaphore");
					semaphore.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} ).start();
		}
	}
}
