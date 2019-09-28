package byr.lavaluv.Mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/*
 * 1/ISAM 
 * ISAM是一个定义明确且历经时间考验的数据表格管理方法，它在设计之时就考虑到数据库被查询的次数要远大于更新的次数。因此，ISAM执行读取操作的速度很快，而且不占用大量的内存和存储资源。
 * ISAM的两个主要不足之处在于，它不支持事务处理，也不能够容错：如果你的硬盘崩溃了，那么数据文件就无法恢复了。
 * 如果你正在把ISAM用在关键任务应用程序里，那就必须经常备份你所有的实时数据，通过其复制特性，MySQL能够支持这样的备份应用程序。 
 * 2/InnoDB 
 * 它提供了事务控制能力功能，它确保一组命令全部执行成功，或者当任何一个命令出现错误时所有命令的结果都被回退，可以想像在电子银行中事务控制能力是非常重要的。
 * 支持COMMIT、ROLLBACK和其他事务特性。
 * 
 * 创建数据库:Create Database databaseName
 * 切换数据库：Use databaseName
 * 创建表：Create Table If Not Exist tableName(name Type [not null|Default value],Primary key(name)[Index])[Engine=InnoDB|Default CharSet=]
 * 更改表：Alter Table tableName [Rename To tableName |Drop colName |Add colName Type |Modify colName Type |Change colName newName Type][Not Null][Default value]
 * 删除表：Drop Table tableName
 * 插入数据：Insert Into tableName (name) Values (value)
 * 查询数据:SELECT column_name,column_name
	FROM table_one |table_two
	[WHERE condition1 [AND [OR]] condition2]
	[LIMIT N][ OFFSET M]
 * MySQL 的 WHERE 子句的字符串比较是不区分大小写的。 你可以使用 BINARY 关键字来设定 WHERE 子句的字符串比较是区分大小写的。
 * 
 * 更新数据：UPDATE table_name SET field1=new-value1, field2=new-value2[WHERE Clause]
 * 删除数据：DELETE FROM table_name [WHERE Clause]
 * 在 where like 的条件查询中，SQL 提供了四种匹配方式。
	%：表示任意 0 个或多个字符。可匹配任意类型和长度的字符，有些情况下若是中文，请使用两个百分号（%%）表示。
	_：表示任意单个字符。匹配单个任意字符，它常用来限制表达式的字符长度语句。
	[]：表示括号内所列字符中的一个（类似正则表达式）。指定一个字符、字符串或范围，要求所匹配对象为它们中的任一个。
	[^] ：表示不在括号所列之内的单个字符。其取值和 [] 相同，但它要求所匹配对象为指定字符以外的任一个字符。
	查询内容包含通配符时,由于通配符的缘故，导致我们查询特殊字符 “%”、“_”、“[” 的语句无法正常实现，而把特殊字符用 “[ ]” 括起便可正常查询。
	
 * 联合语句：select one UNION [ALL | DISTINCT] select two
 * 排序：ORDER BY field1 [ASC [DESC][默认 ASC]], [field2...] [ASC [DESC][默认 ASC]]
 * 分组：SELECT column_name, function(column_name)
	FROM table_name
	WHERE column_name operator value
	GROUP BY column_name [Having];
 * 注意group by后的列名和select的列名一致
 * 
 * 联合多表查询：
 * INNER JOIN（内连接,或等值连接）：获取两个表中字段匹配关系的记录,交集。
 * LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应匹配的记录，左并集。
 * RIGHT JOIN（右连接）： 与 LEFT JOIN 相反，用于获取右表所有记录，即使左表没有对应匹配的记录，右并集。
 * 
 * null值处理：
 * IS NULL: 当列的值是 NULL,此运算符返回 true。
 * IS NOT NULL: 当列的值不为 NULL, 运算符返回 true。
 * <=>: 比较操作符（不同于=运算符），当比较的的两个值为 NULL 时返回 true。
 * ifnull(columnName2,0)返回0
 * 
 * 正则表达式：
 * WHERE name REGEXP ''
 * ^	匹配输入字符串的开始位置。如果设置了 RegExp 对象的 Multiline 属性，^ 也匹配 '\n' 或 '\r' 之后的位置。
 * $	匹配输入字符串的结束位置。如果设置了RegExp 对象的 Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置。
 * .	匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用象 '[.\n]' 的模式。
 * [...]	字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。
 * [^...]	负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。
 * p1|p2|p3	匹配 p1 或 p2 或 p3。例如，'z|food' 能匹配 "z" 或 "food"。'(z|f)ood' 则匹配 "zood" 或 "food"。
 * *	匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。
 * +	匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。
 * {n}	n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。
 * {n,m}	m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。
 * 
 * 在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。
 * 事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。
 * 事务用来管理 insert,update,delete 语句
 * 事务是必须满足4个条件（ACID）：：原子性（Atomicity，或称不可分割性）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）
 * 原子性：一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
 * 一致性：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
 * 隔离性：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。
 * 事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
 * 持久性：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。
 * 1、用 BEGIN, ROLLBACK, COMMIT来实现
	BEGIN 开始一个事务
	ROLLBACK 事务回滚
	COMMIT 事务确认
 * 2、直接用 SET 来改变 MySQL 的自动提交模式:
	SET AUTOCOMMIT=0 禁止自动提交
	SET AUTOCOMMIT=1 开启自动提交
 * 3、使用 SAVEPOINT
 	SAVEPOINT savepoint_name;    // 声明一个 savepoint
	ROLLBACK TO savepoint_name;  // 回滚到savepoint
	RELEASE SAVEPOINT savepoint_name;  // 删除指定保留点
 * 
 * 索引分单列索引和组合索引。单列索引，即一个索引只包含单个列，一个表可以有多个单列索引，但这不是组合索引。组合索引，即一个索引包含多个列。
 * 单列索引(普通索引，唯一索引，主键索引)、组合索引、全文索引、空间索引
 * 索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录。
 * 虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件。
 * 建立索引会占用磁盘空间的索引文件。
 * CREATE [UNIQUE] INDEX indexName ON tableName(colName(length)) 如果是CHAR，VARCHAR类型，length可以小于字段实际长度；如果是BLOB和TEXT类型，必须指定 length。
 * ALTER Table tableName ADD [UNIQUE]INDEX indexName(columnName)
 * DROP INDEX [indexName] ON tableName; 
 * 有四种索引类型：
 * primary key主键，值唯一且不能为null
 * unique 值唯一可以为null
 * index
 * fulltext 全文索引 
 * 
 * CREATE TEMPORARY TABLE创建临时表，只限当前连接可见，关闭连接自动删除
 * SHOW CREATE TABLE 显示创建表的sql语句
 * INSERT INTO ... SELECT 用于复制表
 * CREATE TABLE tableName LIKE sourceTable| AS {SELECT colName [AS newName] FROM sourceTable [WHERE ...]}用于复制表
 * 
 * 获取表元数据：
 * SELECT VERSION( )	服务器版本信息
 * SELECT DATABASE( )	当前数据库名 (或者返回空)
 * SELECT USER( )	当前用户名
 * SHOW STATUS	服务器状态
 * SHOW VARIABLES	服务器配置变量
 * 
 * 重复数据：
 * insert ignore into避免重复插入
 * select distinct 避免查询重复值
 * select count(colName),colName from table group by count(colName) having count(colName)>1 统计重复值
 * create table tableName select colName from sourceTable group by colName 创建没有重复值的表
 * 
 * 导出,导入数据：
 * select * from tableName Into OutFile "fileName"
 * mysql -u用户名    -p密码    <  要导入的数据库数据(runoob.sql)
 * source /home/abc/abc.sql
 * LOAD DATA LOCAL INFILE 'dump.txt' INTO TABLE mytbl
 */
