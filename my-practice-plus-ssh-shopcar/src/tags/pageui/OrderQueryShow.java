/**
 * 文件：orderQueryShow.java
 * 说明：订单查询的显示ui
 * 时间：08-05-19
 * 编写：tarena
 */
package tags.pageui;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import global.DataAccessImpl;
import global.TableInfo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import businesses.tools.CommonTools;
import businesses.tools.QueryTools;
import businesses.tools.ShowPage;

public class OrderQueryShow extends BodyTagSupport {

	private DataAccessImpl dataAccess = DataAccessImpl.newInstance();
	private String queryPage = "orderquery.jsp";
	
	public String getQueryPage() {
		return queryPage;
	}
	public void setQueryPage(String queryPage) {
		this.queryPage = queryPage;
	}
	/**
	 * 说明：订单查询
	 * 简单分页技术的实现,此处由于时间关系,没有将分页封装成类
	 * 在oracle数据库中查询结果的行号使用伪列ROWNUM表示（从1开始）。
	 * 例如select * from employee where rownum<10 返回前10条记录。但注意rownum是在查询之后排序之前赋值的
	 * mySQL可以使用LIMIT子句： select name, birthday from employee order by birthday LIMIT 99,20 
	 * 		limit 99,20 表示查询结果从99行后开始,返回20行的结果 即返回 100-119
	 * DB2有rownumber()函数用于获取当前行数。 
	 */
	
