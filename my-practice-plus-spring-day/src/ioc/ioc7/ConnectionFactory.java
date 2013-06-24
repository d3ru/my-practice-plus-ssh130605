package ioc.ioc7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	public void setDriverClassName(String driverClassName)
	{
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return conn;
	}
}
