package byr.lavaluv.Design;

import java.lang.reflect.Proxy;

/*
 * 代理模式就是：当前对象不愿意干的，没法干的东西委托给别的对象来做，只要做好本分的东西就好了！
 * 
 * 动态代理：
 * 代理对象拥有目标对象相同的方法【因为参数二指定了对象的接口，代理对象会实现接口的所有方法】
 * 用户调用代理对象的什么方法，都是在调用处理器的invoke方法。【被拦截】
 * 使用JDK动态代理必须要有接口【参数二需要接口】
 * 
 * 静态代理需要自己写代理类-->代理类需要实现与目标对象相同的接口
 * 而动态代理不需要自己编写代理类--->(是动态生成的)
 * 
 * 要实现动态代理必须要有接口的，动态代理是基于接口来代理的(实现接口的所有方法)，如果没有接口的话我们可以考虑cglib代理。
 * cglib代理也叫子类代理，从内存中构建出一个子类来扩展目标对象的功能！
 */
public class Agency {
	public interface Programmer{
		public void coding();
	}
	static public class SomePro implements Programmer{
		public void coding() {
			System.out.println("SomePro coding");
		}
	}
	//静态代理
	static public class ProAgency implements Programmer{
		private Programmer programmer;
		public ProAgency(Programmer programmer) {
			this.programmer = programmer;
		}
		public void coding() {
			programmer.coding();
			etcCoding();
		}
		//代理类自定义方法
		public void etcCoding() {
			System.out.println("ProAgency etcCoding");
		}
	}
	//透明代理
	static public class SomeProAgency implements Programmer{
		private Programmer programmer;
		public SomeProAgency() {
			this.programmer = new SomePro();
		}
		public void coding() {
			programmer.coding();
			etcCoding();
		}
		public void etcCoding() {
			System.out.println("SomeProAgency etcCoding");
		}
	}
	static public void main(String[] args)throws Exception{
		SomePro somePro = new SomePro();
		Programmer programmer = new ProAgency(somePro);
		programmer.coding();
		Programmer someProgrammer = new SomeProAgency();
		someProgrammer.coding();
		//动态代理
		Programmer dynamicProgrammer = (Programmer)Proxy.newProxyInstance(
				somePro.getClass().getClassLoader(), somePro.getClass().getInterfaces(), (proxy,method,arg)->{
            // 如果是调用coding方法，那么添加自定义方法
            if (method.getName().equals("coding")) {
                method.invoke(somePro, arg);
                System.out.println("dynamic etcCoding");
            } else {
                // 如果不是调用coding方法，那么调用原对象的方法
                return method.invoke(somePro, arg);
            }
            return null;
		});
		dynamicProgrammer.coding();
	}
}
