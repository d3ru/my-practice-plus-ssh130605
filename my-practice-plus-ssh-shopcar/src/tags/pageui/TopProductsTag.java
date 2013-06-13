package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.Str;
import global.TableInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TopProductsTag extends BodyTagSupport {
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		try {
			String sql="select * from "+TableInfo.TABLE_ProductType;
			IDataAccess ida=DataAccessImpl.newInstance();
			ResultSet rs=ida.queryBySQL(sql);
			int i=0;
			while(rs.next()){
				out.println("<option value='"+rs.getInt("ptypeid")+"'");
				i++;
			}
			out.println("<table border='1' cellspacing='0' cellpadding='0'  width='100%'>");
			out.println("<tr valign='bottom'><td>");
			out.println("<table border='0' width='100%'  cellspacing='0' cellpadding='0'>");
			out.println("<tr height='14' valign='buttom' >");
			out.println("<td width='90' height='10' align='center'  style='border-bottom-style: none; border-bottom-color: #FFFFFF; border-bottom:none; border-bottom-width:0px;' background='images/2.gif'>::商品搜索::</td>");
			out.println("<td width='90' background='images/2.gif'><div align='center'>请输入关键字</div></td>");
			out.println("<td width='220' background='images/2.gif'><div align='center'>");
			out.println("<input type='text' style='border-style: none;height:15px;' id='key' name='key' style='width:220px'/>");
			out.println("</div></td>");
			out.println("<td background='images/2.gif' width='58' valign='middle' align='center'>");
			out.println("<input type='button' style='height:16px;border:1px;border-color: black;border-style: groove;border-width: 1px;' onclick=\"showname('"+null+"','"+null+"');\"   value='搜索'/>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</td>");
			out.println("</tr>");
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
