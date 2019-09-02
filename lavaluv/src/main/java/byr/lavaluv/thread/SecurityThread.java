package byr.lavaluv.thread;

import java.util.HashSet;
import java.util.Set;

/*
 * 发布(publish) 使对象能够在当前作用域之外的代码中使用,如public
   逸出(escape) 当某个不应该发布的对象被发布了
   常见的逸出有：
   静态域逸出，
   public修饰的get
   方法参数传递
   隐式的this
 *
 * 安全的发布对象的方法有： 
 * 在静态域中初始化（类加载时完成初始化，有jvm的同步机制）
 * 对象的引用应声明为volatile或atomicReferance
 * 由final修饰
 * 声明和使用时用synchronized等锁修饰
 * 
 * 保证线程安全的方法：
 * 无状态（没有共享变量：不使用成员变量，变量停留在方法作用域内）
 * 加锁（内置锁，lock锁）
 * 用final修饰引用变量，若引用变量引用了其他变量，则发布和使用时要加锁
 * 用java线程安全容器ConcurrentHashMap等
 * 使用原子类，Atomic类
 * 使用volatile声明，多用于标志位，使用前应保证修改变量不依赖于当前值，变量不用于不变性条件，访问变量时不需加锁:
   一旦变量修改，所有线程都得到最新的值
   保证修改之前的操作都已发生
   防止重排序
 */
public class SecurityThread {
	public SecurityThread() {};
	static class UnsecurityStatic{
		//在调用初始化之前，外部已经可以使用静态域的对象，且间接发布了SecurityThread对象
		public static Set<SecurityThread> securityThreads;
		private int privateInt;
		public void init() {
			securityThreads = new HashSet<SecurityThread>();
		}
		//外部可以任意调用
		public int getSecurityThread() {
			return privateInt;
		}
	}
}
