package byr.lavaluv.IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

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
 * Reader与InputStream的不同：
 * 操作对象的不同。字节流操作字节、字符操作字符。
 * 实现的接口不同。Reader比InputStream多实现了一个Readable接口，用于提供一个可以将字符写入到指定缓存数组的方法。
 * close方法不同。Reader的close方法是抽象的、子类必须重写。 InputStream的close方法则不是抽象的。
 * 
 * Writer与OutputStream区别
 * 操作对象的不同。字节流操作字节、字符操作字符。
 * 实现的接口不同。Writer相比与OutputStream多实现了一个Appendable接口、用于提供几个向此流中追加字符的方法。
 * close、flush方法不同。Writer的close、flush方法都是抽象的，而OutputStream则不是。
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
	/*
	 * PipedInputStream与PipedOutputStream分别为管道输入流和管道输出流。管道输入流通过连接到管道输出流实现了类似管道的功能，用于线程之间的通信。
	 * 通常，由某个线程向管道输出流中写入数据。根据管道的特性，这些数据会自动发送到与管道输出流对应的管道输入流中。这时其他线程就可以从管道输入流中读取数据，这样就实现了线程之间的通信。
	 * 不建议对这两个流对象尝试使用单个线程，因为这样可能死锁线程。
	 * PipedOutputStream是数据的发送者；PipedInputStream是数据的接收者。
	 * PipedInputStream缓冲区大小默认为1024，写入数据时写入到这个缓冲区的，读取数据也是从这个缓冲区中读取的。
	 * PipedInputStream通过read方法读取数据。PipedInputStream通过write方法写入数据，write方法其实是调用PipedInputStream中的receive方法来实现将数据写入缓冲区的的，因为缓冲区是在PipedInputStream中的。
	 * PipedOutputStream和PipedInputStream之间通过connect()方法连接。
	 * 使用后要关闭输入输出流。
	 * 一个输出管道只能连接一个输入管道
	 */
	static public class PipedInputStreamTest{
		/**
		 * 发送者线程
		 */
		public class Sender extends Thread {

		    private PipedOutputStream out = new PipedOutputStream();

		    public PipedOutputStream getOutputStream() {
		        return out;
		    }

		    @Override
		    public void run() {
		        writeMessage();
		    }

		    // 向管道输出流中写入信息
		    private void writeMessage() {
		        String strInfo = "Hello World!";
		        try {
		            // 向管道输入流中写入数据
		            out.write(strInfo.getBytes());
		            // 释放资源
		            out.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		/**
		 * 接收者线程
		 */
		public class Receiver extends Thread {
		    private PipedInputStream in = new PipedInputStream();

		    public PipedInputStream getInputStream() {
		        return in;
		    }

		    @Override
		    public void run() {
		        readMessage();
		    }

		    // 从管道输入流中读取数据
		    public void readMessage() {
		        byte[] buf = new byte[1024];
		        try {
		            //从缓冲区中读取数据到buf中
		            int len = in.read(buf);
		            //打印读取到的内容
		            System.out.println(new String(buf, 0, len));
		            //释放资源
		            in.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		/**
		 * 测试类
		 */
		@Test
		public void testPip() {
			Sender sender = new Sender();
		    Receiver receiver = new Receiver();
		    PipedOutputStream out = sender.getOutputStream();
	        PipedInputStream in = receiver.getInputStream();
		    try {
		        // 连接输入流和输出流。下面两条语句的效果是一样。
		        // out.connect(in);
		        in.connect(out);
		        sender.start();
		        receiver.start();
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
	}
	/*
	 * FilterInputStream、FilterOutputStream是过滤器字节输入输出流。它们的主要用途在于封装其他的输入输出流，为它们提供一些额外的功能。
	 * FilterInputStream、FilterOutputStream并没有提供什么装饰功能。FilterInputStream、FilterOutputStream的子类可进一步重写这些方法中的一些方法，来提供装饰功能。
	 * FilterInputStream装饰功能的实现的关键在于类中有一个InputStream字段，依赖这个字段它才可以对InputStream的子类提供装饰功能。FilterOutputStream也是如此。
	 */
	static public class FilterInputStreamTest{}
	/*
	 * BufferedInputStream是缓冲输入流，作用是为另一个输入流添加一些功能，比如缓冲输入功能以及支持mark和reset方法的能力。
	 * BufferedOutputStream是缓冲输出流，通过设置这种输出流，应用程序就可以将各个字节写入底层输出流中，而不必针对每次字节写入调用底层系统。
	 */
	static public class BufferedInputStreamTest{
		@Test
		public void test() {
	        try {
	            BufferedInputStream in = new BufferedInputStream(new FileInputStream("src/test.txt"), 10);

	            int avail = in.available();
	            System.out.println("可读字节数：" + avail);
	            System.out.println("除最后十个字节外，读出所有字节");
	            for (int i = 0; i < avail - 10; i++) {
	            	byte[] inbuf = new byte[1];
	            	in.read(inbuf);
	                System.out.print(new String(inbuf) + ",");
	            }
	            System.out.println("\n可读字节数：" + in.available() + "\n");

	            if (!in.markSupported()) {
	                System.out.println("make/reset not supported!\n");
	                in.close();
	                return;
	            } else
	                System.out.println("make/reset supported!\n");

	            in.mark(1024);
	            System.out.println("使用skip方法跳过两个字节");
	            in.skip(2);
	            System.out.println("可读字节数：" + in.available());
	            in.reset();
	            System.out.println("执行reset方法后，可读字节数：" + in.available() + "\n");

	            byte[] buf = new byte[5];
	            in.read(buf, 0, 5);
	            // 将buf转换为String字符串
	            String str1 = new String(buf);
	            System.out.println("读取的5个字节为" + str1);

	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	static public class BufferedOutputStreamTest{}
	/*
	 * DataInputStream提供了一系列从二进制流中读取字节，并根据所有Java基本类型数据进行重构的readXXXX方法。
	 * 同时还提供根据UTF-8修改版格式的数据重构String的工具，即readUTF方法。
	 * DataOutputStream提供了一系列将数据从任意Java基本类型转换为一系列字节，并将这些字节写入二进制流的writeXXXX方法。
	 * 同时还提供了一个将String转换成UTF-8修改版格式并写入所得到的系列字节的工具，即writeUTF方法。
	 */
	static public class DataInputOutputStream{
		@Test
	    public void test() {
	        testDataOutputStream();
	        testDataInputStream();
	    }
	    private static void testDataOutputStream() {
	        try {
	            File file = new File("src/dataOutputStream.txt");
	            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

	            out.writeBoolean(true);
	            out.writeByte(122);
	            out.writeChar('b');
	            out.writeShort(123);
	            out.writeInt(1111);
	            out.writeLong(1233442L);
	            out.writeUTF("DataOutputStream");

	            out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * DataInputStream的API测试函数
	     */
	    private static void testDataInputStream() {

	        try {
	            File file = new File("src/dataOutputStream.txt");
	            DataInputStream in = new DataInputStream(new FileInputStream(file));

	            System.out.printf("readBoolean():%s\n", in.readBoolean());
	            System.out.printf("readByte():%s\n", in.readByte());
	            System.out.printf("readChar():%s\n", in.readChar());
	            System.out.printf("readShort():%s\n", in.readShort());
	            System.out.printf("readInt():%s\n", in.readInt());
	            System.out.printf("readLong():%s\n", in.readLong());
	            System.out.printf("readUTF():%s\n", in.readUTF());

	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * FileInputStream是文件输入流，用于从文件系统中的某个文件中获得输入字节。FileInputStream用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用FileReader。
	 * FileOutputStream是文件输出流，用于将数据写入File或FileDescriptor的输出流。FileOutputStream用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用FileWriter。
	 * Java语言本身不能对操作系统底层进行访问和操作，但是可以通过JNI接口调用其他语言来实现对底层的访问。
	 * FileInputStream不支持mark方法与set方法。
	 */
	static public class FileStreamTest {

	    private static final String FileName = "src/fileStream.txt";
	    @Test
	    public void main() {
	        testFileOutputStream();
	        testFileInputStream();
	    }

	    /**
	     * FileOutputStream的API测试类
	     */
	    private static void testFileOutputStream() {
	        try {
	            // FileOutputStream fos = new FileOutputStream("fileStream.txt");
	            // 创建文件对应File对象
	            File file = new File(FileName);
	            // 创建文件对应的FileOutputStream对象，默认是覆盖模式
	            FileOutputStream fos = new FileOutputStream(file);
	            fos.write(new byte[] { 0x61, 0x62, 0x63, 0x64 });// abcd
	            fos.write(new byte[] { 0x65, 0x66, 0x67, 0x68 });// efgh

	            // 创建文件对应的FileOutputStream对象，默认是覆盖模式
	            FileOutputStream fos2 = new FileOutputStream(file);
	            fos2.write(new String("qrstuvwx").getBytes());// qrstuvwx

	            // 创建文件对应的FileOutputStream对象，模式为追加模式
	            FileOutputStream fos3 = new FileOutputStream(file, true);
	            fos3.write(new byte[] { 0x51, 0x52, 0x53, 0x54 });// QRST

	            fos.close();
	            fos2.close();
	            fos3.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * FileInputStream的API测试函数
	     */
	    private static void testFileInputStream() {
	        try {
	            File file = new File(FileName);
	            FileInputStream fis = new FileInputStream(file);

	            FileDescriptor fd = fis.getFD();
	            // 根据文件描述符创建FileInputStream对象
	            FileInputStream fis2 = new FileInputStream(fd);

	            // 测试read()
	            System.out.println("使用read()读取一个字节：" + (char) fis.read());

	            System.out.println("使用available()获取当前可用字节数:" + fis.available());

	            // 测试read(byte[] b,int off,int len)
	            byte[] b = new byte[5];
	            fis.read(b, 0, b.length);
	            System.out.println("使用readread(byte[] b,int off,int len)读取5个字节到b中:" + new String(b));

	            System.out.println("使用available()获取当前可用字节数:" + fis.available());

	            // 测试skip(long byteCount)
	            System.out.printf("使用skip(long n)跳过%s个字节\n", fis.skip(1));

	            System.out.println("使用available()获取当前可用字节数:" + fis.available());

	            fis.close();
	            fis2.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * CharArrayReader实现一个可用作字符输入流的字符缓冲区。
	 * 在做所有操作前，都要确认流处于open状态(ready())。判断流处于open状态的依据是buf不为null。close方法中会将buf置为null。
	 * CharArrayReader支持mark()操作。
	 */
	static public class CharArrayReaderTest {

	    /**
	     * CharArrayReader的API测试函数
	     */
	    @Test
	    public void tesCharArrayReader() {
	        try {
	            CharArrayReader reader = new CharArrayReader(
	                    new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' });

	            // 从reader中连续读取三个字节
	            for (int i = 0; i < 3; i++) {
	                if (reader.ready() == true) {
	                    char tmp = (char) reader.read();
	                    System.out.print(tmp);
	                }
	            }
	            System.out.println();

	            // 测试是否支持mark
	            if (!reader.markSupported()) {
	                System.out.println("make not supported!");
	                return;
	            } else
	                System.out.println("make supported!");

	            // 标记，当前位置为d
	            reader.mark(0);

	            // 跳过2个字符，当前位置为f
	            reader.skip(2);

	            // 向下读取两个字符，fg
	            char[] buf = new char[2];
	            reader.read(buf, 0, 2);
	            System.out.println("buf:" + String.valueOf(buf));

	            // 重置当前位置为上一次标记的位置，即为d
	            reader.reset();

	            // 向下读取两个字符，de
	            reader.read(buf, 0, 2);
	            System.out.println("buf:" + String.valueOf(buf));

	            // 关闭后，在调用方法，会抛出“java.io.IOException: Stream closed”异常
	            reader.close();
	            reader.read();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * CharArrayWriter实现一个可用作字符输出流的字符缓冲区。
	 * CharArrayWriter的缓冲区会随向流中写入数据而自动增长。
	 * 可使用CharArrayWritert的oCharArray()和 toString()获取缓冲区中数据。
	 * CharArrayWriter中close()方法无效。
	 */
	public static class CharArrayWriterTest {

	    private static final char[] charArr = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g'};

	    /**
	     * CharArrayWriter的API测试函数
	     */
	    @Test
	    public void testCharArrayWriter() {
	        try {
	            // 创建CharArrayWriter字符流,默认大小为32
	            CharArrayWriter writer = new CharArrayWriter();

	            writer.write('A');
	            writer.write("BCDEF");
	            writer.write(charArr, 1, 3);
	            System.out.println("写入字符A，写入字符串BCDEF，写入charArr中从索引位置1开始的三个字符，即bcd，此时的writer为：\n" + writer);

	            writer.append('0').append("12345").append(String.valueOf(charArr), 3, 6);
	            System.out.println("写入字符0，写入字符串12345，写入charArr中从索引位置3到5的三个字符，即def，此时的writer为：\n" + writer);

	            System.out.println("此时writer的大小为：\n" + writer.size());

	            char[] buf = writer.toCharArray();
	            System.out.println("将writer转化为字符数组再将其打印：\n" + String.valueOf(buf));

	            // 创建CharArrayWriter字符流,指定大小为100
	            CharArrayWriter writer2 = new CharArrayWriter(100);
	            writer.writeTo(writer2);
	            System.out.println("将writer缓冲区内容写入到wirter2中，writer缓冲区内容为：\n" + writer2);

	            writer.reset();
	            System.out.println("将writer重置，此时其缓冲区为:\n" + writer);

	            writer2.close();
	            System.out.println("将writer2关闭，调用其size方法，结果为:\n" + writer2.size());
	            System.out.println("如果没有抛出异常，说明其close方法没有作用。");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * BufferedReader，字符缓冲输入流，作用是为其他输入流提供缓冲功能。BufferedReader从其他字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
	 * BufferedReader支持标记功能。
	 * BufferedWriter，字符缓冲输出流，作用是为其他输出流提供缓冲功能。BufferedWriter将文本写入其他字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
	 */
	public static class BufferedReaderTest {

	    @Test
	    public void test() {
	        try {
	            File file = new File("src/BufferedReader.txt");
	            BufferedReader in = new BufferedReader(new FileReader(file));

	            System.out.println("从缓冲区中读取5个字符，并打印");
	            for (int i = 0; i < 5; i++) {
	                if (in.ready()) {
	                    int tmp = in.read();
	                    System.out.println(i + ":" + (char) tmp);
	                }
	            }

	            char[] buf = new char[3];
	            in.read(buf, 0, 3);
	            System.out.println("从缓冲区中读取三个字符存到字符数组中，字符数组内容为" + String.valueOf(buf));
	            System.out.println("当前行剩余字符为" + in.readLine());

	            System.out.println("测试BufferedReader是否支持标记");
	            if (!in.markSupported()) {
	                System.out.println("make not supported!");
	                in.close();
	                return;
	            }
	            System.out.println("make supported!");

	            System.out.println("测试标记功能（当前位置的下个字符为A）");
	            in.mark(1024);
	            System.out.println("跳过22个字符");
	            in.skip(21);
	            System.out.println("当前位置为" + (char) in.read());
	            System.out.println("重置输入流的索引为mark()所标记的位置，即重置到f处");
	            in.reset();
	            System.out.println("当前位置为" + (char) in.read());

	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static class BufferedWriterTest {

	    @Test
	    public void test() {

	        try {
	            File file = new File("src/BufferedWriter.txt");
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

	            writer.write(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 3);
	            writer.newLine();
	            writer.write("ABCDEFGHIJKLMN", 0, 3);
	            writer.newLine();
	            writer.write('\n');
	            writer.write(1200);

	            writer.flush();

	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * InputStreamReader，字节流通向字符流的桥梁：它使用指定的charset读取字节并将其解码为字符。
	 * 每次调用InputStreamReader中的read方法都会导致从底层输入流读取一个或多个字节，然后调用编码转换器将字节转化为字符。为避免频繁调用转换器，实现从字节到字符的高效转换，可以提前从底层流读取更多的字节。为了达到最高效率，可要考虑在BufferedReader内包装InputStreamReader。
	 * InputStreamReader的功能是依赖于StreamDecoder完成的。
	 * OutputtreamWriter，字符流通向字节流的桥梁：它使用指定的charset将要写入流中的字符编码成字节。
	 * 每次调用write()方法都会导致在给定字符（或字符集）上调用编码转换器。为避免频繁调用转换器，在写入底层输出流之前，可以将得到的这些字节积累在缓冲区。例如，可考虑将OutputStreamWriter包装到BufferedWriter中。
	 */
	public static class OutputStreamWriterTest {

	    @Test
	    public void test() {
	        try {
	            File file = new File("src/inputStreamReader.txt");
	            //等价于FileWriter outFileWriter = new FileWriter(file);
	            OutputStreamWriter out1 = new OutputStreamWriter(new FileOutputStream(file));
	            out1.write("潘威威的博客");

	            out1.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static class InputStreamReaderTest {

	    /**
	     * inputStreamReader.txt中的内容为“潘威威的博客”
	     */
	    @Test
	    public void test() {
	        try {
	            File file = new File("src/inputStreamReader.txt");
	            //等价于FileReader fileReader = new FileReader(file);
	            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"utf-8");

	            System.out.println("ready():"+reader.ready());

	            System.out.println("getEncoding():"+reader.getEncoding());

	            System.out.println("read():"+(char)reader.read());

	            char[] buf = new char[40];
	            reader.read(buf, 0, buf.length);
	            System.out.println("read(char[], int, int)："+(new String(buf)));

	            reader.close();
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	/*
	 * 
	 */
}
