/**
 * 文件：TransactionQuery.java 说明：交易记录查询 时间：08-05-20 编写：tarena
 */
package tags.pageui;

import global.DataAccessImpl;
import global.TableInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import businesses.tools.CommonTools;
import businesses.tools.ShowPage;

public class TransactionsQueryShow extends BodyTagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373609490215349L;
	private final DataAccessImpl dataAccess = DataAccessImpl.newInstance();
	private String queryPage = "transactionquery.jsp";

	public String getQueryPage()
	{
		return queryPage;
	}

	public void setQueryPage(String queryPage)
	{
		this.queryPage = queryPage;
	}

	@Override
	public int doEndTag() throws JspException
	{
		try
		{
			ServletRequest request = pageContext.getRequest();
			JspWriter out = pageContext.getOut();
			ServletResponse response = pageContext.getResponse();
			response.setContentType("text/html;charset=gb2312");
			/**
			 * 一些约定： pageResult:每页显示的记录条数,默认10 currPage:当前页,默认1 totalPage:全部的页数 totalResult:总记录数 urlAddress:显示分页时连接的页面
			 */
			int pageResult = 10;
			int currPage = 1;
			int totalPage = 1;
			int totalResult = 1;
			String urlAddress = queryPage;

			String uid = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			// String uname = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			// String uid = queryTools.getColumnValue(dataAccess, TableInfo.USER_uid, TableInfo.TABLE_UserInfo,
			// TableInfo.USER_uname+"="+uname);
			// 商品名称
			String pname = request.getParameter(TableInfo.PROT_pname);
			// 商品描述
			String pdescription = request.getParameter(TableInfo.PROT_pdescription);
			// 交易日期
			String tshipdate = request.getParameter(TableInfo.TRAN_tshipdate);
			// 防止传过来空值
			if (pname != null && pname.equals(""))
				pname = null;
			if (pdescription != null && pdescription.equals(""))
				pdescription = null;
			if (tshipdate != null && tshipdate.equals(""))
				tshipdate = null;
			// 页数
			String page = request.getParameter("page");
			// 查询交易记录类型
			int tshiped = 0;
			String[] tshipeds = request.getParameterValues(TableInfo.TRAN_tshiped);
			// 不为空,说明是来自transactionquery.jsp页面提交,否则是通过url提交过来的
			if (tshipeds != null)
			{
				if (tshipeds.length == 0 || tshipeds.length == 2)
					tshiped = 3;
				else
					tshiped = CommonTools.StringToInt(tshipeds[0]);
			}
			else
			{
				String tshipedx = request.getParameter("tshipe");
				tshiped = CommonTools.StringToInt(tshipedx);
				// 如果=3时表示查询已经发货和已经签收的商品
				if (tshiped == -1 || tshiped > 2)
					tshiped = 3;
			}

			currPage = CommonTools.StringToInt(page);
			if (currPage == -1)
				currPage = 1;
			// urlAddress += "?page="+currPage;
			if (pname != null)
				urlAddress += "?" + TableInfo.PROT_pname + "=" + pname;
			if (pdescription != null)
				urlAddress += (urlAddress.indexOf("?") == -1 ? "&" : "?") + TableInfo.PROT_pdescription + "=" + pdescription;
			if (tshipdate != null)
				urlAddress += (urlAddress.indexOf("?") == -1 ? "&" : "?") + TableInfo.TRAN_tshipdate + "=" + tshipdate;
			urlAddress += "&tshipe=" + tshiped;

			// 查询总记录数,计算总页面数
			String c_sql = "select count(" + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tid + ") as total";
			c_sql += " from " + TableInfo.TABLE_Products + "," + TableInfo.TABLE_Transactions + " where ";
			c_sql += TableInfo.TABLE_Products + "." + TableInfo.PROT_pid + "=" + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tpid + " and ";
			c_sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tuid + "=" + uid + " and ";
			if (tshiped == 3)
				c_sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshiped + ">0";
			else
				c_sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshiped + "=" + tshiped;
			if (tshipdate != null)
				c_sql += " and " + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshipdate + "='" + tshipdate + "'";
			if (pname != null)
				c_sql += " and " + TableInfo.TABLE_Products + "." + TableInfo.PROT_pname + " like '%" + pname + "%'";
			if (pdescription != null)
				c_sql += " and " + TableInfo.TABLE_Products + "." + TableInfo.PROT_pdescription + " like '%" + pdescription + "%'";
			System.out.println("查询总记录数的sql语句:\n" + c_sql);
			ResultSet rs = dataAccess.queryBySQL(c_sql);
			if (rs.next())
			{
				totalResult = rs.getInt("total");
				System.out.println("come-->" + totalResult);
				totalPage = totalResult % pageResult == 0 ? totalResult / pageResult : totalResult / pageResult + 1;
				// 输入的页数大于总页数时,显示第1页
				if (currPage > totalPage)
					currPage = 1;
				// 查询符合条件的记录
				// 查询结果：商品名称,商品价格,商品折扣率,交易id,交易商品的数量,交易日期,交易时间,商品状态
				String sql = "select " + TableInfo.TABLE_Products + "." + TableInfo.PROT_pname + ",";// 商品名称
				sql += TableInfo.TABLE_Products + "." + TableInfo.PROT_pprice + ",";
				sql += TableInfo.TABLE_Products + "." + TableInfo.PROT_pdiscount + ",";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tid + ",";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tamount + ",";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tdate + ",";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_ttime + ",";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshiped;
				sql += " from " + TableInfo.TABLE_Products + "," + TableInfo.TABLE_Transactions + " where ";
				sql += TableInfo.TABLE_Products + "." + TableInfo.PROT_pid + "=" + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tpid + " and ";
				sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tuid + "=" + uid + " and ";
				if (tshiped == 3)
					sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshiped + ">0";
				else
					sql += TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshiped + "=" + tshiped;
				if (tshipdate != null)
					sql += " and " + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tshipdate + "='" + tshipdate + "'";
				if (pname != null)
					sql += " and " + TableInfo.TABLE_Products + "." + TableInfo.PROT_pname + " like '%" + pname + "%'";
				if (pdescription != null)
					sql += " and " + TableInfo.TABLE_Products + "." + TableInfo.PROT_pdescription + " like '%" + pdescription + "%'";
				sql += " order by " + TableInfo.TABLE_Transactions + "." + TableInfo.TRAN_tid + " desc";
				sql += " limit " + ((currPage - 1) * pageResult) + "," + pageResult;
				System.out.println("查询记录的语句\n" + sql);
				rs = dataAccess.queryBySQL(sql);

				out.println("<table width='90%' border='1' cellspacing='0' cellpadding='0' style='font-size:12px'>");
				out.println("<tr>");
				out.println("<td width='8%' height='24' align='center' valign='middle'>交易编号</td>");
				out.println("<td width='35%' align='center' valign='middle'>商品名称</td>");
				out.println("<td width='8%' align='center' valign='middle'>数量</td>");
				out.println("<td width='8%' align='center' valign='middle'>单价</td>");
				out.println("<td width='8%' align='center' valign='middle'>折扣率</td>");
				out.println("<td width='10%' align='center' valign='middle'>金额</td>");
				out.println("<td width='15%' align='center' valign='middle'>交易日期</td>");
				out.println("<td width='8%' align='center' valign='middle'>商品状态</td>");
				out.println("</tr>");
				if (totalResult == 0)
					out.println("<tr><td height='20' align='center' valign='middle' colspan='8'>暂无交易记录!</td></tr>");
				while (rs.next())
				{
					String ttid = rs.getString(TableInfo.TRAN_tid);
					out.println("<tr>");
					out.println("<td height='20' align='center' valign='middle'>" + rs.getString(TableInfo.TRAN_tid) + "</td>");
					out.println("<td align='center' valign='middle'>" + rs.getString(TableInfo.PROT_pname) + "</td>");
					out.println("<td align='center' valign='middle'>" + rs.getString(TableInfo.TRAN_tamount) + "</td>");
					out.println("<td align='center' valign='middle'>" + rs.getString(TableInfo.PROT_pprice) + "</td>");
					out.println("<td align='center' valign='middle'>" + CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pdiscount)) / 10 + "</td>");
					out.println("<td align='center' valign='middle'>" + CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pprice)) * CommonTools.StringToInt(rs.getString(TableInfo.PROT_pdiscount))
							* CommonTools.StringToInt(rs.getString(TableInfo.TRAN_tamount)) / 100 + "</td>");
					out.println("<td align='center' valign='middle'>" + rs.getString(TableInfo.TRAN_tdate) + " " + rs.getString(TableInfo.TRAN_ttime) + "</td>");
					out.println("<td align='center' valign='middle'><div id='isSigned" + ttid + "'>"
							+ (rs.getString(TableInfo.TRAN_tshiped).equals("1") ? "<a href='javascript:productSigned(\"order/shipedchange.jsp?orderid=" + ttid + "\"," + ttid + ")'>已发货</a>" : "已签收") + "</div></td>");
					out.println("</tr>");
					// javascript:isDeleteOrder(url)
				}
				out.println("</table><br>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
			}
			else
				out.println("发生错误,请联系管理员!");
			out.flush();

		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
