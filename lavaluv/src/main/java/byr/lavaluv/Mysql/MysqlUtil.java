package byr.lavaluv.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlUtil {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private Connection connection = null;
	private Statement statement = null;
	public MysqlUtil(String path,String user,String password) {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(path,user,password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return connection;
	}
	public void releaseConnection() {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e2) {}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet querySql(Connection connection,String sql) {
		try {
			return statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int alterSql(Connection connection,String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
