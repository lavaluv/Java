package byr.lavaluv;

import java.util.ArrayList;

/*
 * Object是所有java对象的父亲，都有Object的方法
 * getClass():返回字节码文件对象
 * 
 * HashCode():默认返回由对象地址转换来的哈希地址，可重写
 * 同一个对象调用应返回同样的哈希值
 * 如果equals()相等，则HashCode()一定相同，equals()不相等，HashCode()也应该为不相等，有利于散列表性能
 * 
 * equals():判断传入的对象与调用对象是否相等
 * 具有自反性，对称性，传递性，一致性
 * 传入NULL则返回false
 * 默认实现为判断(==)两个对象的地址
 * 如果重写，则HashCode也需要重写，以维持约定
 * 一般需要重写以满足对象相等需求
 * 
 * toString():默认返回getClass()+"@"+Integer.toHexString(HashCode())的字符串
 * 所有子类都应重写该方法
 * 
 * clone():默认为protected方法，Object与子类不在一个包，子类需要实现Cloneable接口并提升为public，默认实现为super.clone()
 * x.clone() != x 一般不相同
 * x.clone().getClass() == x.getClass() 一般相同，不强求
 * x.clone().equals(x) 一般不相同，不强求
 * 如果类成员有可变的引用变量，如果全都拷贝，为深复制，否则为浅复制
 * 
 * wait(),notify(),notifyAll()：用于线程间的通讯，必须用于同步代码
 * 由锁对象调用，锁对象可以为任意对象，故这三个方法在Object类，而不是Thread类
 * notify()随机唤醒等待队列的线程，notifyAll()唤醒全部，唤醒的线程不会立即得到锁，而是等待notify的同步代码块执行完毕
 * wait()线程可以被中断、超时、notify和notifyAll唤醒
 * 调用wait()的线程会释放锁，被唤醒后回到调用wait时的状态
 * Thread.sleep()不会释放锁
 * 
 * finalize():在对象被内存回收前调用，但不定时，可能在调用后对象又不回收，一般不重写
 * 
 * obj instanceOf T:
 * 1、obj如果为null，则返回false；否则设S为obj的类型对象，剩下的问题就是检查S是否为T的子类型；
　　2、如果S == T，则返回true；
　　3、接下来分为3种情况，之所以要分情况是因为instanceof要做的是“子类型检查”，而Java语言的类型系统里数组类型、接口类型与普通类类型三者的子类型规定都不一样，必须分开来讨论。
　　①、S是数组类型：如果 T 是一个类类型，那么T必须是Object；如果 T 是接口类型，那么 T 必须是由数组实现的接口之一；
　　②、接口类型：对接口类型的 instanceof 就直接遍历S里记录的它所实现的接口，看有没有跟T一致的；
　　③、类类型：对类类型的 instanceof 则是遍历S的super链（继承链）一直到Object，看有没有跟T一致的。遍历类的super链意味着这个算法的性能会受类的继承深度的影响。
 * getClass():只能判断两个类是否严格相等(==)
 * 
 * 四种引用类型：
 * 强引用：大部分常用的对象为强引用
 * 软引用：非必须的对象，SoftRefference对象为软引用，当内存不足时会被回收
 * 弱引用：比软引用生命周期更短，WeakRefference对象，当执行GC回收时会被回收
 * 虚引用：只用于跟踪GC对象，PhantomRefference对象，必须和RefferenceQueue联合使用
 * 第一是可以让程序员通过代码的方式决定某些对象的生命周期；第二是有利于JVM进行垃圾回收。
 * 
 */
public class Objects {
	static public void out() {
		System.out.println("out");
	}
	public static void main(String[] args)throws Exception{
		String a = "";
		String b = "";
		Test test = new Test();
		boolean bo = a.hashCode() == b.hashCode();
		boolean ao = a.equals(b);
		System.out.println(bo);
		System.out.println(ao);
		System.out.println(test.clone() == test);
		System.out.println(test.clone().getClass() == test.getClass());
		System.out.println(test.clone().equals(test));
		System.out.println("_______");
		System.out.println(test.clone().getArrayList() == test.getArrayList());
		System.out.println(test.clone() == test);
		System.out.println(test.clone().getClass() == test.getClass());
		System.out.println(test.clone().equals(test));
		((Objects)null).out();
		Objects.out();
	}
	static class Test implements Cloneable{
		private ArrayList<String> list = new ArrayList<String>();
		public Test() {}
		public Test(ArrayList<String> list) {
			this.list = list;
		}
		@Override
		public Test clone() throws CloneNotSupportedException {
			// 默认返回Object,浅复制
			//return (Test) super.clone();
			//深复制
			Test test = (Test) super.clone();
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>)this.list.clone();
			test.list = list;
			return test;
		}
		public ArrayList<String> getArrayList(){
			return list;
		}
	}
}
