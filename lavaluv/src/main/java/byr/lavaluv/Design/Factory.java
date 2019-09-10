package byr.lavaluv.Design;
/*
 * 创建对象变得简单而且修改对象时能很方便
 * 我们修改了具体的实现类，对客户端(调用方)而言是完全不用修改的。
 * 如果我们使用new的方式来创建对象的话，那么我们就说：new出来的这个对象和当前客户端(调用方)耦合了！也就是，当前客户端(调用方)依赖着这个new出来的对象！
 * 
 * 三种工厂模式：简单/静态工厂模式，工厂方法模式，抽象工厂模式
 * 1:客户端不需要在负责对象的创建,明确了各个类的职责
 * 2:如果有新的对象增加,只需要增加一个具体的类和具体的工厂类即可
 * 3:不会影响已有的代码,后期维护容易,增强系统的扩展性
 * 
 * 工厂方法模式的工厂是创建出一种产品，而抽象工厂是创建出一类产品。
 * 抽象工厂模式说到底就是多了一层抽象，减少了工厂的数量。
 */

public class Factory {
	//工厂模式
	public interface AnimalFactory{
		Animal creatAnimal();
	}
	public abstract class Animal{
		public void eat() {}
	}
	public class Dog extends Animal{
		public void eat() {
			System.out.println("Dog eat");
		};
	}
	public class Cat extends Animal{
		public void eat() {
			System.out.println("cat eat");
		};
	}
	public class DogFactory implements AnimalFactory{
		public Dog creatAnimal() {
			return new Dog();
		}
	}
	public class CatFactory implements AnimalFactory{
		public Cat creatAnimal() {
			return new Cat();
		}
	}
	//简单,静态工厂模式
	public class DogCatFactory{
		public Dog creatDog() {
			return new Dog();
		}
		public Cat creatCat() {
			return new Cat();
		}
		public Animal creatAnimal(String type) {
			if (type.equals(type)) {
				return new Cat();
			}else if(type.equals(type)){
				return new Dog();
			}
			return null;
		}
	}
	static public void main(String[] args)throws Exception{
		Factory dFactory = new Factory();
		DogFactory dogFactory = dFactory.new DogFactory();
		Dog dog = dogFactory.creatAnimal();
		dog.eat();
		DogCatFactory dogCatFactory = dFactory.new DogCatFactory();
		Cat cat = (Cat) dogCatFactory.creatAnimal("cat");
		cat.eat();
	}
}
