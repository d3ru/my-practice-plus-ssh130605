package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OrderAtOnceTag extends BodyTagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9160847708143672651L;

	@Override
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		Cookie[] cookies = request.getCookies();
		try
		{
			IDataAccess ida = DataAccessImpl.newInstance();
			Cookie cartproducts[] = request.getCookies();// 通过Ｃookie从客户获取选购商品信息.
			String cpid = "";
			int pid;
			int pronum;
			double totalmoney = 0;
			out.print("<p>&nbsp;</p>");
			out.print("<table border='1' width='80%' cellspacing='0' cellpadding='0'>");
			out.print("<tr style='background-color:#333333;color:blue;'>");
			out.print("<td>商品名称</td>");
			out.print("<td>商品价格</td>");
			out.print("<td>商品说明</td>");
			out.print("<td>选购数量</td>");
			out.print("<td>价格小记</td>");
			out.print("</tr>");
			//
			for (Cookie cook : cartproducts)
			{// 循环解析Cookie中的选购商品信息。
				cpid = cook.getName();// Ｃookie名，选购商品以pro开头。
				if (cpid.startsWith("pro_"))
				{
					try
					{
						pid = Integer.parseInt(cpid.substring(4));// 商品ＩＤ
						pronum = Integer.parseInt(cook.getValue());// 商品数量
						String sql = "select * from " + TableInfo.TABLE_Products + " where " + TableInfo.PROT_pid + "=" + pid;
						ResultSet rs = ida.queryBySQL(sql);
						while (rs.next())
						{
							out.print("<tr>");
							String pname = rs.getString("pname");
							String pdescription = rs.getString("pdescription");
							double pprice = rs.getDouble("pprice");

							out.println("<td>" + pname + "</td>");// 商品名称
							out.println("<td>" + pprice + "</td>");// 销售价
							out.println("<td>" + pdescription + "</td>");// 库存量
							out.print("<td>" + pronum + "</td>");
							out.println("<td>" + pronum * pprice + "</td>");
							totalmoney += pronum * pprice;
							out.print("<tr>");
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					// 清空Cookie
					cook.setMaxAge(0);// 正:在指定秒数后删除,0:马上删除,负:浏览器关闭后删除.
					// cook.setValue("");
					response.addCookie(cook);
					// response.setHeader("refresh", "0;url='"+request.getContextPath()+"/index.jsp'");
				}
			}
			// out.print("<tr  align='center'><td colspan='5' align='center'><B>总金额:</b><font color=red>"+totalmoney+"</font></td></tr>");
			out.print("<tr  align='center'><td colspan='5' align='center'><B>总金额:</b><font color=red>" + totalmoney + "</font></td></tr>");
			out.print("</table>");
			out.print("<p>&nbsp;</p>");
			out.print("<a href='" + request.getContextPath() + "/index.jsp'><u>返回</u></a>");
			response.flushBuffer();

		}
		catch (Exception err)
		{

		}
		return super.doEndTag();
	}
}
