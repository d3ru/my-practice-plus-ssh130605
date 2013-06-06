package businesses.order;

import global.DataAccessImpl;
import global.TableInfo;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businesses.tools.CommonTools;
import businesses.tools.QueryTools;

public class ProductShow extends HttpServlet
{

	private static final long serialVersionUID = 4785292953943164850L;

	public ProductShow()
	{
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataAccessImpl dataAccess = DataAccessImpl.newInstance();
		HttpSession session = request.getSession();

		String pidx = request.getParameter("pid");
		int pid = CommonTools.StringToInt(pidx);
		if (pid == -1)
			CommonTools.Error(request, response, session);
		else
		{
			if (QueryTools.isExistColumn(dataAccess, TableInfo.PROT_pid, TableInfo.TABLE_Products, TableInfo.PROT_pid + "=" + pid))
			{
				try
				{
					response.setContentType("image/jpeg");
					ServletOutputStream out = response.getOutputStream();
					String sql = "select " + TableInfo.PROT_pphoto + " from " + TableInfo.TABLE_Products + " where ";
					sql += TableInfo.PROT_pid + "=" + pid;
					ResultSet rst = dataAccess.queryBySQL(sql);
					if (rst.next())
					{
						Blob blob = rst.getBlob(TableInfo.PROT_pphoto);
						if (blob != null)
						{
							byte[] b = blob.getBytes(1, (int) blob.length());
							out.write(b);
						}
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

}
