/**
 * 说明：查询的常用工具类 文件：queryTools.java 时间：08-05-17 编写：tarena
 */

package businesses.tools;

import global.DataAccessImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTools
{

	// 说明：返回符合某个条件某表中某个字段的值
	// 条件：只能返回查询的第一个字段值
	// 使用： dataAcess：数据库访问通用类
	// columnName：欲查询的列名
	// tableName：表名
	// queryFactor：查询条件
	public static String getColumnValue(DataAccessImpl dataAccess, String columnName, String tableName, String queryFactor)
	{
		String sql = "select " + columnName + " from " + tableName + " where " + queryFactor;
		System.out.println(sql);
		try
		{
			ResultSet rst = dataAccess.queryBySQL(sql);
			if (rst.next())
				return rst.getString(1);
			else
				return null;
		}
		catch (SQLException e)
		{
			System.out.println("执行语句:\n" + sql + "\n时发生错误,请检查!");
			e.printStackTrace();
			return null;
		}
	}

	// 说明：查询符合某个条件的某表中的某些字段是否有记录集
	// 使用： dataAcess：数据库访问通用类
	// columnName：欲查询的列名
	// tableName：表名
	// queryFactor：查询条件
	public static boolean isExistColumn(DataAccessImpl dataAccess, String columnName, String tableName, String queryFactor)
	{
		String sql = "select " + columnName + " from " + tableName + " where " + queryFactor;
		// System.out.println(sql);
		try
		{
			ResultSet rst = dataAccess.queryBySQL(sql);
			return rst.next();
		}
		catch (SQLException e)
		{
			System.out.println("执行语句:\n" + sql + "\n时发生错误,请检查!");
			e.printStackTrace();
			return false;
		}
	}

}
