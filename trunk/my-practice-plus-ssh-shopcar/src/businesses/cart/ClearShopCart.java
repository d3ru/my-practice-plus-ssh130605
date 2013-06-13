package businesses.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearShopCart extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555296870214595828L;

	public ClearShopCart()
	{
		super();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=gb2312");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			String pidx = cookie.getName();
			System.out.println("cookie的名称:" + pidx);
			if (pidx.startsWith("pro_"))
			{
				// 清空cookie中的数据
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		PrintWriter out = response.getWriter();
		out.println("");
		out.flush();
		// out.close();
	}

}
