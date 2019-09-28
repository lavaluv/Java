package byr.lavaluv;
/*
 * （1）string
       1，Stirng是对象不是基本数据类型 
       2，String是final类，不能被继承。是不可变对象，一旦创建，就不能修改它的值。 
       3，对于已经存在的Stirng对象，修改它的值，就是重新创建一个对象，然后将新值赋予这个对象 
 *（2）stringBuffer
　　    1，一个类似于 String 的字符串缓冲区，对它的修改的不会像String那样重创建对象。 
　　    2，使用append()方法修改Stringbuffer的值，使用toString()方法转换为字符串。 
 *（3）stringBuild
       是jdk1.5后用来替换stringBuffer的一个类，大多数时候可以替换StringBuffer。和StringBuffer的区别在于Stringbuild是一个单线程使用的类，不值执行线程同步所以比              
       StringBuffer的速度快，效率高。是线程非安全的。
 */

import org.junit.jupiter.api.Test;

public class Strings {
	@Test
	public void test() {
		String string = "sarings";
		String string2 = "string";
		System.out.println(string.length());
		System.out.println(string.concat(string2));
		System.out.println(string.compareTo(string2));
		System.out.println(string.matches("saring"));
		System.out.println(string.replaceAll("[s]", " "));
		System.out.println(string.replaceFirst("[s]", " "));
		System.out.println(string.split("s"));
		System.out.println(string.contains("sar"));
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(string);
	}
}
