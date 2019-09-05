package byr.lavaluv;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.management.IntrospectionException;

/*
 * 注解其实就是代码中的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相对应的处理。
 * 即给方法和类注入信息
 * 基本注解：
 * @Overried,@Deprecated,@SuppressWarnning,@SafeVarargs,@FunctionalInterface
 * 
 * 自定义注解：
 * @Interface定义注解
 * 注解中可以拥有成员变量，String、数组、Class、枚举类、注解，写法与声明方法一样
 * 
 * 需要用反射reflection将注解的信息注入到方法中：
 * 反射出该类的方法
   通过方法得到注解上具体的信息
   将注解上的信息注入到方法上
 * 
 * 用于修饰注解的注解：
 * @Rentenction:用于指定注解被保留多长时间，value为RententionPolicy，有三个值SOURCE，CLASS(默认)，RUNTIME(可被反射获取)
 * @Target:用于指定注解用于修饰的对象类型
 * @Documented:被修饰的注解用于javadoc
 * @Inherited:被修饰的注解具有继承性，@A被Inherited修饰，@A修饰类B，C为B的子类，则C类也被@A修饰
 */
public class Annotation {
	public static void main(String[] args)throws Exception{
		//利用反射将注解注入到方法
		Class<Annotation> class1 = Annotation.class;
		Method method = class1.getMethod("test", String.class,int.class);
		MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
		
		String name = myAnnotation.name();
		int age = myAnnotation.age();
		
		Object object = class1.newInstance();
		method.invoke(object, name,age);
		test("a", 0);
		//利用反射将注解注入到对象中
		//1.使用内省【后边需要得到属性的写方法】，得到想要注入的属性
        PropertyDescriptor descriptor = new PropertyDescriptor("person", PersonDao.class);

        //2.得到要想注入属性的具体对象
        Person person = (Person) descriptor.getPropertyType().newInstance();

        //3.得到该属性的写方法【setPerson()】
        Method method2 = descriptor.getWriteMethod();

        //4.得到写方法的注解
        InjectPerson annotation = method2.getAnnotation(InjectPerson.class);

        //5.得到注解上的信息【注解的成员变量就是用方法来定义的】
        Method[] methods = annotation.getClass().getMethods();

        //6.将注解上的信息填充到person对象上

        for (Method m : methods) {

            //得到注解上属性的名字【age或name】
            String name2 = m.getName();

            //看看Person对象有没有与之对应的方法【setAge(),setName()】
            try {

                //6.1这里假设：有与之对应的写方法，得到写方法
                PropertyDescriptor descriptor1 = new PropertyDescriptor(name2, Person.class);
                Method method1 = descriptor1.getWriteMethod();//setAge(), setName()

                //得到注解中的值
                Object o = m.invoke(annotation, null);

                //调用Person对象的setter方法，将注解上的值设置进去
                method1.invoke(person, o);

            } catch (Exception e) {

                //6.2 Person对象没有与之对应的方法，会跳到catch来。我们要让它继续遍历注解就好了
                continue;
            }
        }
        
        //当程序遍历完之后，person对象已经填充完数据了

        //7.将person对象赋给PersonDao【通过写方法】
        PersonDao personDao = new PersonDao();
        method2.invoke(personDao, person);

        System.out.println(personDao.getPerson().getName());
        System.out.println(personDao.getPerson().getAge());
        
        //注入到成员变量
      //1.得到想要注入的属性
        Field field = PersonDao.class.getDeclaredField("person");

        //2.得到属性的具体对象
        Person person2 = (Person) field.getType().newInstance();

        //3.得到属性上的注解
        InjectPerson annotation2 = field.getAnnotation(InjectPerson.class);

        //4.得到注解的属性【注解上的属性使用方法来表示的】
        Method[] methods2 = annotation.getClass().getMethods();
        
        //5.将注入的属性填充到person对象上
        for (Method method3 : methods2) {

            //5.1得到注解属性的名字
            String name2 = method3.getName();
            
            try {
                //如果有
    			PropertyDescriptor descriptor2 = new PropertyDescriptor(name2, Person.class);

    			//得到Person对象上的写方法
    			Method method1 = descriptor2.getWriteMethod();

    			//得到注解上的值
    			Object o = method3.invoke(annotation2, null);

    			//填充person对象
    			method1.invoke(person2, o);
			} catch (Exception e) {
				continue;
			}
        }

        //循环完之后，person就已经填充好数据了


        //6.把person对象设置到PersonDao中
        PersonDao personDao2 = new PersonDao();
        field.setAccessible(true);
        field.set(personDao2, person2);

        System.out.println(personDao2.getPerson().getName());
		
	}
	@Retention(RetentionPolicy.RUNTIME)
	static @interface MyAnnotation{
		String name() default "no name";
		int age() default 0;
	}
	@Retention(RetentionPolicy.RUNTIME)
	static @interface MyAnnotation2{
		String value();
	}
	@Retention(RetentionPolicy.RUNTIME)
	static @interface InjectPerson{
		String name();
		int age();
	}
	@MyAnnotation2("qq")
	@MyAnnotation(name = "hbq",age = 12)
	static public void test(String name,int age) {
		System.out.println(name+" "+age);
	}
	static public class Person{
		private String name;
		private int age;
		public void setName(String name) {
			this.name = name;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}
	}
	static public class PersonDao{
		@InjectPerson(name = "ww",age = 2)
		private Person person;
		@InjectPerson(name = "hbq",age = 1)
		public void setPerson(Person person) {
			this.person = person;
		}
		public Person getPerson() {
			return person;
		}
	}
}
