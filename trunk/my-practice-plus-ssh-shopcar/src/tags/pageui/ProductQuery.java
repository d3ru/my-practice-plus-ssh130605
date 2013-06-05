package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.Str;
import global.TableInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ProductQuery extends BodyTagSupport {
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		String cond=pageContext.getRequest().getParameter("cond");
		cond=cond==null?"":cond;
		try {
			cond=new String(cond.getBytes("iso-8859-1"),"gb2312");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cond=cond.equals("")?"请输入查询内容":cond;
		try {
			out.println("<table width='100%' border='0' style='border-bottom:none;' cellspacing='0' cellpadding='0'>");
			out.println("<tr>");
			out.println("<td  height='17px' width='16%' background='/shopcart/images/2.gif'>");
			out.println("<p align='center'>::商品搜索::");
			out.println("</td>");
			out.println("<td valign='top' align='right' style='padding-right:15px;' background='/shopcart/images/2.gif'>");
			out.println("<nobr>&nbsp;&nbsp;<select style='z-index:-1;padding-top:-3px;padding-bottom:-8px;height:17px;width:80px;' name='productype'>");
			String sql="select * from "+TableInfo.TABLE_ProductType;
			IDataAccess ida=DataAccessImpl.newInstance();
			ResultSet rs=ida.queryBySQL(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String ptname=rs.getString(2);
				out.println("<option value='"+id+"'>");
				out.println(ptname);
				out.println("</option>");
			}
			out.println("</select>");
			out.println("&nbsp;&nbsp;<input id='querycondition'  name='querycondition' type='text' style='width:200px;' onFocus='this.value=\"\";' style='margin-top:-7px;padding-bottom:-3px;height:19px;' value='"+cond+"'/>");
			out.println("&nbsp;&nbsp;<input id='querytype'  name='querytype' type='hidden' value='0'/>");
			out.println("<input type='button' onclick='query();' value='搜索' style='height:19px;'/>");
			out.println("&nbsp;&nbsp;<input type='button' onclick='location.href(\"query.jsp\");' value='高级搜索' style='height:19px;'/>");
			out.println("</nobr>");
			out.println("</td>");
			out.println("</tr");
			out.println("</table>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
}
