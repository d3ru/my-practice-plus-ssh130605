package com.pan.evms.sl.interaction.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-18
 * @time 下午4:05:05
 */
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet
{

	private static final long serialVersionUID = 7579158508938601067L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println();
		Map<String, String[]> map = post(request);
		for (String string : map.keySet())
		{
			for (int i = 0; i < map.get(string).length; i++)
				out.println(map.get(string)[i]);
		}
		out.println();
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	private Map<String, String[]> post(HttpServletRequest request)
	{
		Map<String, String[]> map = request.getParameterMap();
		return map;
	}

}
