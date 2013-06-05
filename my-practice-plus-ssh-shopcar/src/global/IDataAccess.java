/*
 * 类名：IDataAccess 作用：定义数据访问的通用接口。 日期： 作者：
 */
package global;

import java.sql.ResultSet;

public interface IDataAccess
{
	public boolean executeSQL(String sql);

	public ResultSet queryBySQL(String sql);

	public void openConnect();

}
