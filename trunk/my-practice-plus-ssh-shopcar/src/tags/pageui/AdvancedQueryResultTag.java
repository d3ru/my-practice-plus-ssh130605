/**
 *  文件：queryProduct.java
 *  说明：实现查询及高级查询
 *  时间：08-05-20
 *  编写：Tarena
 */
package tags.pageui;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import global.DataAccessImpl;
import global.TableInfo;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import businesses.tools.CommonTools;
import businesses.tools.ShowPage;

public class AdvancedQueryResultTag extends BodyTagSupport {
	private HttpSession session=null;
	private DataAccessImpl dataAccess =null;	
	private HttpServletRequest request;
	private HttpServletResponse response;	
	private String page;
	private String queryType;
	private String productType;
	private String queryCondition;
	private String pDate1,pDate2;
	private String pPrice1,pPrice2;
	private String pDiscount1,pDiscount2;
	
	/**
	 * 一些约定：
	 * pageResult:每页显示的记录条数,默认10
	 * currPage:当前页,默认1
	 * totalPage:全部的页数
	 * totalResult:总记录数
	 * urlAddress:显示分页时连接的页面
	 */
	private int pageResult=3;
	private int currPage = 1;
	private int totalPage = 1;
	private int totalResult = 1;
	public AdvancedQueryResultTag() {
		super();
	}

	
	public int doEndTag() throws JspException{
		try {
			this.request = (HttpServletRequest)pageContext.getRequest();
			//this.response = (HttpServletResponse)pageContext.getResponse();
			session = request.getSession();
			dataAccess = DataAccessImpl.newInstance();
			//response.setContentType("text/html;charset=gb2312");
			JspWriter out=pageContext.getOut();
			
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
			queryCondition = request.getParameter("querycondition");//有可能出现汉字，需要解码。
			
			pDate1 = request.getParameter("pdate1");
			pDate2 = request.getParameter("pdate2");
			pPrice1 = request.getParameter("price1");
			pPrice2 = request.getParameter("price2");
			pDiscount1 = request.getParameter("pdiscount1");
			pDiscount2 = request.getParameter("pdiscount2");
			//判定各查询条件项是否输入
			if(queryType!=null && queryType.equals(""))
				queryType = null;
			//如果没有输入开始日期，则从1970-01-01开始算起.
			if(pDate1==null || pDate1.equals(""))
				pDate1 =  null;
			//如果没有输入结束日期，则商品日期查询到当前日期为准。
			if(pDate2==null || pDate2.equals(""))
				pDate2 =null;			
			if(pPrice1!=null && pPrice1.equals(""))
				pPrice1 = null;			
			if(pPrice2!=null && pPrice2.equals(""))
				pPrice2 = null;
			if(pDiscount1!=null && pDiscount1.equals(""))
				pDiscount1 = null;
			if(pDiscount2!=null && pDiscount2.equals(""))
				pDiscount2 = null;			
			currPage = CommonTools.StringToInt(page);
			if(currPage == -1)
				currPage = 1;
			
			if(queryCondition == null || queryCondition.equals("")){				
				queryCondition=null;
			}
			//else{
			if(queryCondition != null && !queryCondition.equals("")){//解码
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
				//System.out.println("查询ＳＱＬ："+sql[0]);				
				ResultSet rs = dataAccess.queryBySQL(sql[0]);
				out.println("<div style='overflow:auto;height:350px;'>");
				out.println("<table>");
				if(totalResult == 0){
					out.println("<tr><td height='20' align='center' valign='middle'>暂无符合您查询的商品!</td></tr>");
				}
				while(rs.next()){
					int id=rs.getInt("pid");
					out.println("<tr>");
					out.println("<td>");
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String date=df.format(rs.getDate("pdate"));
					out.println("<table width='100%' height='100%' border='0'>");
					out.println("<tr>");
					out.println("<td width='156' rowspan='4'><img src='displaypphoto.do?pid="+rs.getInt(1)+"' width='70' height='90' border='0'/></td>");
					out.println("<td width='76' height='25'>产品id：</td>");
					out.println("<td width='169'>"+id+"</td>");
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
				out.println("<hr color='blue' style='width:576px;'>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
			}else{
				CommonTools.Error(request,response,session,"发生错误,请联系管理员!");
				return super.doEndTag();
			}

			out.flush();
			//out.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return super.doEndTag();
	}
	//根据查询条件生成查询SQL语句与分页查询的连接地址。
	private String[] returnQuerySQL(int n){
		//查询类型，1为统计查询，2为详细查询。
		String sql = "";
		String urlAddress = "?";
		if(n==1)
			sql += "select count(*) as total from "+TableInfo.TABLE_Products+" where ";
		if(n==2)
			sql += "select * from "+TableInfo.TABLE_Products+" where ";
		if(queryCondition!=null){
			urlAddress += "querycondition="+queryCondition;
			sql += TableInfo.PROT_pname+" like '%"+queryCondition+"%' ";
		}
		else{
			urlAddress += "querycondition=";
			sql += TableInfo.PROT_pname+" like '%%' ";
		}
		//queryType查询类型分：高级查询与简单查询
		//1为高级查询，0，或空为简单查询
		if(queryType!=null && queryType.equals("1")){
			urlAddress += "&querytype="+queryType;
			
			//交易日期
			if(pDate1!=null){
				//是否是日期？
				try{
					new SimpleDateFormat("yyyy-MM-dd").parse(pDate1);
					//是日期
					urlAddress += "&pdate1="+pDate1;
					sql += " and "+TableInfo.PROT_pdate+" >='"+pDate1+"' ";
				}
				catch(Exception err){
					//不是日期
					CommonTools.Error(request,response,session,"请确保输入的商品生产日期开始范围的正确,格式(yyyy-MM-dd)!");
				}
			}
			else{
				//没有输入日期,则从1970-01-01开始查询
				sql += " and "+TableInfo.PROT_pdate+" >='1970-01-01' ";
			}
			
			if(pDate2!=null){
				//是否是日期
				try{
					//是日期，就正常查询。
					urlAddress += "&pdate２="+pDate2;
					sql += " and "+TableInfo.PROT_pdate+" <='"+pDate2+"' ";
				}catch(Exception err){
					//不是日期，则跳转。
					CommonTools.Error(request,response,session,"请确保输入的商品生产日期结束范围的正确,格式(yyyy-MM-dd)!");
				}
			}
			else{
				//没有输入日期,结束日期以当前日期为结束日期。
				sql += " and "+TableInfo.PROT_pdate+" <='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' ";
			}
			
			
			
			//价格范围,考虑只输入一个的情况和2个都输入的情况	
			//最小价格的考虑
			if(pPrice1!=null){
				//是否是否点书
				try{
					//是浮点数。
					Double.parseDouble(pPrice1);
					urlAddress += "&price1="+pPrice1;
					sql += " and "+TableInfo.PROT_pprice+" >= "+pPrice1+" ";
				}
				catch(Exception err){
					//不是浮点数则跳转
					CommonTools.Error(request,response,session,"请确保输入的商品金额开始范围正确!");
				}
			}
			else{
				//没有输入最小交割，以０作为最小价格
				sql += " and "+TableInfo.PROT_pprice+" >=0 ";//其实可以取消的.
			}
			//最大价格的考虑
			if(pPrice2!=null){
				//是否是否点书
				try{
					//是浮点数。
					Double.parseDouble(pPrice2);
					urlAddress += "&price2="+pPrice2;
					sql += " and "+TableInfo.PROT_pprice+" <= "+pPrice2+" ";
				}
				catch(Exception err){
					//不是浮点数则跳转
					CommonTools.Error(request,response,session,"请确保输入的商品金额结束范围正确!");
				}
			}
			else{
				//没有输入最小交割，以０作为最小价格
				//sql += " and "+TableInfo.PROT_pprice+" <=??? ";//其实可以取消的.
			}
			
			//最小折扣率
			if(pDiscount1!=null){
				//是否是整数，这里要求折扣率为0-100的整数,可以考虑用户输入的是0-1的小数。其他结构不改变
				try{
					Integer.parseInt(pDiscount1);
					urlAddress +="&pdiscount1="+pDiscount1;
					sql += " and "+TableInfo.PROT_pdiscount+" <= "+pDiscount1+" ";
				}
				catch(Exception err){
					//不是浮点数
					CommonTools.Error(request,response,session,"请确保输入的折扣率开始范围正确!(0-100)");
				}
			}
			else{
				//没有输入折扣率，则不作为查询条件
			}
			//最大折扣率
			if(pDiscount2!=null){
				//是否是整数，这里要求折扣率为0-100的整数,可以考虑用户输入的是0-1的小数。其他结构不改变
				try{
					Integer.parseInt(pDiscount2);
					urlAddress +="&pdiscount2="+pDiscount2;
					sql += " and "+TableInfo.PROT_pdiscount+" <= "+pDiscount2+" ";
				}
				catch(Exception err){
					//不是浮点数
					CommonTools.Error(request,response,session,"请确保输入的折扣率开始范围正确!(0-100)");
				}
			}
			else{
				//没有输入折扣率，则不作为查询条件
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
		return new String[]{sql,urlAddress};
	}

}
