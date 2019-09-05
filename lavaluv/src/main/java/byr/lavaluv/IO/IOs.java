package byr.lavaluv.IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/*
 * java I/O 分为字节流和字符流，其中又分为输入和输出
 * 
 * 字节流输入：基类InputStream
 * ByteArrayInputStream	包含一个内部缓冲区，该缓冲区包含从流中读取的字节
 * PipedInputStream	和PipedOutputStream一起使用，实现多线程间的管道通信。
 * FilterInputStream 主要用途在于封装其他的输入输出流，为它们提供一些额外的功能。FilterInputStream的子类可进一步重写一些方法，并且还可以提供一些额外的方法和字段。
 * BufferedInputStream	用于为其他输入流提供缓冲功能。
 * DataInputStream	用来装饰其它输入流，以从底层输入流中读取基本Java数据类型。
 * FileInputStream 文件输入流，用于从文件系统中的某个文件中获得输入字节。FileInputStream用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用FileReader。
 * ObjectInputStream	对以前使用 ObjectOutputStream 写入的基本数据和对象进行反序列化
 * SequenceInputStream	串联输入流，将多个输入流转化为一个
 * StringBufferInputStream	将String转化为输入流
 * 
 * 字节流输出：基类OutputStream
 * ByteArrayOutputStream	数据被写入一个byte数组。缓冲区会随着数据的不断写入而自动增长。
 * PipedOutputStream	和PipedInputStream一起使用，实现多线程间的管道通信。
 * FilterOutputStream	主要用途在于封装其他的输入输出流，为它们提供一些额外的功能。FilterOutputStream的子类可进一步重写一些方法，并且还可以提供一些额外的方法和字段。
 * BufferedOutputStream	用于为其他输出流提供缓冲功能。
 * DataOutputStream	数据输出流允许应用程序以适当方式将基本Java数据类型写入输出流中。
 * PrintStream	为其他输出流提供打印功能。
 * FileOutputStream	文件输出流，用于将数据写入File或FileDescriptor的输出流。FileOutputStream用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用FileWriter。
 * ObjectOutputStream	将Java对象的基本数据类型和图形写入 OutputStream。
 * 
 * 字符流输入：基类Reader
 * CharArrayReader	用于读取字符数组，实现一个可用作字符输入流的字符缓冲区。
 * PipedReader	和PipedWriter一起实现线程间的通讯。
 * FilterReader	用于读取已过滤的字符流
 * BufferedReader	为另一个输入流添加缓冲功能。
 * InputStreamReader	是字节流通向字符流的桥梁
 * FileReader	用于对文件进行读取操作
 * 
 * 字符流输出：基类Writer
 * CharArrayWriter	实现了一个字符缓冲区。缓冲区会随向流中写入数据而自动增长。
 * PipedWriter	和PipedReader一起是通过管道实现线程间的通讯。
 * FilterWriter	用于写入已过滤的字符流。	
 * BufferedWriter	为另一个输出流添加缓冲功能。
 * OutputStreamWriter	是字符流通向字节流的桥梁
 * FileWriter	用于对文件进行写入操作。	
 * PrintWriter	为文本输出流提供打印功能。
 * 
 * https://blog.csdn.net/panweiwei1994/article/details/78046000
 */
public class IOs {
	/*
	 * 字节输入流必须提供返回下一个输入字节的read()方法。因为所有字节输入流的父类InputStream有这样一个抽象方法：public abstract int read()。
	 * ByteArrayInputStream 支持 mark/reset。
	 * ByteArrayInputStream的close方法无效，无法关闭此输入流。
	 */
	static public class ByteArrayInputStreamTest{
	    byte[] buf = new byte[] { 2, 15, 67, -1, -9, 9 };

	    // 构造方法和read()
	    // 15,67,255,247,
	    // ********************
	    // 2,15,67,255,247,9,
	    @Test
	    public void test1() {
	        ByteArrayInputStream bais = new ByteArrayInputStream(buf, 1, 4);
	        int b;
	        while ((b = bais.read()) != -1) {
	            System.out.print(b + ",");
	        }
	        System.out.println("\n********************");
	        bais = new ByteArrayInputStream(buf);
	        while ((b = bais.read()) != -1) {
	            System.out.print(b + ",");
	        }
	        System.out.println("");
	    }

