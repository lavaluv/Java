package byr.lavaluv.Design;
/*
 * 门面模式：
 * 要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。
 * 
 * 优点：
 * 减少系统的相互依赖。使用门面模式，所有的依赖都是对门面对象的依赖，与子系统无关
 * 提高了灵活性。不管子系统内部如何变化，只要不影响门面对象，任你自由活动。
 * 缺点：
 * 不符合开闭原则，对修改关闭，对扩展开放。
 */
public class Facade {
	static public class TV{
		public void trunoff() {
			System.out.println("TV off");
		}
	}
	static public class PC{
		public void turnoff() {
			System.out.println("PC off");
		}
	}
	static public class ElectricBreaker{
		private PC pc = new PC();
		private TV tv = new TV();
		public void trunoffAll() {
			pc.turnoff();
			tv.trunoff();
		}
	}
	static public void main(String[] args)throws Exception{
		ElectricBreaker electricBreaker = new ElectricBreaker();
		electricBreaker.trunoffAll();
	}
}
