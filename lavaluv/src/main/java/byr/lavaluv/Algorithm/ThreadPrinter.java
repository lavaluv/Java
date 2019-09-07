package byr.lavaluv.Algorithm;

import java.util.concurrent.Semaphore;

import org.junit.Test;

public class ThreadPrinter {
	public void testSemaphore()throws Exception{
		Semaphore A = new Semaphore(1);
		Semaphore B = new Semaphore(0);
		Semaphore C = new Semaphore(0);
		SemaphorePrinter printer = new SemaphorePrinter();
		new Thread(()->printer.printA(A,B)).start();
		new Thread(()->printer.printB(B, C)).start();
		new Thread(()->printer.printC(C, A)).start();
	}
	@Test
	public void testSynchronized()throws Exception{
		SynchronizedPrinter synchronizedPrinter = new SynchronizedPrinter();
		new Thread(()->synchronizedPrinter.printA()).start();
		new Thread(()->synchronizedPrinter.printB()).start();
		new Thread(()->synchronizedPrinter.printC()).start();
	}
	static class SemaphorePrinter{
		public void printA(Semaphore now,Semaphore next) {
			while (true) {
				nextSemaphore("A", now, next);
			}
		}
		public void printB(Semaphore now,Semaphore next) {
			while (true) {
				nextSemaphore("B", now, next);
			}
		}
		public void printC(Semaphore now,Semaphore next) {
			while (true) {
				nextSemaphore("C", now, next);
			}
		}
		public void nextSemaphore(String out,Semaphore now,Semaphore next) {
			try {
				now.acquire();
				System.out.print(out);
				next.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static class SynchronizedPrinter{
		private volatile Boolean A = true;
		private volatile Boolean B = false;
		private volatile Boolean C = false;
		public void printA() {
			while (true) {
				if(A)
				synchronized (A) {
					if (A) {
						A = false;
						System.out.print("A");
						B = true;
					}
				}
			}
		}
		public void printB() {
			while (true) {
				if (B)
				synchronized (B) {
					if (B) {
						B = false;
						System.out.print("B");
						C = true;
					}
				}
			}
		}
		public void printC() {
			while (true) {
				if(C)
				synchronized (C) {
					if (C) {
						C = false;
						System.out.print("C");
						A = true;
					}
				}
			}
		}
	}
}
