package byr.lavaluv.IO;
/*
 * java I/O 分为字节流和字符流，其中又分为输入和输出
 * 
 * 字节流输入：
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
 * 字节流输出：
 * ByteArrayOutputStream	数据被写入一个byte数组。缓冲区会随着数据的不断写入而自动增长。
 * PipedOutputStream	和PipedInputStream一起使用，实现多线程间的管道通信。
 * FilterOutputStream	主要用途在于封装其他的输入输出流，为它们提供一些额外的功能。FilterOutputStream的子类可进一步重写一些方法，并且还可以提供一些额外的方法和字段。
 * BufferedOutputStream	用于为其他输出流提供缓冲功能。
 * DataOutputStream	数据输出流允许应用程序以适当方式将基本Java数据类型写入输出流中。
 * PrintStream	为其他输出流提供打印功能。
 * FileOutputStream	文件输出流，用于将数据写入File或FileDescriptor的输出流。FileOutputStream用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用FileWriter。
 * ObjectOutputStream	将Java对象的基本数据类型和图形写入 OutputStream。
 * 
 * 字符流输入：
 * CharArrayReader	用于读取字符数组，实现一个可用作字符输入流的字符缓冲区。
 * PipedReader	和PipedWriter一起实现线程间的通讯。
 * FilterReader	用于读取已过滤的字符流
 * BufferedReader	为另一个输入流添加缓冲功能。
 * InputStreamReader	是字节流通向字符流的桥梁
 * FileReader	用于对文件进行读取操作
 * 
 * 字符流输出：
 * CharArrayWriter	实现了一个字符缓冲区。缓冲区会随向流中写入数据而自动增长。
 * PipedWriter	和PipedReader一起是通过管道实现线程间的通讯。
 * FilterWriter	用于写入已过滤的字符流。	
 * BufferedWriter	为另一个输出流添加缓冲功能。
 * OutputStreamWriter	是字符流通向字节流的桥梁
 * FileWriter	用于对文件进行写入操作。	
 * PrintWriter	为文本输出流提供打印功能。
 */
public class IOs {

}
