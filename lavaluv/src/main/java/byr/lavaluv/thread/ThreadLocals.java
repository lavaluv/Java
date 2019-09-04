package byr.lavaluv.thread;
/*
 * ThreadLocal提供了线程的局部变量，每个线程都可以通过set()和get()来对这个局部变量进行操作，但不会和其他线程的局部变量进行冲突，实现了线程的数据隔离
 * 
 * ThreadLocal的作用：
 * 管理数据库连接，保证同一线程链接的事务
 * 避免参数传递
 * 
 * 每个Thread维护着一个ThreadLocalMap的引用
   ThreadLocalMap是ThreadLocal的内部类，用Entry来进行存储
   调用ThreadLocal的set()方法时，实际上就是往ThreadLocalMap设置值，key是ThreadLocal对象，值是传递进来的对象
   调用ThreadLocal的get()方法时，实际上就是往ThreadLocalMap获取值，key是ThreadLocal对象
   ThreadLocal本身并不存储值，它只是作为一个key来让线程从ThreadLocalMap获取value。
 * 
 * 
 */

import java.sql.Connection;

public class ThreadLocals {
	private static ThreadLocal<Connection> local;
	public void init() {
		local = new ThreadLocal<Connection>();
	}
	public Connection setConnect() {
		Connection connection = null;
		local.set(connection);
		return connection;
	}
	public void connect() {
		Connection connection = local.get();
		//do
	}
}
