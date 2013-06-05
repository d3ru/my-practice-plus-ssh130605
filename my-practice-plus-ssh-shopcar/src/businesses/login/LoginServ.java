package businesses.login;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

public class LoginServ extends HttpServlet {
	private ServletConfig config;
	public LoginServ() {
		// TODO Auto-generated constructor stub
	}
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//BufferedReader reader=request.getReader();
		//System.out.println("come");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		Object url=request.getSession().getAttribute("urlstr");
		//解码先
		user=user==null?"":user;
		pass=pass==null?"":pass;
		String code=request.getParameter("img");
		HttpSession session=request.getSession();
		String oldcode=session.getAttribute("img").toString();
		String sql="select * from "+TableInfo.TABLE_UserInfo+" where umail='"+user+"' and upassword='"+pass+"'";
		if(code.equals(oldcode)){
			IDataAccess ida=DataAccessImpl.newInstance();
			ResultSet rs=ida.queryBySQL(sql);
			try{
				if(rs.next()){
					//登录成功后的session标记。 
					session.setAttribute("loginsuccess", "登录成功！");
					String uname=rs.getString(3);
					session.setAttribute("uname",uname);
					session.setAttribute("sc_user",rs.getString(2));
					//如果被拦截到登录的，则重新定向到被拦截前的ＵＲＬ
					//System.out.println("需要重定向的url:"+url);
					if(url!=null){
						//response.setHeader("refresh", "0;url='"+request.getContextPath()+"'/index.jsp");
						//RequestDispatcher rd=request.getRequestDispatcher(url.toString());
						//rd.forward(request, response);
						request.getSession().removeAttribute("urlstr");
						response.sendRedirect(request.getContextPath()+url.toString());
					}
					else{
						//RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
						//rd.forward(request, response);
						//如果没有定向URL，则直接定向到主页
						response.sendRedirect(request.getContextPath()+"/index.jsp");
					}
					
				}else{
					request.setAttribute("notlogin", "登录失败");
					RequestDispatcher rd=request.getRequestDispatcher("/login/userlogin.jsp");
					rd.forward(request, response);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		else
		{
			//
			request.setAttribute("notlogin", "登录失败");
			RequestDispatcher rd=request.getRequestDispatcher("/login/userlogin.jsp");
			rd.forward(request, response);
			
			//out.print("<font color=red>登录失败</font><br/>");
			//out.print("<a href='"+request.getContextPath()+"//index.jsp'>返回</a>");
		}
		
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config=config;
	}

}
