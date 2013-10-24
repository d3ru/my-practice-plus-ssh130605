package com.pan.evms.sl.interaction.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-18
 * @time 下午3:41:31
 */
public abstract class HttpsServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4924119704400204549L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String queryType = request.getParameter("queryType");
		if (Strings.isNullOrEmpty(queryType))
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		}
		try
		{
			printResponse(response, getResponse(request));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void printResponse(HttpServletResponse response, String r) throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(this.getClass());
		out.flush();
		out.close();
	}

	protected abstract String getResponse(HttpServletRequest request);

}
