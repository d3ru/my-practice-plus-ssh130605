package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ShopMenuTag extends BodyTagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1450289669722127279L;
	private String submitevent;

	public String getSubmitevent()
	{
		return submitevent;
	}

	public void setSubmitevent(String submitevent)
	{
		this.submitevent = submitevent;
	}

	@Override
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		ServletContext context = pageContext.getRequest().getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("user");
		String password = context.getInitParameter("password");
		try
		{
			// out.println("<menu type='бя'>");
			out.println("<table  border=0>");
			String sql = "select * from " + TableInfo.TABLE_ProductType;
			IDataAccess ida = DataAccessImpl.newInstance(driver, url, user, password);
			ResultSet rs = ida.queryBySQL(sql);
			int i = 0;
			while (rs.next())
			{
				int id = rs.getInt(1);
				String ptname = rs.getString(2);
				out.println("<tr>");
				out.println("<td>");
				// out.println("<li id='a"+i+"'>");
				out.println("<table width='191' height='31' border='0' cellpadding='0' cellspacing='0'>");
				out.println("<tr>");
				out.println("<td width='36' height='31' align='right' valign='top'><img src='images/doc.gif' width='18' height='18'>&nbsp;</td>");
				out.println("<td height='31'>");
				out.println("<span onclick='" + submitevent + "(" + id + ");' onmouseover=\"this.style.cursor='hand';this.style.color='red';\" onmouseout=\"this.style.cursor='normal';this.style.color='black';\">");
				out.println(ptname);
				out.println("</span>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				i++;
			}
			// out.println("</menu>");
			out.println("</table>");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.doEndTag();
	}
}
