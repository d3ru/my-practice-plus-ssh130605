package businesses.login;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServ extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3796520778451017180L;
	private ServletConfig config;

	public LoginServ()
	{
	}

	@Override
	public void destroy()
	{
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// BufferedReader reader=request.getReader();
		response.setContentType("text/html;charset=gb2312");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		Object url = request.getSession().getAttribute("urlstr");

		user = user == null ? "" : user;
		pass = pass == null ? "" : pass;
		String code = request.getParameter("img");
		HttpSession session = request.getSession();
		String oldcode = session.getAttribute("img").toString();
		String sql = "select * from " + TableInfo.TABLE_UserInfo + " where umail='" + user + "' and upassword='" + pass + "'";
		if (code.equals(oldcode))
		{
			IDataAccess ida = DataAccessImpl.newInstance();
			ResultSet rs = ida.queryBySQL(sql);
			try
			{
				if (rs.next())
				{
					// 登录成功后的session标记。
					session.setAttribute("loginsuccess", "登录成功！");
					String uname = rs.getString(3);
					session.setAttribute("uname", uname);
					session.setAttribute("sc_user", rs.getString(2));
					// 如果被拦截到登录的，则重新定向到被拦截前的ＵＲＬ
					// System.out.println("需要重定向的url:"+url);
					if (url != null)
					{
						// response.setHeader("refresh", "0;url='"+request.getContextPath()+"'/index.jsp");
						// RequestDispatcher rd=request.getRequestDispatcher(url.toString());
						// rd.forward(request, response);
						request.getSession().removeAttribute("urlstr");
						response.sendRedirect(request.getContextPath() + url.toString());
					}
					else
					{
						// RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
						// rd.forward(request, response);
						// 如果没有定向URL，则直接定向到主页
						response.sendRedirect(request.getContextPath() + "/index.jsp");
					}

				}
				else
				{
					request.setAttribute("notlogin", "登录失败");
					RequestDispatcher rd = request.getRequestDispatcher("/login/userlogin.jsp");
					rd.forward(request, response);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			request.setAttribute("notlogin", "登录失败");
			RequestDispatcher rd = request.getRequestDispatcher("/login/userlogin.jsp");
			rd.forward(request, response);
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		this.config = config;
	}

}