	    // skip()
	    // 15,67,255,247,9,
	    @Test
	    public void test2() {
	        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
	        int b;
	        bais.skip(1);
	        while ((b = bais.read()) != -1) {
	            System.out.print(b + ",");
	        }
	    }

	    // available()
	    // 5，4，3，2，1，0，
	    @Test
	    public void test3() {
	        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
	        while (bais.read() != -1) {
	            System.out.print(bais.available() + "，");
	        }
	    }

	    // markSupported()
	    // true
	    // 2
	    // 2
	    // ******************
	    // 15
	    @Test
	    public void test4() {
	        // 2, 15, 67, -1, -9, 9
	        // 默认标记值为0
	        ByteArrayInputStream bais = new ByteArrayInputStream(buf);
	        System.out.println(bais.markSupported());
	        System.out.println(bais.read());
	        bais.reset();
	        System.out.println(bais.read());

	        System.out.println("******************");

	        // mark()的参数没有意义
	        bais.mark(4);
	        while (bais.read() != -1) {
	        }
	        bais.reset();
	        System.out.println(bais.read());

	    }
	}
	/*
	 * ByteArrayOutputStream中的数据被写入到一个byte数组里。byte数组会随着被写入其中的数据的增长而增长。
	 * 表示字节输出流的类必须提供至少一种可写入一个输出字节的方法。ByteArrayOutputStream提供了两种。
	 * ByteArrayOutputStream可以将缓冲区中的数据转化为byte数组或字符串并返回。
	 * ByteArrayOutputStream可以通过writeTo( OutputStream out)实现输出流之间数据的复制
	 * ByteArrayOutputStream 的close方法无效，无法关闭此输出流。
	 */
	static public class ByteArrayOutputStreamTest{
	    // write(int b)&&toByteArray()[]
	    // 0,1,2,
	    @Test
	    public void test1() {
	        int a = 0;
	        int b = 1;
	        int c = 2;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos.write(a);
	        baos.write(b);
	        baos.write(c);
	        byte[] buf = baos.toByteArray();
	        for (int i = 0; i < buf.length; i++)
	            System.out.print(buf[i] + ",");
	        System.out.println();
	    }

	    // write(byte b[], int off, int len)
	    // 15,67,-1,-9,
	    @Test
	    public void test2() {
	        byte[] buf = new byte[] { 2, 15, 67, -1, -9, 9 };
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos.write(buf, 1, 4);
	        buf = baos.toByteArray();
	        for (int i = 0; i < buf.length; i++)
	            System.out.print(buf[i] + ",");
	        System.out.println();
	    }

	    // writeTo(OutputStream out)
	    // 2,15,67,-1,-9,9,
	    // ***************
	    // 2,15,67,-1,-9,9,
	    @Test
	    public void test3() throws IOException {
	        byte[] buf = new byte[] { 2, 15, 67, -1, -9, 9 };
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos.write(buf);
	        buf = baos.toByteArray();
	        for (int i = 0; i < buf.length; i++)
	            System.out.print(buf[i] + ",");
	        System.out.println("\n***************");
	        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
	        baos.writeTo(baos2);
	        buf = baos2.toByteArray();
	        for (int i = 0; i < buf.length; i++)
	            System.out.print(buf[i] + ",");
	        System.out.println();
	    }

	    // reset()
	    // 2,15,67,-1,-9,9,
	    // ***************
	    // 0
	    @Test
	    public void test4() throws IOException {
	        byte[] buf = new byte[] { 2, 15, 67, -1, -9, 9 };
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos.write(buf);
	        buf = baos.toByteArray();
	        for (int i = 0; i < buf.length; i++)
	            System.out.print(buf[i] + ",");
	        System.out.println("\n*************");
	        baos.reset();
	        buf = baos.toByteArray();
	        System.out.println(buf.length);
	    }
	}
	
}
