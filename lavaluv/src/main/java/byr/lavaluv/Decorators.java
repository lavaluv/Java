package byr.lavaluv;

import org.junit.jupiter.api.Test;
/*
 * 有两种方法给一个类增加方法
 * 继承。继承一个父类。
 * 关联。在这个类中嵌入另一个类的对象。装饰器的思想
 * 
 * 第一种方式把父类所有的方法都继承了，较为臃肿，而且也不能再继承其他类了。
 * 而第二种方法可以有这个类来决定调用另一个类的什么方法，较为灵活，符合“开闭原则”。客户端可以根据具体需要添加具体构建类和具体装饰类，并进行组合。
 * 
 */
public class Decorators {
	//抽象构件，定义抽象方法
	static public interface Operation{
		public void test();
	}
	//具体构件，具体实现
	static public class ConcrentOperation implements Operation{
		public void test() {
			System.out.println("Concrent operation");
		}
	}
	//抽象装饰类，用于给具体构件添加功能
	static public class AbstartDecoratorOperation implements Operation{
		Operation operation;
		public AbstartDecoratorOperation(Operation operation) {
			this.operation = operation;
		}
		public void test() {
			operation.test();
		}
	}
	//具体装饰类，具体添加的功能实现
	static public class DecoratorOperation extends AbstartDecoratorOperation{
		public DecoratorOperation(Operation operation) {
			super(operation);
			// TODO Auto-generated constructor stub
		}

		public void test() {
			System.out.println("Decorator operation");
			operation.test();
		}
	}
	@Test
	public void testOperation() {
		ConcrentOperation concrentOperation = new ConcrentOperation();
		DecoratorOperation decoratorOperation = new DecoratorOperation(concrentOperation);
		decoratorOperation.test();
	}
}
