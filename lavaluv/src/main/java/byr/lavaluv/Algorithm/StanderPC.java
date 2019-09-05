package byr.lavaluv.Algorithm;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class StanderPC {
	static public class Producer implements Runnable {

	    // true--->生产者一直执行，false--->停掉生产者
	    private volatile boolean isRunning = true;

	    // 公共资源
	    private final Vector sharedQueue;

	    // 公共资源的最大数量
	    private final int SIZE;

	    // 生产数据
	    private static AtomicInteger count = new AtomicInteger();

	    public Producer(Vector sharedQueue, int SIZE) {
	        this.sharedQueue = sharedQueue;
	        this.SIZE = SIZE;
	    }

	    @Override
	    public void run() {
	        int data;
	        Random r = new Random();

	        System.out.println("start producer id = " + Thread.currentThread().getId());
	        try {
	            while (isRunning) {
	                // 模拟延迟
	                Thread.sleep(r.nextInt(500));

	                // 当队列满时阻塞等待
	                while (sharedQueue.size() == SIZE) {
	                    synchronized (sharedQueue) {
	                        System.out.println("Queue is full, producer " + Thread.currentThread().getId()
	                                + " is waiting, size：" + sharedQueue.size());
	                        sharedQueue.wait();
	                    }
	                }

	                // 队列不满时持续创造新元素
	                synchronized (sharedQueue) {
	                    // 生产数据
	                    data = count.incrementAndGet();
	                    sharedQueue.add(data);

	                    System.out.println("producer create data:" + data + ", size：" + sharedQueue.size());
	                    sharedQueue.notifyAll();
	                }
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
				Thread.interrupted();
	        }
	    }

	    public void stop() {
	        isRunning = false;
	    }
	}
	static public class Consumer implements Runnable {
	    private volatile boolean isRunning = true;
	    // 公共资源
	    private final Vector sharedQueue;

	    public Consumer(Vector sharedQueue) {
	        this.sharedQueue = sharedQueue;
	    }

	    @Override
	    public void run() {

	        Random r = new Random();

	        System.out.println("start consumer id = " + Thread.currentThread().getId());
	        try {
	            while (isRunning) {
	                // 模拟延迟
	                Thread.sleep(r.nextInt(500));

	                // 当队列空时阻塞等待
	                while (sharedQueue.isEmpty()) {
	                    synchronized (sharedQueue) {
	                        System.out.println("Queue is empty, consumer " + Thread.currentThread().getId()
	                                + " is waiting, size：" + sharedQueue.size());
	                        sharedQueue.wait();
	                    }
	                }
	                // 队列不空时持续消费元素
	                synchronized (sharedQueue) {
	                    System.out.println("consumer consume data：" + sharedQueue.remove(0) + ", size：" + sharedQueue.size());
	                    sharedQueue.notifyAll();
	                }
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            Thread.currentThread().interrupt();
	        }
	    }
	    public void stop() {
	        isRunning = false;
	    }
	}
	public static void main(String[] args) throws InterruptedException {

        // 1.构建内存缓冲区
        Vector sharedQueue = new Vector();
        int size = 4;

        // 2.建立线程池和线程
        ExecutorService service = Executors.newCachedThreadPool();
        Producer prodThread1 = new Producer(sharedQueue, size);
        Producer prodThread2 = new Producer(sharedQueue, size);
        Producer prodThread3 = new Producer(sharedQueue, size);
        Consumer consThread1 = new Consumer(sharedQueue);
        Consumer consThread2 = new Consumer(sharedQueue);
        Consumer consThread3 = new Consumer(sharedQueue);
        service.execute(prodThread1);
        service.execute(prodThread2);
        service.execute(prodThread3);
        service.execute(consThread1);
        service.execute(consThread2);
        service.execute(consThread3);

        // 3.睡一会儿然后尝试停止生产者(结束循环)
        Thread.sleep(10 * 1000);
        prodThread1.stop();
        prodThread2.stop();
        prodThread3.stop();
        consThread1.stop();
        consThread2.stop();
        consThread3.stop();
        // 4.再睡一会儿关闭线程池
        Thread.sleep(3000);

        // 5.shutdown()等待任务执行完才中断线程(因为消费者一直在运行的，所以会发现程序无法结束)
        service.shutdown();


    }
}
