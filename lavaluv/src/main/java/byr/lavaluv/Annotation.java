package byr.lavaluv;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/*
 * 注解其实就是代码中的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相对应的处理。
 * 即给方法和类注入信息
 * 基本注解：
 * @Overried,@Deprecated,@SuppressWarnning,@SafeVarargs,@FunctionalInterface
 * 
 * 自定义注解：
 * @Interface定义注解
 * 注解中可以拥有成员变量，String、数组、Class、枚举类、注解，写法与声明方法一样
 * 
 * 需要用反射reflection将注解的信息注入到方法中：
 * 反射出该类的方法
   通过方法得到注解上具体的信息
   将注解上的信息注入到方法上
 * 
 */
public class Annotation {
	public static void main(String[] args)throws Exception{
		Class<Annotation> class1 = Annotation.class;
		Method method = class1.getMethod("test", String.class,int.class);
		MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
		String name = myAnnotation.name();
		int age = myAnnotation.age();
		Object object = class1.newInstance();
		method.invoke(object, name,age);
		test("a", 0);
	}
	@Retention(RetentionPolicy.RUNTIME)
	static @interface MyAnnotation{
		String name() default "no name";
		int age() default 0;
	}
	@Retention(RetentionPolicy.RUNTIME)
	static @interface MyAnnotation2{
		String value();
	}
	@MyAnnotation2("qq")
	@MyAnnotation(name = "hbq",age = 12)
	static public void test(String name,int age) {
		System.out.println(name+" "+age);
	}
}
