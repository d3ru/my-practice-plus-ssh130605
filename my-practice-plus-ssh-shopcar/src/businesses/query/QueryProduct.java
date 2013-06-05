/**
 *  �ļ���queryProduct.java
 *  ˵����ʵ�ֲ�ѯ���߼���ѯ
 *  ʱ�䣺08-05-20
 *  ��д��tarena
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
	 * һЩԼ����
	 * pageResult:ÿҳ��ʾ�ļ�¼����,Ĭ��10
	 * currPage:��ǰҳ,Ĭ��1
	 * totalPage:ȫ����ҳ��
	 * totalResult:�ܼ�¼��
	 * urlAddress:��ʾ��ҳʱ���ӵ�ҳ��
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
			
			//querytype   	��ѯ����  0  ������Ʒ����ѯ   1  ��ϸ��ѯ
			//productype	��ѯ����Ʒ���
			//querycondition��ѯ����//		pname	��Ʒ����
			//����			���ݲ�ͬ���͵Ĳ�ѯ�ֶ������ݱ��ֶ�ͬ
			//pdate	��Ʒ�������� --
			//pprice	��Ʒ�۸� --
			//pdiscount	�ۿ��� 0-100
			//ptypeid	��Ʒ���
			//ҳ��
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
				CommonTools.Error(request,response,session,"�������ѯ�ؼ���!");
				return;
			}
			else{
				queryCondition=new String(queryCondition.getBytes("iso-8859-1"),"gb2312");
			}
			//������ѯ�������ʾ��ҳʱ��ѯ�����ӵ�ַ
			String urlAddress ="query.jsp";
			String[] sql = returnQuerySQL(1);
			urlAddress += sql[1];
			ResultSet rss = dataAccess.queryBySQL(sql[0]);
			if(rss.next()){
				totalResult = rss.getInt("total");
				totalPage = totalResult%pageResult==0?totalResult/pageResult:totalResult/pageResult+1;
				//�����ҳ��������ҳ��ʱ,��ʾ��1ҳ
				if(currPage>totalPage)
					currPage = 1;
				sql = returnQuerySQL(2);
				ResultSet rs = dataAccess.queryBySQL(sql[0]);
//				out.println("<table width='90%' border='1' cellspacing='0' cellpadding='0' style='font-size:12px'>");
//				out.println("<tr>");
//				out.println("<td width='5%' height='24' align='center' valign='middle'>��Ʒid</td>");
//				out.println("<td width='20%' align='center' valign='middle'>��Ʒ����</td>");
//				out.println("<td width='10%' align='center' valign='middle'>��Ʒ���</td>");
//				out.println("<td width='5%' align='center' valign='middle'>��Ʒ����</td>");
//				out.println("<td width='5%' align='center' valign='middle'>����</td>");
//				out.println("<td width='5%' align='center' valign='middle'>�ۿ���</td>");
//				out.println("<td width='30%' align='center' valign='middle'>��Ʒ����</td>");
//				out.println("<td width='10%' align='center' valign='middle'>��Ʒ��ע</td>");
//				out.println("<td width='10%' align='center' valign='middle'>��������</td>");
//				out.println("</tr>");
				out.println("<div style='overflow:scroll;height:300px;'>");
				out.println("<table>");
				if(totalResult == 0)
					out.println("<tr><td height='20' align='center' valign='middle'>���޷�������ѯ����Ʒ!</td></tr>");
				while(rs.next()){
					int id=rs.getInt("pid");
					//pid-->��ƷID//pname-->��Ʒ����//pdescription-->��Ʒ����//pdate-->��Ʒ��������//pprice-->��Ʒ�۸�
					//pamount-->��Ʒ����//pnotes-->��Ʒ��ע//pdiscount-->�ۿ��� 0-100//ptypeid-->��Ʒ���
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
					out.println("<td width='76' height='25'>��Ʒid��</td>");
					out.println("<td width='169'>"+id+"</td>");
					System.out.println(rs.getInt("pid"));
					out.println("<td width='57'>��Ʒ����</td>");
					out.println("<td width='192'>"+rs.getString("pname"));
					out.println("</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25'>�������ڣ�</td>");
					out.println("<td>"+date+"</td>");
					out.println("<td>��棺</td>");
					out.println("<td>"+rs.getInt("pamount")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25'>���ۣ�</td>");
					out.println("<td>"+rs.getDouble("pprice")+"</td>");
					out.println("<td>��Ʒ��ע:</td>");
					out.println("<td>"+rs.getString("pnotes")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>��ϸ������</td>");
					out.println("<td colspan='2'>"+rs.getString("pdescription")+"</td>");
					//out.println("</tr>");
					//out.println("<tr>");
					out.println("<td><button class='bstyle' onclick=\"ShopCarts("+id+",1);\" />���빺�ﳵ</button></td>");
					out.println("</tr>");
					out.println("<hr color='white' style='width:576px;'>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				} 
				out.println("</table>");
				out.println("</div>");
				//������ʾ��ҳ
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
			}else{
				CommonTools.Error(request,response,session,"��������,����ϵ����Ա!");
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
			
			//��������
			if(pDate !=null){
				urlAddress += "&"+TableInfo.PROT_pdate+"="+pDate;
				sql += " and "+TableInfo.PROT_pdate+"='"+pDate+"'";
			}
			//�۸�Χ,����ֻ����һ���������2������������
			System.out.println(pPricex1+"==="+pPricex2);
			if(pPricex1!=null && pPricex2!=null){
				int pPrice1 = CommonTools.StringToInt(pPricex1);
				int pPrice2 = CommonTools.StringToInt(pPricex2);
				if(pPrice1==-1 || pPrice2==-1)
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ�����ȷ!");
				urlAddress += "&price1="+pPrice1+"&price2="+pPrice2;
				sql += " and "+TableInfo.PROT_pprice+" between "+pPrice1+" and "+pPrice2;
			}
			if(pPricex1!=null && pPricex2==null){
				int pPrice1 = CommonTools.StringToInt(pPricex1);
				if(pPrice1==-1)
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ�����ȷ!");
				urlAddress += "&price1="+pPrice1;
				sql += " and "+TableInfo.PROT_pprice+">="+pPrice1;
			}
			if(pPricex2!=null && pPricex1==null){
				int pPrice2 = CommonTools.StringToInt(pPricex2);
				if(pPrice2==-1)
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ�����ȷ!");
				urlAddress += "&price2="+pPrice2;
				sql += " and "+TableInfo.PROT_pprice+"<="+pPrice2;
			}
			//�ۿ���
			if(pDiscount!=null){
				int pDiscounts = CommonTools.StringToInt(pDiscount);
				if(pDiscounts==-1)
					CommonTools.Error(request,response,session,"��ȷ��������ۿ�����ȷ!");
				urlAddress +="&"+TableInfo.PROT_pdiscount+"="+pDiscount;
				sql +=" and "+TableInfo.PROT_pdiscount+"="+pDiscount;
			}
			
		}
		//��Ʒ���
		if(productType!=null){
			int typeid = CommonTools.StringToInt(productType);
			if(typeid==-1)
				CommonTools.Error(request, response, session,"��ȷ���������Ʒ�����ȷ!");
			//Լ��999Ϊ������Ʒ����
			if(typeid!=999){
				urlAddress += "&productype="+productType;
				sql += " and "+TableInfo.PROT_ptypeid+"="+typeid;
			}
		}
		if(n==2)
			sql +=" limit "+((currPage-1)*pageResult)+","+pageResult;
		System.out.println("��ѯ��¼�����\n"+sql);
		return new String[]{sql,urlAddress};
	}

}
