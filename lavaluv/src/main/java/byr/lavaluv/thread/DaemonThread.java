package byr.lavaluv.thread;

import byr.lavaluv.thread.Threads.TestRun;
import byr.lavaluv.thread.Threads.TestThread;
/*
 * 守护线程是为其他线程服务的,如果服务对象消失，这守护线程也消失
 * 垃圾回收线程为守护线程
 * 
 * 在线程启动前可以设置为守护线程，setDaemon(boolean)
 * 守护线程不应访问共享资源(数据库，文件)
 * 守护线程中产生的新线程也为守护线程
 */
public class DaemonThread extends TestRun{
	public DaemonThread(int i) {
		super(i);
	}
	public static void main(String[] args)throws Exception{
		DaemonThread daemonThread = new DaemonThread(2000);
		Thread thread = new Thread(daemonThread,"thread1");
		TestThread testThread = new TestThread(20);
		thread.setDaemon(true);
		
		thread.start();
		testThread.start();
	}
}
