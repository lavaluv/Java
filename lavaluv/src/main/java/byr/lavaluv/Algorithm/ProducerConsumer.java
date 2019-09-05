package byr.lavaluv.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
	private final List<Integer> list;
	private final int size;
	private  volatile boolean running = true;
	public ProducerConsumer(int size) {
		this.list = Collections.synchronizedList(new ArrayList<Integer>(size));
		this.size = size;
	}
	public void producer() {
		try {
			while (running) {
				Random random = new Random();
				//阻塞等待的功能
				while (list.size() == size) {
					synchronized (list) {
						System.out.println(Thread.currentThread().getName()+" producer waitting");
						Thread.sleep(random.nextInt(500));
						list.wait();
					}
				}
				synchronized (list) {
					if (list.size() < size) {
						int i = random.nextInt(500);
						list.add(i);
						System.out.println(Thread.currentThread().getName()+" produce "+i);
						System.out.println(Thread.currentThread().getName()+" listsize "+list.size());
						list.notifyAll();
					}
				}
				Thread.sleep(random.nextInt(500));
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	public void consumer() {
		try {
			while (running) {
				Random random = new Random();
				//阻塞等待的功能
				while (list.isEmpty()) {
					synchronized (list) {
						System.out.println(Thread.currentThread().getName()+" consumer waitting");
						Thread.sleep(random.nextInt(500));
						list.wait();
					}
				}
				synchronized (list) {
					if (!list.isEmpty()) {
						int i = list.remove(0);
						System.out.println(Thread.currentThread().getName()+" consume "+i);
						System.out.println(Thread.currentThread().getName()+" listsize "+list.size());
						list.notifyAll();
					}
				}
				Thread.sleep(random.nextInt(500));
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	public void stop() {
		this.running = false;
	}
	public static void main(String[] args)throws Exception{
		ProducerConsumer producerConsumer = new ProducerConsumer(10);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable pro1 = ()->producerConsumer.producer();
		Runnable pro2 = ()->producerConsumer.producer();
		Runnable pro3 = ()->producerConsumer.producer();
		Runnable con1 = ()->producerConsumer.consumer();
		Runnable con2 = ()->producerConsumer.consumer();
		Runnable con3 = ()->producerConsumer.consumer();
		executorService.execute(pro1);
		executorService.execute(pro2);
		executorService.execute(pro3);
		executorService.execute(con1);
		executorService.execute(con2);
		executorService.execute(con3);
		Thread.sleep(10000);
		producerConsumer.stop();
		Thread.sleep(2000);
		executorService.shutdown();
	}
}
