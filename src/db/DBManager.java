package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ����ӹ�����
 *
 * @author 502
 * 
 */
public class DBManager {
	public static final String DEFAULT_PWD = "123456";
	public static final String DEFAULT_UID = "root";
	public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/mi";
	public static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getConn() {
		Connection con = null;

		try {
			Class.forName(DEFAULT_DRIVER);
			con = DriverManager.getConnection(DEFAULT_URL, DEFAULT_UID, DEFAULT_PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ���������ʧ��");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}

		return con;
	}

}
