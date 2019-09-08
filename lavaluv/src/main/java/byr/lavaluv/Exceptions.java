package byr.lavaluv;
/*
 * 在 Java 中，所有的异常都有一个共同的祖先 Throwable（可抛出）。Throwable 指定代码中可用异常传播机制通过 Java 应用程序传输的任何问题的共性。
 * Throwable： 有两个重要的子类：Exception（异常）和 Error（错误），二者都是 Java 异常处理的重要子类，各自都包含大量子类。
 * Error（错误）:是程序无法处理的错误，表示运行应用程序中较严重问题。大多数错误与代码编写者执行的操作无关，而表示代码运行时 JVM（Java 虚拟机）出现的问题。
   例如，Java虚拟机运行错误（Virtual MachineError），当 JVM 不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止。
 * 
 * Java的异常(包括Exception和Error)分为可查的异常（checked exceptions）和不可查的异常（unchecked exceptions）
 * 除了RuntimeException及其子类以外，其他的Exception类及其子类都属于可查异常。
 * 运行时异常：都是RuntimeException类及其子类异常，如NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，这些异常是不检查异常
 * 
 * 捕获异常：
 * 在方法抛出异常之后，运行时系统将转为寻找合适的异常处理器（exception handler）。
 * 潜在的异常处理器是异常发生时依次存留在调用栈中的方法的集合。
 * 当异常处理器所能处理的异常类型与方法抛出的异常类型相符时，即为合适的异常处理器。
 * 运行时系统从发生异常的方法开始，依次回查调用栈中的方法，直至找到含有合适异常处理器的方法并执行。
 * 当运行时系统遍历调用栈而未找到合适 的异常处理器，则运行时系统终止。同时，意味着Java程序的终止。
 * 
 * 一旦某个catch捕获到匹配的异常类型，将进入异常处理代码。一经处理结束，就意味着整个try-catch语句结束。其他的catch子句不再有匹配和捕获异常类型的机会。
 * 
 * finally 块：无论是否捕获或处理异常，finally块里的语句都会被执行。
 * 当在try块或catch块中遇到return语句时，finally语句块将在方法返回之前被执行。在以下4种特殊情况下，finally块不会被执行：
 * 1）在finally语句块中发生了异常。
 * 2）在前面的代码中用了System.exit()退出程序。
 * 3）程序所在的线程死亡。
 * 4）关闭CPU。
 * 
 * try、catch、finally语句块的执行顺序:
 * 1)当try没有捕获到异常时：try语句块中的语句逐一被执行，程序将跳过catch语句块，执行finally语句块和其后的语句；
 * 2)当try捕获到异常，catch语句块里没有处理此异常的情况：
 * 当try语句块里的某条语句出现异常时，而没有处理此异常的catch语句块时，此异常将会抛给JVM处理，finally语句块里的语句还是会被执行，但finally语句块后的语句*不会*被执行；
 * 3)当try捕获到异常，catch语句块里有处理此异常的情况：
 * 在try语句块中是按照顺序来执行的，当执行到某一条语句出现异常时，程序将跳到catch语句块，并与catch语句块逐一匹配，找到与之对应的处理程序，其他的catch语句块将*不会*被执行，
 * 而try语句块中，出现异常之后的语句也不会被执行，catch语句块执行完后，执行finally语句块里的语句，最后执行finally语句块后的语句；
 * 
 * . runtimeException子类:
    1、 java.lang.ArrayIndexOutOfBoundsException
    数组索引越界异常。当对数组的索引值为负数或大于等于数组大小时抛出。
    2、java.lang.ArithmeticException
    算术条件异常。譬如：整数除零等。
    3、java.lang.NullPointerException
    空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等
    4、java.lang.ClassNotFoundException
    找不到类异常。当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常。
   5、java.lang.NegativeArraySizeException  数组长度为负异常
   6、java.lang.ArrayStoreException 数组中包含不兼容的值抛出的异常
   7、java.lang.SecurityException 安全性异常
   8、java.lang.IllegalArgumentException 非法参数异常
 * 
 * IOException
 * IOException：操作输入流和输出流时可能出现的异常。
 * EOFException   文件已结束异常
 * FileNotFoundException   文件未找到异常
 * 
 * ClassCastException    类型转换异常类
ArrayStoreException  数组中包含不兼容的值抛出的异常
SQLException   操作数据库异常类
NoSuchFieldException   字段未找到异常
NoSuchMethodException   方法未找到抛出的异常
NumberFormatException    字符串转换为数字抛出的异常
StringIndexOutOfBoundsException 字符串索引超出范围抛出的异常
IllegalAccessException  不允许访问某类异常
InstantiationException  当应用程序试图使用Class类中的newInstance()方法创建一个类的实例，而指定的类对象无法被实例化时，抛出该异常
 */
public class Exceptions {
    public Exceptions() {  
    }  
  
    boolean testEx() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx1();  
        } catch (Exception e) {  
            System.out.println("testEx, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx, finally; return value=" + ret);  
            return ret;  
        }  
    }  
  
    boolean testEx1() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx2();  
            if (!ret) {  
                return false;  
            }  
            System.out.println("testEx1, at the end of try");  
            return ret;  
        } catch (Exception e) {  
            System.out.println("testEx1, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx1, finally; return value=" + ret);  
            return ret;  
        }  
    }  
  
    boolean testEx2() throws Exception {  
        boolean ret = true;  
        try {  
            int b = 12;  
            int c;  
            for (int i = 2; i >= -2; i--) {  
                c = b / i;  
                System.out.println("i=" + i);  
            }  
            return true;  
        } catch (Exception e) {  
            System.out.println("testEx2, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx2, finally; return value=" + ret);  
            //finally不应返回值，会使上一个函数无法捕获错误
            return ret;  
        }  
    }  
  
    public static void main(String[] args) {  
        Exceptions testException1 = new Exceptions();  
        try {  
            testException1.testEx();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}
