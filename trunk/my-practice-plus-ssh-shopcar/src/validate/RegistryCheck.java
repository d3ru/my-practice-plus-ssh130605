package validate;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistryCheck extends HttpServlet
{

	private static final long serialVersionUID = -5392648280450800329L;

	public RegistryCheck()
	{
		super();
	}

	@Override
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		String umail = request.getParameter("umail");
		String sql = "select *  from " + TableInfo.TABLE_UserInfo + " where " + TableInfo.USER_umail + "='" + umail + "'";
		IDataAccess ida = DataAccessImpl.newInstance();
		ResultSet rs = ida.queryBySQL(sql);
		System.out.println(sql);
		try
		{
			if (rs.next())
			{
				response.setStatus(200);
			}
			else
			{
				response.setStatus(400);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	@Override
	public void init() throws ServletException
	{
	}

}
