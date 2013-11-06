package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.TableInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HighQuery extends BodyTagSupport
{
	@Override
	public int doEndTag() throws JspException
	{
		ServletRequest request = pageContext.getRequest();
		// TODO Auto-generated method stub
		// 获取原来是否存在的查询条件
		String queryType = request.getParameter("querytype");
		String productType = request.getParameter("productype");
		String queryCondition = request.getParameter("querycondition");// 有可能出现汉字，需要解码。

		String pDate1 = request.getParameter("pdate1");
		String pDate2 = request.getParameter("pdate2");
		String pPrice1 = request.getParameter("price1");
		String pPrice2 = request.getParameter("price2");
		String pDiscount1 = request.getParameter("pdiscount1");
		String pDiscount2 = request.getParameter("pdiscount2");

		queryType = queryType == null ? "" : queryType;
		productType = productType == null ? "" : productType;
		queryCondition = queryCondition == null ? "" : queryCondition;

		pDate1 = pDate1 == null ? "" : pDate1;
		pDate2 = pDate2 == null ? "" : pDate2;
		pPrice1 = pPrice1 == null ? "" : pPrice1;
		pPrice2 = pPrice2 == null ? "" : pPrice2;
		pDiscount1 = pDiscount1 == null ? "" : pDiscount1;
		pDiscount2 = pDiscount2 == null ? "" : pDiscount2;
		try
		{
			queryCondition = new String(queryCondition.getBytes("iso-8859-1"), "gb2312");
		}
		catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JspWriter out = pageContext.getOut();
		try
		{
			out.println("<label style='text-align:center; font-weight:bold'>商品高级搜索</label>");
			out.println("</label><br>");
			out.println("<label>商品名称:&nbsp;&nbsp;<input name='querycondition' style='width:116px;' type='text' value='" + queryCondition + "'/>");
			out.println("</label><br>");
			out.println("<label>商品类型:");
			out.println("&nbsp;<select name='productype' style='width:116px;'>");
			String sql = "select * from " + TableInfo.TABLE_ProductType;
			IDataAccess ida = DataAccessImpl.newInstance();
			ResultSet rs = ida.queryBySQL(sql);
			while (rs.next())
			{
				int id = rs.getInt(1);
				String ptname = rs.getString(2);
				if (("" + id).equals(productType))
				{
					out.println("<option value='" + id + "'  checked>");
					out.println(ptname);
					out.println("</option>");
				}
				else
				{
					out.println("<option value='" + id + "'>");
					out.println(ptname);
					out.println("</option>");
				}
			}
			out.println("</select>");
			out.println("</label><br>");
			out.println("<label>生产日期:&nbsp;&nbsp;<input name='pdate1' type='text' style='width:48px;' value='" + pDate1 + "'/> -");
			out.println("<input name='pdate2' type='text' style='width:50px;' value='" + pDate2 + "'/>");
			out.println("</label><br>");
			out.println("<label>价格范围:&nbsp;&nbsp;<input name='price1' type='text' style='width:48px;' value='" + pPrice1 + "'/> -");
			out.println("<input name='price2' type='text' style='width:50px;' value='" + pPrice2 + "'/></label><br>");
			out.println("<label>折&nbsp;扣&nbsp;率:&nbsp;&nbsp;<input name='pdiscount1' type='text'  style='width:48px;' value='" + pDiscount1 + "'/> -");
			out.println("<input name='pdiscount2' type='text' style='width:50px;' value='" + pDiscount2 + "'/>");
			out.println("</label><br/><br/>");
			out.println("<label>&nbsp;&nbsp;&nbsp;<input type='hidden' name='querytype'  value='1'/><input type='button' onclick='highquery();' value='提交' />");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='reset' name='Submit3' value='重置' /></label>");
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
