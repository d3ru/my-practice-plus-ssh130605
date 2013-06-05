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

public class ShowPage extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4802903316120623242L;
	private final static int itemsperpage = 4;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 获取类型
		String type = request.getParameter("type");
		String key = request.getParameter("key");
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
		IDataAccess ida = DataAccessImpl.newInstance();
		ResultSet rs = ida.queryBySQL(sql);
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		try
		{
			if (rs.next())
			{
				System.out.println(rs.getDate("pdate"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(df);
				out.println("<tr valign='top'>");
				out.println("<td>");
				out.println("<table width='100%' height='100%' border='0'>");
				out.println("<tr>");
				out.println("<td width='156' rowspan='3'><img src='displayImg?id=" + rs.getInt("pid") + "' /></td>");
				out.println("<td width='76' height='40'>产品id：</td>");
				out.println("<td width='169'>" + rs.getInt("pid") + "</td>");
				out.println("<td width='57'>产品名：</td>");
				out.println("<td width='192'>" + global.Str.isoToGb(rs.getString("pname")) + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td height='41'>出厂日期：</td>");
				out.println("<td>" + date + "</td>");
				out.println("<td>库存：</td>");
				out.println("<td>" + rs.getInt("pamount") + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td height='39'>单价：</td>");
				out.println("<td>" + rs.getDouble("pprice") + "</td>");
				out.println("<td>备注:</td>");
				out.println("<td>" + global.Str.isoToGb(rs.getString("pnotes")) + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>详细描述：</td>");
				out.println("<td colspan='4'>" + global.Str.isoToGb(rs.getString("pdescription")) + "</td>");
				out.println("</tr>");
				out.println("</table>");
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
		// out.println("<tr>");
		// out.println("<td colspan=4>");
		// 计算总的页数

		// int pages=totalpage%itemsperpage==0?totalpage/itemsperpage:totalpage/itemsperpage+1;
		//
		// // for(int i=1;i<=pages;i++){
		// //
		// out.println("<label style='text-decoration:underline;' onclick=\"showpagebyper('"+i+"','"+type+"');\">"+i+"</label>");
		// // }
		// out.println("<label onclick=\"showpagebyper(1,'"+type+"');\" onmouseover='this.style.cursor=\"hand\";' >首页</label>");
		// if(curPage!=1){
		// out.println("<label onclick=\"showpagebyper('"+(curPage-1)+"','"+type+"');\" onmouseover='this.style.cursor=\"hand\";' >上一页</label>");
		// }
		// if(curPage!=pages){
		// out.println("<label onclick=\"showpagebyper('"+(curPage+1)+"','"+type+"');\" onmouseover='this.style.cursor=\"hand\";' >下一页</label>");
		// }
		// out.println("<label onclick=\"showpagebyper('"+pages+"','"+type+"');\" onmouseover='this.style.cursor=\"hand\";'>尾页</label>");
		// out.println("共<font color='red'>"+totalpage+"</font>个商品&nbsp;&nbsp;共<font color='red'>"+pages+"</font>页&nbsp当前第<font color='red'>"+curPage+"</font>页");
		// out.println("</td>");
		// out.println("</tr>");
		// out.println("</table>");
		out.flush();
		out.close();
	}

}
