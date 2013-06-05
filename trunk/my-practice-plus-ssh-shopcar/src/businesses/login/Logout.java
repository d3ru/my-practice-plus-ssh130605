package businesses.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772947398721224449L;

	/**
	 * Constructor of the object.
	 */
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

	/**
	 * The service method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
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

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	@Override
	public void init() throws ServletException
	{
		// Put your code here
	}

}
