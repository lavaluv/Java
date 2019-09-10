package byr.lavaluv.Design;
/*
 * 定义一个操作中的算法框架，而将一些步骤延迟到子类中。使子类可以不改变一个算法的结构即可重定义该算法的某些步骤。
 * 
 * 优点：
 * 封装不变的部分，扩展可变的部分。把认为是不变的部分的算法封装到父类，可变部分的交由子类来实现！
 * 提取公共部分的代码，行为由父类控制，子类实现！
 * 缺点：
 * 抽象类定义了部分抽象方法，这些抽象的方法由子类来实现，子类执行的结果影响了父类的结果(子类对父类产生了影响)，会带来阅读代码的难度！
 */
public class Templete {
	//模板定义
	public abstract class CopyFile{
		//基本方法
		protected abstract void read();
		protected abstract void write();
		//模板方法
		public final void copy() {
			read();
			write();
		}
	}
	//具体模板类
	public class MyCopy extends CopyFile{
		//实现基本方法
		@Override
		protected void read() {
			System.out.println("My read");	
		}
		@Override
			protected void write() {
				System.out.println("My write");
		}
	}
	public class YourCopy extends CopyFile{
		@Override
		protected void read() {
			System.out.println("Your read");	
		}
		@Override
			protected void write() {
				System.out.println("Your write");
		}
	}
	static public void main(String[] args)throws Exception{
		Templete templete = new Templete();
		MyCopy myCopy = templete.new MyCopy();
		YourCopy yourCopy = templete.new YourCopy();
		myCopy.copy();
		yourCopy.copy();
	}
}
