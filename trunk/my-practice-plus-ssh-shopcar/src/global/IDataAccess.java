/*
 * ������IDataAccess ���ã��������ݷ��ʵ�ͨ�ýӿڡ� ���ڣ� ���ߣ�
 */
package global;

import java.sql.ResultSet;

public interface IDataAccess
{
	public boolean executeSQL(String sql);

	public ResultSet queryBySQL(String sql);

	public void openConnect();

}