	public int doEndTag() throws JspException {
		try {
			ServletRequest request = pageContext.getRequest();
			JspWriter out = pageContext.getOut();
			ServletResponse response = pageContext.getResponse();
			response.setContentType("text/html;charset=gb2312");
			/**
			 * 一些约定：
			 * pageResult:每页显示的记录条数,默认10
			 * currPage:当前页,默认1
			 * totalPage:全部的页数
			 * totalResult:总记录数
			 * urlAddress:显示分页时连接的页面
			 */
			int pageResult=10;
			int currPage = 1;
			int totalPage = 1;
			int totalResult = 0;
			String urlAddress =queryPage;
			String uid = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			//从session中取到用户名,根据用户名查询用户id
			//String uname = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			//String uid = queryTools.getColumnValue(dataAccess, TableInfo.USER_uid, TableInfo.TABLE_UserInfo, TableInfo.USER_uname+"="+uname);
			
			String pname = request.getParameter(TableInfo.PROT_pname);
			String pdescription = request.getParameter(TableInfo.PROT_pdescription);
			String tshipdate = request.getParameter(TableInfo.TRAN_tshipdate);
			String page = request.getParameter("page");
			//防止传过来空值
			if(pname!=null && pname.equals(""))
				pname = null;
			if(pdescription!=null && pdescription.equals(""))
				pdescription = null;
			if(tshipdate!=null && tshipdate.equals(""))
				tshipdate = null;
			
			currPage = CommonTools.StringToInt(page);
			if(currPage == -1)
				currPage = 1;
			if(pname!=null)
				urlAddress += "?"+TableInfo.PROT_pname+"="+pname;
			if(pdescription!=null)
				urlAddress += (urlAddress.indexOf("?")==-1?"&":"?")+TableInfo.PROT_pdescription+"="+pdescription;
			if(tshipdate!=null)
				urlAddress += (urlAddress.indexOf("?")==-1?"&":"?")+TableInfo.TRAN_tshipdate+"="+tshipdate;
			
			//查询总记录数,计算总页面数
			String c_sql ="select count("+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tid+") as total";
			c_sql +=" from "+TableInfo.TABLE_Products+","+TableInfo.TABLE_Transactions+" where ";
			c_sql += TableInfo.TABLE_Products+"."+TableInfo.PROT_pid+"="+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tpid+" and ";
			c_sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tuid+"="+uid+" and ";
			c_sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tshiped+"=0";
			if(tshipdate!=null)
				c_sql +=" and "+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tshipdate+"='"+tshipdate+"'";
			if(pname!=null)
				c_sql +=" and "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pname+" like '%"+pname+"%'";
			if(pdescription!=null)
				c_sql +=" and "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pdescription+" like '%"+pdescription+"%'";
			System.out.println("查询总记录数的sql语句:\n"+c_sql);
			ResultSet rs = dataAccess.queryBySQL(c_sql);
			if(rs.next()){
				totalResult = rs.getInt("total");
				System.out.println("come-->"+totalResult);
				totalPage = totalResult%pageResult==0?totalResult/pageResult:totalResult/pageResult+1;
				//输入的页数大于总页数时,显示第1页
				if(currPage>totalPage)
					currPage = 1;
				//查询符合条件的记录
				//查询结果：商品名称,商品价格,商品折扣率,交易id,交易商品的数量
				String sql ="select "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pname+",";//商品名称
				sql += TableInfo.TABLE_Products+"."+TableInfo.PROT_pprice+",";
				sql += TableInfo.TABLE_Products+"."+TableInfo.PROT_pdiscount+",";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tid+",";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tamount+",";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tdate+",";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_ttime;
				sql +=" from "+TableInfo.TABLE_Products+","+TableInfo.TABLE_Transactions+" where ";
				sql += TableInfo.TABLE_Products+"."+TableInfo.PROT_pid+"="+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tpid+" and ";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tuid+"="+uid+" and ";
				sql += TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tshiped+"=0";
				if(tshipdate!=null)
					sql +=" and "+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tshipdate+"='"+tshipdate+"'";
				if(pname!=null)
					sql +=" and "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pname+" like '%"+pname+"%'";
				if(pdescription!=null)
					sql +=" and "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pdescription+" like '%"+pdescription+"%'";
				sql += " order by "+TableInfo.TABLE_Transactions+"."+TableInfo.TRAN_tid+" desc";
				sql += " limit "+((currPage-1)*pageResult)+","+pageResult;
				System.out.println("查询记录的语句\n"+sql);
				rs = dataAccess.queryBySQL(sql);
				
				out.println("<table width='90%' border='1' cellspacing='0' cellpadding='0' style='font-size:12px'>");
				out.println("<tr>");
				out.println("<td width='5%' height='24' align='center' valign='middle'>订单号</td>");
				out.println("<td width='30%' align='center' valign='middle'>商品名称</td>");
				out.println("<td width='8%' align='center' valign='middle'>数量</td>");
				out.println("<td width='8%' align='center' valign='middle'>单价</td>");
				out.println("<td width='8%' align='center' valign='middle'>折扣率</td>");
				out.println("<td width='10%' align='center' valign='middle'>金额</td>");
				out.println("<td width='15%' align='center' valign='middle'>交易日期</td>");
				out.println("<td width='8%' align='center' valign='middle'>订单修改</td>");
				out.println("<td width='8%' align='center' valign='middle'>不购买</td>");
				out.println("</tr>");
				if(totalResult == 0)
					out.println("<tr><td height='20' align='center' valign='middle' colspan='9'>暂无订单!</td></tr>");
				while(rs.next()){
					out.println("<tr>");
					out.println("<td height='20' align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tid)+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pname)+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tamount)+"</td>");
					out.println(" <td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pprice)+"</td>");
					out.println(" <td align='center' valign='middle'>"+CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pdiscount))/10+"</td>");
					out.println("<td align='center' valign='middle'>"+CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pprice))*CommonTools.StringToInt(rs.getString(TableInfo.PROT_pdiscount))*CommonTools.StringToInt(rs.getString(TableInfo.TRAN_tamount))/100+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tdate)+" "+rs.getString(TableInfo.TRAN_ttime)+"</td>");
					out.println("<td align='center' valign='middle'><a href='ordermodify.jsp?orderid="+rs.getString(TableInfo.TRAN_tid)+"'>修改</a></td>");
					out.println("<td align='center' valign='middle'><a href='javascript:isDeleteOrder(\"order_manager.jsp?method=delete&orderid="+rs.getString(TableInfo.TRAN_tid)+"\")'>删除</a></td>");
					out.println("</tr>");
					//javascript:isDeleteOrder(url)
				} 
				out.println("</table><br>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
					
			}else
				out.println("发生错误,请联系管理员!");
			out.flush();	
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
}
