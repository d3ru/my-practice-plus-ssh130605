/**
 * ˵������ѯ�ĳ��ù����� �ļ���queryTools.java ʱ�䣺08-05-17 ��д��tarena
 */

package businesses.tools;

import global.DataAccessImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTools
{

	// ˵�������ط���ĳ������ĳ����ĳ���ֶε�ֵ
	// ������ֻ�ܷ��ز�ѯ�ĵ�һ���ֶ�ֵ
	// ʹ�ã� dataAcess�����ݿ����ͨ����
	// columnName������ѯ������
	// tableName������
	// queryFactor����ѯ����
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
			System.out.println("ִ�����:\n" + sql + "\nʱ��������,����!");
			e.printStackTrace();
			return null;
		}
	}

	// ˵������ѯ����ĳ��������ĳ���е�ĳЩ�ֶ��Ƿ��м�¼��
	// ʹ�ã� dataAcess�����ݿ����ͨ����
	// columnName������ѯ������
	// tableName������
	// queryFactor����ѯ����
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
			System.out.println("ִ�����:\n" + sql + "\nʱ��������,����!");
			e.printStackTrace();
			return false;
		}
	}

}
