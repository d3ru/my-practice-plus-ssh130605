package businesses.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet
{

	private static final long serialVersionUID = -3772947398721224449L;

	public Logout()
	{
		super();
	}

	@Override
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if (session.getAttribute("uname") != null)
		{
			session.removeAttribute("uname");
		}
		if (session.getAttribute("loginsuccess") != null)
		{
			session.removeAttribute("loginsuccess");
		}
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}

}
