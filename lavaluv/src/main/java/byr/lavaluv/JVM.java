package byr.lavaluv;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

/*
 * javac 编译.java文件生成.class文件 java执行.class文件
 *  Java源码编译由以下三个过程组成：
 *  分析和输入到符号表
 *  注解处理
 *  语义分析和生成class文件
 *  
 *  词法分析 语法分析 语义分析 中间代码生成 中间代码优化 目标代码生成
 *  
 *  .class 文件不能直接运行，需要借助JVM,JVM运行在操作系统上，JDK区分不同操作系统
 *  一次编译，到处运行
 *  
 *  虚拟机规范则是严格规定了有且只有5种情况必须立即对类进行“初始化”(class文件加载到JVM中)：
 *  创建类的实例(new 的方式)。访问某个类或接口的静态变量，或者对该静态变量赋值，调用类的静态方法
 *  反射的方式
 *  初始化某个类的子类，则其父类也会被初始化
 *  Java虚拟机启动时被标明为启动类的类，直接使用java.exe命令来运行某个主类（包含main方法的那个类）
 *  当使用JDK1.7的动态语言支持时(....)
 *  
 *  Java类的加载是动态的，它并不会一次性将所有类全部加载后再运行，而是保证程序运行的基础类(像是基类)完全加载到jvm中，至于其他类，则在需要的时候才加载。这当然就是为了节省内存开销。
 *  Java默认有三种类加载器：
 *  1）Bootstrap ClassLoader：负责加载$JAVA_HOME中jre/lib/rt.jar里所有的class，由C++实现，不是ClassLoader子类
 *  2）Extension ClassLoader：负责加载java平台中扩展功能的一些jar包，包括$JAVA_HOME中jre/lib/ext/*.jar或-Djava.ext.dirs指定目录下的jar包
 *  3）App ClassLoader：负责记载classpath中指定的jar包及目录中class
 *  双亲委派模型。简单来说：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，如果父类加载失败则依次向下。
 *  防止内存中出现多份同样的字节码(安全性角度)
 *  类加载器在成功加载某个类之后，会把得到的 java.lang.Class类的实例缓存起来。下次再请求加载该类的时候，类加载器会直接使用缓存的类的实例，而不会尝试再次加载。
 *  
 *  加载器加载到jvm中，接下来其实又分了好几个步骤：
 *  加载，查找并加载类的二进制数据，在Java堆中也创建一个java.lang.Class类的对象。
 *  连接，连接又包含三块内容：验证、准备、解析。
    - 1）验证，文件格式、元数据、字节码、符号引用验证；
    - 2）准备，为类的静态变量分配内存，并将其初始化为默认值；
    - 3）解析，把类中的符号引用转换为直接引用
 * 初始化，为类的静态变量赋予正确的初始值。
 * 
 * JIT即时编辑器：
 * 就是把这些Java字节码重新编译优化，生成机器码，让CPU直接执行。这样编出来的代码效率会更高。
 * 编译也是要花费时间的，我们一般对热点代码做编译，非热点代码直接解析就好了。
 * 热点代码：多次调用的方法，循环体
 * 热点检测：计数器方式
 * 每个方法都有两类计数器，方法调用计数器，回边计数器
 * 
 * JVM内存结构
 * 堆：存放对象实例，几乎所有的对象实例都在这里分配内存，以及字符串常量池（存储类似“stirng”的字符串），运行时常量池（符号引用：类和接口的全名称，方法和字段的名称和描述符）
 * 虚拟机栈：虚拟机栈描述的是Java方法执行的内存结构：每个方法被执行的时候都会同时创建一个栈帧（Stack Frame）用于存储局部变量表、操作栈、动态链接、方法出口等信息
 * 本地方法栈：本地方法栈则是为虚拟机使用到的Native方法服务。
 * 方法区：存储已被虚拟机加载的类元数据信息(元空间)
 * 程序计数器：当前线程所执行的字节码的行号指示器
 * 
 * 例子：
 * 1、通过java.exe运行Java3yTest.class，随后被加载到JVM中，元空间存储着类的信息(包括类的名称、方法信息、字段信息..)。
 * 2、然后JVM找到Java3yTest的主函数入口(main)，为main函数创建栈帧，开始执行main函数
 * 3、main函数的第一条命令是Java3y java3y = new Java3y();就是让JVM创建一个Java3y对象，但是这时候方法区中没有Java3y类的信息，所以JVM马上加载Java3y类，把Java3y类的类型信息放到方法区中(元空间)
 * 4、加载完Java3y类之后，Java虚拟机做的第一件事情就是在堆区中为一个新的Java3y实例分配内存, 然后调用构造函数初始化Java3y实例，这个Java3y实例持有着指向方法区的Java3y类的类型信息（其中包含有方法表，java动态绑定的底层实现）的引用
 * 5、当使用java3y.setName("Java3y");的时候，JVM根据java3y引用找到Java3y对象，然后根据Java3y对象持有的引用定位到方法区中Java3y类的类型信息的方法表，获得setName()函数的字节码的地址
 * 6、为setName()函数创建栈帧，开始运行setName()函数
 * 
 * JVMGC回收：
 * 判断对象是否死去：
 * 引用计数法-->这种难以解决对象之间的循环引用的问题
 * 可达性分析算法-->主流的JVM采用的是这种方式
 * 
 * 回收算法：
 * 标记-清除算法:如它的名字一样，算法分为“标记”和“清除”两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象。
 * 复制算法:它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。
 * 标记-整理算法:标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存
 * 分代收集算法:把Java堆分为新生代(eden)和老年代(old)，这样就可以根据各个年代的特点采用最适当的收集算法。
 * 
 * 上面所讲的垃圾收集算法只能算是方法论，落地实现的是垃圾收集器：
 * Serial收集器，串行收集器是最古老，最稳定以及效率高的收集器，但可能会产生较长的停顿，只使用一个线程去回收。
 * ParNew收集器，ParNew收集器其实就是Serial收集器的多线程版本。
 * Parallel收集器，Parallel Scavenge收集器类似ParNew收集器，Parallel收集器更关注系统的吞吐量。
 * Parallel Old收集器，Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程“标记－整理”算法
 * CMS收集器，CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。它需要消耗额外的CPU和内存资源，在CPU和内存资源紧张，CPU较少时，会加重系统负担。CMS无法处理浮动垃圾。CMS的“标记-清除”算法，会导致大量空间碎片的产生。
 * G1收集器，G1 (Garbage-First)是一款面向服务器的垃圾收集器,主要针对配备多颗处理器及大容量内存的机器. 以极高概率满足GC停顿时间要求的同时,还具备高吞吐量性能特征。
 * 
 * 内存泄漏的原因很简单：
 * 对象是可达的(一直被引用)
 * 但是对象不会被使用
 * 
 * 内存泄露导致堆栈内存不断增大，从而引发内存溢出。
 * 大量的jar，class文件加载，装载类的空间不够，溢出
 * 操作大量的对象导致堆内存空间已经用满了，溢出
 * nio直接操作内存，内存过大导致溢出
 * 解决：
 * 查看程序是否存在内存泄漏的问题
 * 设置参数加大空间
 * 代码中是否存在死循环或循环产生过多重复的对象实体、
 * 查看是否使用了nio直接操作内存。
 */

public class JVM {
	public void StringTest(){
		String s1 = new String("1");//在堆中创建String("1")引用和常量池"1"引用
		s1.intern();//常量池中有，返回“1”
		String s2 = "1";//引用指向“1”
		System.out.println(s1==s2);
	}
	public static void main(String[] args) {
		String s1 = new String("1");//在堆中创建String("1")引用和常量池"1"引用
		s1.intern();//常量池中有，返回“1”
		String s2 = "1";//引用指向“1”
		System.out.println(s1==s2);
		//Test中为false
		String s3 = new String("1")+new String("1");//创建两个String("1")对象和String("11")对象，常量池为"1"
		s3.intern();//常量池没有，返回现在“11”的引用
		String s4 = "11";//指向String("11")
		System.out.println(s3==s4);
		
		String string = "ok";
		String newsString = new String("ok");
		System.out.println(string == newsString);
		//内存泄漏
		HashSet<Object> set = new HashSet<Object>();
		Object object = new Object();
		set.add(object);
		object = null;
		set.forEach(o->{
			System.out.println(o);
		});
	}
}
