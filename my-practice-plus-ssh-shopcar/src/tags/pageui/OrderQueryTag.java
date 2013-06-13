package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.Str;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OrderQueryTag extends BodyTagSupport {
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		try {
			out.println("<form id='form2' name='form2' method='post' action='orderquery.do'>");
			out.println("<label style='text-align:center; font-weight:bold'>个人订单查询</label><br/>");
			out.println("<label>商品名称:&nbsp;&nbsp;<input name='querycondition' style='width:110px;' type='text' />");  
			out.println("</label><br>");
			out.println("<label>商品类型:");
			out.println("&nbsp;<select name='productype' style='width:110px;'>");
			String sql="select * from producttype";
			IDataAccess ida=DataAccessImpl.newInstance();
			ResultSet rs=ida.queryBySQL(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String ptname=rs.getString(2);
				out.println("<option value='"+id+"'>");
				out.println(Str.isoToGb(ptname));
				out.println("</option>");
			}
			out.println("</select>");
			out.println("</label><br>");
			out.println("<label>交易日期:&nbsp;&nbsp;<input name='pdate' type='text' style='width:110px;' />");
			out.println("</label><br>");
			out.println("<label>&nbsp;&nbsp;&nbsp;<input type='submit' name='Submit2' value='提交' />");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='reset' name='Submit3' value='重置' /></label>");
			out.println("<input type='hidden' name='querytype'  value='1'/>");
			out.println("</form>");
			
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