public class ConnectDataBase {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE_PATH = "jdbc:mysql://localhost:3306/informationCenter";
	static public void main(String[] args) throws SQLException{
		MysqlUtil mysqlUtil = new MysqlUtil(DATABASE_PATH, "root", "root");
		Connection connection = mysqlUtil.getConnection();
		//String sql = "create table if not exists test2(id int auto_increment,text varchar(40) not null,date datetime,primary key(id))engine=InnoDB,default charset=utf8";
		//String sql = "drop table test";
//		Random random = new Random();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//		String dateString = format.format(new Date());
//		String sql = "insert into test (text,date) values ('test"+random.nextInt(100000)+"','"+dateString+"')";
//		mysqlUtil.alterSql(connection, sql);
//		for (int i = 0; i < 1000000; i++) {
//			dateString = format.format(new Date());
//			sql = "insert into test2 (text,date) values ('test"+random.nextInt(100000)+"','"+dateString+"')";
//			mysqlUtil.alterSql(connection, sql);
//		}
		String sqlString = "select text,count(text) from test where text>'test50' and text like '%51%' group by text having count(text) > 2 order by count(text) desc limit 1000";
		//String sqlString = "select test.text,test.date,test2.date from test left join test2 on test.text > test2.text limit 100";
		//String sqlString = "select * from test where text regexp '^test5' and text regexp '0.0$'";
		ResultSet resultSet = mysqlUtil.querySql(connection, sqlString);
		while(resultSet.next()) {
			System.out.println(resultSet.getString("text")+" "+resultSet.getLong("count(text)"));
		}
		mysqlUtil.releaseConnection();
	}
}
