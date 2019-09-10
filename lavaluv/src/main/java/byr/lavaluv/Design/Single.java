package byr.lavaluv.Design;
/*
 * 一个类中能创建一个实例，所以称之为单例
 * 类的状态与对象无关。
 * 频繁创建对象、管理对象是一件耗费资源的事，创建一个对象来用就足够了！
 * 
 * 使用静态类.doSomething()体现的是基于对象，而使用单例设计模式体现的是面向对象。
 * 
 * 编写单例模式的代码其实很简单，就分了三步：
 * 将构造函数私有化
 * 在类的内部创建实例
 * 提供获取唯一实例的方法
 */
public class Single {
	static public void main(String[] args)throws Exception{
		Singleton.getInstance();
		InnerSingle.getInstance();
		System.out.println(WeekDay.Mon.getDay());
	}
	//如果未被使用，会浪费内存
	static public class Singleton{
		//private static Singleton singleton = new Singleton();
		private static Singleton singleton = null;
		private Singleton() {}
		//在多线程环境不安全，需加锁
		public static synchronized Singleton getInstance() {
			if (singleton == null) {
				return new Singleton();
			}
			return singleton;
		}
	}
	//使用内部类实现单例,初始化静态数据时，Java提供了的线程安全性保证
	static public class InnerSingle{
		private InnerSingle() {}
		static private class LazyLoad{
			private final static InnerSingle INSTANCE = new InnerSingle();
		}
		static public InnerSingle getInstance() {
			return LazyLoad.INSTANCE;
		}
	}
	//枚举实现单例
	public enum WeekDay { 
		//等于public static final
	     Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri( "Friday"), Sat("Saturday"), Sun("Sunday"); 
	     private final String day; 
	     private WeekDay(String day) { 
	            this.day = day; 
	     } 
	    public static void printDay(int i){ 
	       switch(i){ 
	           case 1: System.out.println(WeekDay.Mon); break; 
	           case 2: System.out.println(WeekDay.Tue);break; 
	           case 3: System.out.println(WeekDay.Wed);break; 
	            case 4: System.out.println(WeekDay.Thu);break; 
	           case 5: System.out.println(WeekDay.Fri);break; 
	           case 6: System.out.println(WeekDay.Sat);break; 
	            case 7: System.out.println(WeekDay.Sun);break; 
	           default:System.out.println("wrong number!"); 
	         } 
	     } 
	    public String getDay() { 
	        return day; 
	     } 
	}
}
