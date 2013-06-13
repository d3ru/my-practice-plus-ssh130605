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

public class ListProrityProduct extends BodyTagSupport {
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		try {
			IDataAccess ida=DataAccessImpl.newInstance();
			String sql="select * from "+TableInfo.TABLE_ProductType;
			ResultSet rs=ida.queryBySQL(sql);
			int i=0;
			while(rs.next()){
				int id=rs.getInt(1);
				String ptname=rs.getString(2);
				String sql2="select * from "+TableInfo.TABLE_Products+" where "+TableInfo.PROT_pprority+"=1 and "+TableInfo.PROT_ptypeid+"='"+id+"'";
				ResultSet rs2=ida.queryBySQL(sql2);
				out.println(ptname+"推荐列表：");
				
				out.println("<div style='height:92;overflow:auto;width:580;'>");
				out.println("<table height='80%' cellspacing='0' cellpadding='0'>");
				out.println("<tr valign='bottom'>");
				if(rs2.next()){
					out.println("<td>");
					out.println("<img src='displaypphoto.do?pid="+rs2.getInt(1)+"' width='70' height='70' border='0'/>");
					out.println("</td>");
					//最多显示4个商品
					int max=0;
					while(rs2.next() &&  max++<4){
						out.println("<td>");
						out.println("<img valign='bottom' src='displaypphoto.do?pid="+rs2.getInt(1)+"' width='70' height='70' border='0'/>");
						out.println("</td>");
					}
					out.println("<td>");
					out.println("&nbsp;&nbsp;&nbsp;&nbsp;<a href='listproductbytype.jsp?typeid="+id+"'>>>更多...</a>");
					out.println("</td>");
				}else{
					out.println("<td>");
					out.println("<br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span align='center' height='92' style='color:red;'>此类中暂无商品</span>");
					out.println("</td>");
				}
				out.println("</tr>");
				out.println("</table>");
				
				out.println("</div>");
				out.println("</hr color='white' style='width:576px;'>");
			}
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
