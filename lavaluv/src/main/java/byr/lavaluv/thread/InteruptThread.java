package byr.lavaluv.thread;

public class InteruptThread implements Runnable{
	private int i;
	public InteruptThread(int i) {
		this.i = i;
	}
	public void run() {
			try {
				while (i<1000) {
					Thread.sleep(500);
					System.out.println(i);
					i += 1;
				}
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().isAlive());
				//interrupt()阻塞的线程会清空标记位
				System.out.println(Thread.currentThread().isInterrupted());
				System.out.println("in Runnable");
				e.printStackTrace();
			}
	}
	public static void main(String[] args)throws Exception{
		InteruptThread interuptThread = new InteruptThread(0);
		Thread thread = new Thread(interuptThread);
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		thread.interrupt();
	}
}
