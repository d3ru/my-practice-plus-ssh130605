/*
 * ������DataAccessImpl
 * ���ã����ݷ��ʵ�ʵ���ࡣ
 * ���ڣ�
 * ���ߣ�
 * */
package global;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sun.security.jca.GetInstance;

public class DataAccessImpl implements IDataAccess,Serializable {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	public static Connection connection = null;
	private static DataAccessImpl da = null;
	
	private DataAccessImpl(String driver,String url,String username,String password)
	{
		DataAccessImpl.driver = driver;
		DataAccessImpl.url = url;
		DataAccessImpl.username = username;
		DataAccessImpl.password = password;
		init();
	}
	public static DataAccessImpl newInstance(String driver,String url,String username,String password){
		if(da == null)
			da =new DataAccessImpl(driver,url,username,password);
		return da;
	}
	public static DataAccessImpl newInstance(){
		return da;
	}
	private static void init(){
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ���������ʧ��!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("�����ݿ�ʱ��������!");
		}
	}
	
	public boolean executeSQL(String sql) {
		try {
			Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			sta.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("ִ�����:\n"+sql+"\nʱ��������,����!"+e.getMessage());
			return false;
		}
	}

	public ResultSet queryBySQL(String sql) {
		try {
			Statement sta = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			return sta.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("ִ�����:\n"+sql+"\nʱ��������,����!"+e.getMessage());
			return null;
		}
		
	}
	public void openConnect(){
		if(da == null)
			da =new DataAccessImpl(driver,url,username,password);
	}

}
