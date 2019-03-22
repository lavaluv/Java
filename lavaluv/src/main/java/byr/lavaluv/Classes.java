package byr.lavaluv;

public class Classes {
	public static void main(String args[])throws Exception {
//		Test test1 = new Test();
//		Test test2 = new Test();
		Father father = new Son(4, 5);
		father.getpri();
		father.getpro();
		father.getpub();
		Son son = new Son(4,5);
		son.getpro();
		son.getpub();//0
	}
}
class Test{
	/**
	 * 在类的加载过程中，类的static成员变量会被初始化，
	 * 另外，如果类中有static语句块，则会执行static语句块。
	 */
	static{
		System.out.println("Test Class Load");
	}
	public Test(){
		System.out.println("Test Constructor");
	}
	/**
	 * 在生成对象的过程中，会先初始化父类对象的成员变量》构造器，然后再执行子类成员变量》构造器。
	 * 也就是说类中的变量会在任何方法（包括构造器）调用之前得到初始化，即使变量散步于方法定义之间。
	 */
	Test2 test2 = new Test2();
}
class Test2{
	public Test2(){
		System.out.println("Test2 Constructor");
	}
}
class Father{
	/**
	 * 1）能够继承父类的public和protected成员变量；不能够继承父类的private成员变量
	 * 2）对于父类的包访问权限成员变量，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承
	 * 3）对于子类可以继承的父类成员变量，如果在子类中出现了同名称的成员变量，则会发生隐藏现象，
	 * 即子类的成员变量会屏蔽掉父类的同名成员变量。
	 * 如果要在子类中访问父类中同名成员变量，需要使用super关键字来进行引用
	 * 
	 * 1）能够继承父类的public和protected成员方法；不能够继承父类的private成员方法
	 * 2）对于父类的包访问权限成员方法，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承
	 * 3）对于子类可以继承的父类成员方法，如果在子类中出现了同名称的成员方法，则称为覆盖，
	 * 即子类的成员方法会覆盖掉父类的同名成员方法。
	 * 如果要在子类中访问父类中同名成员方法，需要使用super关键字来进行引用。
	 * 
	 * 子类是不能够继承父类的构造器，但是要注意的是，如果父类的构造器都是带有参数的，
	 * 则必须在子类的构造器中显示地通过super关键字调用父类的构造器并配以适当的参数列表。
	 * 如果是用在子类构造器中，则必须是子类构造器的第一个语句。
	 * 如果父类有无参构造器，则在子类的构造器中用super关键字调用父类构造器不是必须的，
	 * 如果没有使用super关键字，系统会自动调用父类的无参构造器。
	 */
	private int priData;
	protected int proData;
	public int pubData;
	public Father() {};
	public Father(int i,int j,int n) {
		this.priData = i;
		this.proData = j;
		this.pubData = n;
	}
	public void getpri() {
		System.out.println(this.priData);
	}
	public void getpro() {
		System.out.println(this.proData);
	}
	public void getpub() {
		System.out.println(this.pubData);
	}
}
class Son extends Father{
	public int pubData;
	public Son() {};
	public Son(int j,int n) {
		//super(0, j, n);显示调用父类构造器
		System.out.println("Son Constructor");
		this.proData = j;
		this.pubData = n;
	}
}
