package dataBaseutil;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import user.User;

public class DataBaseUtils {
	
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/dy_atm?characterEncoding=utf8";
	private final static String USER = "root";
	private final static String PASSWORD = "Gyy16661727391";
	private static Connection connection = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
	 
	
	public static int addUser(User user) {
		Statement statement = null;
		int row = -1;
		try {
			statement = connection.createStatement();
	
			String sql = "insert into atm_user(card_num,balance,create_time,modify_time) value (" +user.getCarNum() 
					+","
					+user.getBalanece()
					+","
					+"now()"
					+","
					+"now()"
					+");";
			
			row = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(null != statement) {
					statement.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	public static int deposit(int amount,int cardNum) {
		Statement statement = null;
		int row = -1;
		try {
			statement = connection.createStatement();
	
			String sql = "update atm_user set balance = balance +"
					+ amount
					+ "where card_num = "
					+ cardNum
					+";"; 
			
			row = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if(null != statement) {
					statement.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return row;
	}

	
	
	
}


