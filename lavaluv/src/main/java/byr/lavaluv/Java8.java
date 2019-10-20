package byr.lavaluv;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

/*
 * default关键字:
 * 在java里面，我们通常都是认为接口里面是只能有抽象方法，不能有任何方法的实现的，
 * 那么在jdk1.8里面打破了这个规定，引入了新的关键字default，通过使用default修饰方法，可以让我们在接口里面定义具体的方法实现
 * 
 * Lambda 表达式：
 * 函数式编程语言里面函数也可以跟变量，对象一样使用了，也就是说函数既可以作为参数，也可以作为返回值了。
 * 
 * 函数式接口：
 * 只有函数式接口可以用lambda表达式，即只有一个"抽象"函数的接口，使用@FunctionInterface
 * 
 * 方法与构造函数引用：
 * 当你需要使用方法引用时，目标引用放在分隔符::前，方法的名称放在后面，即ClassName::methodName。
 * 
 * Date Api更新：
 * 
 * Stream：
 * 流是Java API的新成员，它允许我们以声明性方式处理数据集合（通过查询语句来表达，而不是临时编写一个实现）。
 * 就现在来说，我们可以把它们看成遍历数据集的高级迭代器。此外，流还可以透明地并行处理，也就是说我们不用写多线程代码了。
 */
public class Java8 {
	public interface javaInterface{
		public default void test() {
			System.out.println("new default");
		}
	}
	@FunctionalInterface
	public interface myInterface{
		public void test();
	}
	//这是常规的Collections的排序的写法，需要对接口方法重写
    public void test1(){
    List<String> list =Arrays.asList("aaa","fsa","ser","eere");
    Collections.sort(list, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    });
    for (String string : list) {
        System.out.println(string);
    }
}
//这是带参数类型的Lambda的写法
    public void testLamda1(){
    List<String> list =Arrays.asList("aaa","fsa","ser","eere");
    Collections.sort(list, (Comparator<? super String>) (String a,String b)->{
        return b.compareTo(a);
    }
    );
    for (String string : list) {
        System.out.println(string);
    }
}
//这是不带参数的lambda的写法
    public void testLamda2(){
    List<String> list =Arrays.asList("aaa","fsa","ser","eere");
    Collections.sort(list, (a,b)->b.compareTo(a)
    );
    for (String string : list) {
        System.out.println(string);
    }
    }
}
