package byr.lavaluv;

import java.util.ArrayList;

/*
 * Java泛型设计原则：只要在编译时期没有出现警告，那么运行时期就不会出现ClassCastException异常.
 * 泛型：
 * 把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊的类型
 * 将类型当做参数传递
 * 只能是引用类型
 * 
 * 泛型的优势：
 * 省去类型强制转换
 * 程序健壮性强
 * 可读性和稳定性
 * 
 * 泛型类，泛型方法
 * 类上声明的泛型只对非静态成员有效
 * 泛型子类分为明确类型和不明确类型两种
 * 
 * List<Object>与List<String>没有继承关系
 * 若不清楚传入的List参数类型，可使用通配符List<?>
 * 集合的PECS原则：
 * 如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends) 可以将子类赋值给泛型集合
 * 如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super) 可以写入子类
 * 如果既要存又要取，那么就不要使用任何通配符。
 * 
 * 如果参数之间的类型有依赖关系，或者返回值是与参数之间有依赖关系的。那么就使用泛型方法
 * 如果没有依赖关系的，就使用通配符，通配符会灵活一些.
 * 
 * 泛型提供给编译器使用，生成的运行程序中没有泛型信息
 */
public class Genericity {
	public static void main(String[] args)throws Exception{
		show(new ArrayList<String>());
		showList(new ArrayList<Integer>());
		ArrayList<? extends Father> list;
		ArrayList<? super Son> list2;
		list = new ArrayList<Father>();
		list = new ArrayList<Son>();
		list2 = new ArrayList<Son>();
		//list.add(new Father());//error
		list2.add(new Son());
		//list2.add(new Father());//error
	}
	static class Test<T>{
		private T t;
		public T get() {
			return t;
		}
	}
	interface Test2<T>{
		public void show(T t);
	}
	public static <T> void show(ArrayList<T> t){
		System.out.println(t.getClass().toString());
	}
	public static void showList(ArrayList<?> list) {
		System.out.println(list.getClass().toString());
	}
	static class son extends Test<String>{
		private String t;
		@Override
		public String get() {
			return t;
		}
	}
	static class dauther<T> extends Test<T>{
		
	}
}
