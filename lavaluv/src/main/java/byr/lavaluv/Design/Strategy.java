package byr.lavaluv.Design;
/*
 * 策略模式优点：
 * 算法可以自由切换
 * 改一下策略很方便
 * 扩展性良好
 * 增加一个策略，就多增加一个类就好了。
 * 
 * 缺点：
 * 策略类的数量增多
 * 每一个策略都是一个类，复用的可能性很小、类数量增多
 * 所有的策略类都需要对外暴露
 * 上层模块必须知道有哪些策略，然后才能决定使用哪一个策略
 */
public class Strategy {
	//策略定义
	public interface CodeStrategy{
		public void coding();
	}
	public class AgencyStrategy implements CodeStrategy{ 
		public void coding() {
			System.out.println("Agency Strategy");
		}
	}
	public class FactoryStrategy implements CodeStrategy{
		@Override
		public void coding() {
			System.out.println("Factory Strategy");
		}
	}
	//上下文，屏蔽具体策略
	public class StrategyUser{
		private CodeStrategy codeStrategy;
		public StrategyUser(CodeStrategy codeStrategy) {
			this.codeStrategy = codeStrategy;
		}
		public void programme() {
			codeStrategy.coding();
		}
	}
	static public void main(String[] args)throws Exception{
		Strategy strategy = new Strategy();
		StrategyUser user = strategy.new StrategyUser(strategy.new AgencyStrategy());
		StrategyUser user2 = strategy.new StrategyUser(strategy.new FactoryStrategy());
		user.programme();
		user2.programme();
	}
}
