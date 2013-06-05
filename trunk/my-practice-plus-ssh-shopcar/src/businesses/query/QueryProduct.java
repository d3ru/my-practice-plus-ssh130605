/**
 *  文件：queryProduct.java
 *  说明：实现查询及高级查询
 *  时间：08-05-20
 *  编写：tarena
 */
package businesses.query;

import global.DataAccessImpl;
import global.TableInfo;

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
import javax.servlet.http.HttpSession;

import businesses.tools.CommonTools;
import businesses.tools.QueryTools;
import businesses.tools.ShowPage;

public class QueryProduct extends HttpServlet {

	private HttpSession session=null;
	private DataAccessImpl dataAccess =null;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	private String page;
	private String queryType;
	private String productType;
	private String queryCondition;
	private String pDate;
	private String pPricex1;
	private String pPricex2;
	private String pDiscount;
	
	/**
	 * 一些约定：
	 * pageResult:每页显示的记录条数,默认10
	 * currPage:当前页,默认1
	 * totalPage:全部的页数
	 * totalResult:总记录数
	 * urlAddress:显示分页时连接的页面
	 */
	private int pageResult=1;
	private int currPage = 1;
	private int totalPage = 1;
	private int totalResult = 1;
	public QueryProduct() {
		super();
	}

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			this.request = request;
			this.response = response;
			session = request.getSession();
			dataAccess = DataAccessImpl.newInstance();
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			
			//querytype   	查询类型  0  根据商品名查询   1  详细查询
			//productype	查询的商品类别
			//querycondition查询条件//		pname	商品名称
			//其他			根据不同类型的查询字段与数据表字段同
			//pdate	商品生产日期 --
			//pprice	商品价格 --
			//pdiscount	折扣率 0-100
			//ptypeid	商品类别
			//页数
			page = request.getParameter("page");
			queryType = request.getParameter("querytype");
			productType = request.getParameter("productype");
			queryCondition = request.getParameter("querycondition");
			
			pDate = request.getParameter(TableInfo.PROT_pdate);
			pPricex1 = request.getParameter("price1");
			pPricex2 = request.getParameter("price2");
			pDiscount = request.getParameter(TableInfo.PROT_pdiscount);
			if(queryType!=null && queryType.equals(""))
				queryType = null;
			if(pDate!=null && pDate.equals(""))
				pDate = null;
			if(pPricex1!=null && pPricex1.equals(""))
				pPricex1 = null;
			if(pPricex2!=null && pPricex2.equals(""))
				pPricex2 = null;
			if(pDiscount!=null && pDiscount.equals(""))
				pDiscount = null;
			currPage = CommonTools.StringToInt(page);
			if(currPage == -1)
				currPage = 1;
			
