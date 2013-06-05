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

public class ShopMenuTag extends BodyTagSupport {
	private String	submitevent;
	public String getSubmitevent() {
		return submitevent;
	}
	public void setSubmitevent(String submitevent) {
		this.submitevent = submitevent;
	}
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		try {
			//out.println("<menu type='бя'>");
			out.println("<table  border=0>");
			String sql="select * from "+TableInfo.TABLE_ProductType;
			IDataAccess ida=DataAccessImpl.newInstance();
			ResultSet rs=ida.queryBySQL(sql);
			int i=0;
			while(rs.next()){
				int id=rs.getInt(1);
				String ptname=rs.getString(2);
				out.println("<tr>");
				out.println("<td>");
				//out.println("<li id='a"+i+"'>");
				out.println("<table width='191' height='31' border='0' cellpadding='0' cellspacing='0'>");   
				out.println("<tr>");
				out.println("<td width='36' height='31' align='right' valign='top'><img src='images/doc.gif' width='18' height='18'>&nbsp;</td>");
				out.println("<td height='31'>");
				out.println("<span onclick='"+submitevent+"("+id+");' onmouseover=\"this.style.cursor='hand';this.style.color='red';\" onmouseout=\"this.style.cursor='normal';this.style.color='black';\">");
				out.println(ptname);
				out.println("</span>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
				i++;
			}
			//out.println("</menu>");
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
