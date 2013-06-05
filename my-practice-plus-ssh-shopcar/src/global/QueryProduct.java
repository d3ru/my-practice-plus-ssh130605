package global;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryProduct extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6783619263052490951L;
	private final static int itemsperpage = 4;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 获取类型
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		int curPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
		// 类型查询
		String sql = "";
		if (type == null || type.equals("0"))
		{
			sql += "select * from " + TableInfo.TABLE_Products;

		}
		else
		{
			sql += "select * from " + TableInfo.TABLE_Products + " where " + TableInfo.PROT_ptypeid + "=" + type;
		}
		// 考虑关键字
		if (key != null)
		{
			if (type == null || type.equals("0"))
			{
				sql += " where " + TableInfo.PROT_pname + " like '%" + key + "%'";

			}
			else
			{
				sql += " and " + TableInfo.PROT_pname + " like '%" + key + "%'";
			}
		}
		int totalpage = 0;
		IDataAccess ida = DataAccessImpl.newInstance();
		ResultSet rs = ida.queryBySQL(sql);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		// 内容
		out.println("<table border='1' cellspacing='0' cellpadding='0'  width='100%'>");
		out.println("<tr valign='bottom'><td>");
		// out.println("<iframe style='height:26px;width:400px;' src='test.jsp' frameborder='0' scrolling='no' hspace='0' ></iframe>");
		out.println("<table border='0' width='100%'  cellspacing='0' cellpadding='0'>");
		out.println("<tr height='14' valign='buttom' >");
		out.println("<td width='90' height='10' align='center'  style='border-bottom-style: none; border-bottom-color: #FFFFFF; border-bottom:none; border-bottom-width:0px;' background='images/2.gif'>::商品搜索::</td>");
		out.println("<td width='90' background='images/2.gif'><div align='center'>请输入关键字</div></td>");
		out.println("<td width='220' background='images/2.gif'><div align='center'>");
		out.println("<input type='text' style='border-style: none;height:15px;' id='key' name='key' style='width:220px'/>");
		out.println("</div></td>");
		out.println("<td background='images/2.gif' width='58' valign='middle' align='center'>");
		out.println("<button onclick=\"showname('" + type + "','" + key + "');\">搜索</button>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td></tr>");
		try
		{
			if (rs.next())
			{
				System.out.println(rs.getDate("pdate"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(df);
				out.println("<tr valign='top'>");
				out.println("<td>");
				// out.println("<table width='100%' height='100%' border='0'>");
				// out.println("<tr>");
				// out.println("<td width='156' rowspan='3'><img src='displayImg?id="+pro.getPId()+"' /></td>");
				// out.println("<td width='76' height='40'>产品id：</td>");
				// out.println("<td width='169'>"+pro.getPId()+"</td>");
				// out.println("<td width='57'>产品名：</td>");
				// out.println("<td width='192'>"+tools.Str.isoToGb(pro.getPName())+"</td>");
				// out.println("</tr>");
				// out.println("<tr>");
				// out.println("<td height='41'>出厂日期：</td>");
				// out.println("<td>"+date+"</td>");
				// out.println("<td>库存：</td>");
				// out.println("<td>"+pro.getPAmount()+"</td>");
				// out.println("</tr>");
				// out.println("<tr>");
				// out.println("<td height='39'>单价：</td>");
				// out.println("<td>"+pro.getPPrice()+"</td>");
				// out.println("<td>厂商:</td>");
				// out.println("<td>"+tools.Str.isoToGb(pro.getPFactory())+"</td>");
				// out.println("</tr>");
				// out.println("<tr>");
				// out.println("<td>详细描述：</td>");
				// out.println("<td colspan='4'>"+tools.Str.isoToGb(pro.getPDesc())+"</td>");
				// out.println("</tr>");
				// out.println("</table>");
				out.println("详情...");
				out.println("</td>");
				out.println("</tr>");

			}
			else
			{
				out.println("<tr valign='top'>");
				out.println("<td>");
				out.println("没有商品");
				out.println("</td>");
				out.println("</tr>");
			}
		}
		catch (SQLException e)
		{

		}

		// 导航
		out.println("<tr>");
		out.println("<td colspan=4>");
		// 计算总的页数

		int pages = totalpage % itemsperpage == 0 ? totalpage / itemsperpage : totalpage / itemsperpage + 1;

		// for(int i=1;i<=pages;i++){
		// out.println("<label style='text-decoration:underline;' onclick=\"showpagebyper('"+i+"','"+type+"');\">"+i+"</label>");
		// }
		out.println("<label onclick=\"showpagebyper(1,'" + type + "');\" onmouseover='this.style.cursor=\"hand\";' >首页</label>");
		if (curPage != 1)
		{
			out.println("<label onclick=\"showpagebyper('" + (curPage - 1) + "','" + type + "');\" onmouseover='this.style.cursor=\"hand\";' >上一页</label>");
		}
		if (curPage != pages)
		{
			out.println("<label onclick=\"showpagebyper('" + (curPage + 1) + "','" + type + "');\" onmouseover='this.style.cursor=\"hand\";' >下一页</label>");
		}
		out.println("<label onclick=\"showpagebyper('" + pages + "','" + type + "');\" onmouseover='this.style.cursor=\"hand\";'>尾页</label>");
		out.println("共<font color='red'>" + totalpage + "</font>个商品&nbsp;&nbsp;共<font color='red'>" + pages + "</font>页&nbsp当前第<font color='red'>" + curPage + "</font>页");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.flush();
		out.close();
	}

}