			if(queryCondition == null ||(queryCondition!=null && queryCondition.equals(""))){				
				CommonTools.Error(request,response,session,"请输入查询关键字!");
				return;
			}
			else{
				queryCondition=new String(queryCondition.getBytes("iso-8859-1"),"gb2312");
			}
			//构建查询语句与显示分页时查询的连接地址
			String urlAddress ="query.jsp";
			String[] sql = returnQuerySQL(1);
			urlAddress += sql[1];
			ResultSet rss = dataAccess.queryBySQL(sql[0]);
			if(rss.next()){
				totalResult = rss.getInt("total");
				totalPage = totalResult%pageResult==0?totalResult/pageResult:totalResult/pageResult+1;
				//输入的页数大于总页数时,显示第1页
				if(currPage>totalPage)
					currPage = 1;
				sql = returnQuerySQL(2);
				ResultSet rs = dataAccess.queryBySQL(sql[0]);
//				out.println("<table width='90%' border='1' cellspacing='0' cellpadding='0' style='font-size:12px'>");
//				out.println("<tr>");
//				out.println("<td width='5%' height='24' align='center' valign='middle'>商品id</td>");
//				out.println("<td width='20%' align='center' valign='middle'>商品名称</td>");
//				out.println("<td width='10%' align='center' valign='middle'>商品类别</td>");
//				out.println("<td width='5%' align='center' valign='middle'>商品数量</td>");
//				out.println("<td width='5%' align='center' valign='middle'>单价</td>");
//				out.println("<td width='5%' align='center' valign='middle'>折扣率</td>");
//				out.println("<td width='30%' align='center' valign='middle'>商品描述</td>");
//				out.println("<td width='10%' align='center' valign='middle'>商品备注</td>");
//				out.println("<td width='10%' align='center' valign='middle'>生产日期</td>");
//				out.println("</tr>");
				out.println("<div style='overflow:scroll;height:300px;'>");
				out.println("<table>");
				if(totalResult == 0)
					out.println("<tr><td height='20' align='center' valign='middle'>暂无符合您查询的商品!</td></tr>");
				while(rs.next()){
					int id=rs.getInt("pid");
					//pid-->商品ID//pname-->商品名称//pdescription-->商品描述//pdate-->商品生产日期//pprice-->商品价格
					//pamount-->商品数量//pnotes-->商品备注//pdiscount-->折扣率 0-100//ptypeid-->商品类别
//					out.println("<tr>");
//					out.println("<td height='20' align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pid)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pname)+"</td>");
//					out.println("<td align='center' valign='middle'>"+queryTools.getColumnValue(dataAccess, TableInfo.TYPE_ptname, TableInfo.TABLE_ProductType, TableInfo.TYPE_ptid+"="+rs.getString(TableInfo.PROT_ptypeid))+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pamount)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pprice)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pdiscount)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pdescription)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pnotes)+"</td>");
//					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pdate)+"</td>");
//					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>");
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String date=df.format(rs.getDate("pdate"));
					out.println("<table width='100%' height='100%' border='0'>");
					out.println("<tr>");
					out.println("<td width='156' rowspan='4'><img src='displaypphoto.do?pid="+rs.getInt(1)+"' width='70' height='90' border='0'/></td>");
					out.println("<td width='76' height='25'>产品id：</td>");
					out.println("<td width='169'>"+id+"</td>");
					System.out.println(rs.getInt("pid"));
					out.println("<td width='57'>产品名：</td>");
					out.println("<td width='192'>"+rs.getString("pname"));
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25'>出厂日期：</td>");
					out.println("<td>"+date+"</td>");
					out.println("<td>库存：</td>");
					out.println("<td>"+rs.getInt("pamount")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25'>单价：</td>");
					out.println("<td>"+rs.getDouble("pprice")+"</td>");
					out.println("<td>商品备注:</td>");
					out.println("<td>"+rs.getString("pnotes")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>详细描述：</td>");
					out.println("<td colspan='2'>"+rs.getString("pdescription")+"</td>");
					//out.println("</tr>");
					//out.println("<tr>");
					out.println("<td><button class='bstyle' onclick=\"ShopCarts("+id+",1);\" />加入购物车</button></td>");
					out.println("</tr>");
					out.println("<hr color='white' style='width:576px;'>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				} 
				out.println("</table>");
				out.println("</div>");
				//调用显示分页
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
			}else{
				CommonTools.Error(request,response,session,"发生错误,请联系管理员!");
				return;
			}

			out.flush();
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String[] returnQuerySQL(int n){
		
		String sql = "";
		String urlAddress = "";
		if(n==1)
			sql += "select count(*) as total from "+TableInfo.TABLE_Products+" where ";
		if(n==2)
			sql += "select * from "+TableInfo.TABLE_Products+" where ";
		
		urlAddress += "?querycondition="+queryCondition;
		sql += TableInfo.PROT_pname+" like '%"+queryCondition+"%'";
		
		if(queryType!=null && queryType.equals("1")){
			urlAddress += "&querytype="+queryType;
			
			//交易日期
			if(pDate !=null){
				urlAddress += "&"+TableInfo.PROT_pdate+"="+pDate;
				sql += " and "+TableInfo.PROT_pdate+"='"+pDate+"'";
			}
			//价格范围,考虑只输入一个的情况和2个都输入的情况
			System.out.println(pPricex1+"==="+pPricex2);
			if(pPricex1!=null && pPricex2!=null){
				int pPrice1 = CommonTools.StringToInt(pPricex1);
				int pPrice2 = CommonTools.StringToInt(pPricex2);
				if(pPrice1==-1 || pPrice2==-1)
					CommonTools.Error(request,response,session,"请确保输入的商品金额正确!");
				urlAddress += "&price1="+pPrice1+"&price2="+pPrice2;
				sql += " and "+TableInfo.PROT_pprice+" between "+pPrice1+" and "+pPrice2;
			}
			if(pPricex1!=null && pPricex2==null){
				int pPrice1 = CommonTools.StringToInt(pPricex1);
				if(pPrice1==-1)
					CommonTools.Error(request,response,session,"请确保输入的商品金额正确!");
				urlAddress += "&price1="+pPrice1;
				sql += " and "+TableInfo.PROT_pprice+">="+pPrice1;
			}
			if(pPricex2!=null && pPricex1==null){
				int pPrice2 = CommonTools.StringToInt(pPricex2);
				if(pPrice2==-1)
					CommonTools.Error(request,response,session,"请确保输入的商品金额正确!");
				urlAddress += "&price2="+pPrice2;
				sql += " and "+TableInfo.PROT_pprice+"<="+pPrice2;
			}
			//折扣率
			if(pDiscount!=null){
				int pDiscounts = CommonTools.StringToInt(pDiscount);
				if(pDiscounts==-1)
					CommonTools.Error(request,response,session,"请确保输入的折扣率正确!");
				urlAddress +="&"+TableInfo.PROT_pdiscount+"="+pDiscount;
				sql +=" and "+TableInfo.PROT_pdiscount+"="+pDiscount;
			}
			
		}
		//商品类别
		if(productType!=null){
			int typeid = CommonTools.StringToInt(productType);
			if(typeid==-1)
				CommonTools.Error(request, response, session,"请确保输入的商品类别正确!");
			//约定999为所有商品类型
			if(typeid!=999){
				urlAddress += "&productype="+productType;
				sql += " and "+TableInfo.PROT_ptypeid+"="+typeid;
			}
		}
		if(n==2)
			sql +=" limit "+((currPage-1)*pageResult)+","+pageResult;
		System.out.println("查询记录的语句\n"+sql);
		return new String[]{sql,urlAddress};
	}

}
