/**
 *  �ļ���queryProduct.java
 *  ˵����ʵ�ֲ�ѯ���߼���ѯ
 *  ʱ�䣺08-05-20
 *  ��д��Tarena
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
	 * һЩԼ����
	 * pageResult:ÿҳ��ʾ�ļ�¼����,Ĭ��10
	 * currPage:��ǰҳ,Ĭ��1
	 * totalPage:ȫ����ҳ��
	 * totalResult:�ܼ�¼��
	 * urlAddress:��ʾ��ҳʱ���ӵ�ҳ��
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
			queryCondition = request.getParameter("querycondition");//�п��ܳ��ֺ��֣���Ҫ���롣
			
			pDate1 = request.getParameter("pdate1");
			pDate2 = request.getParameter("pdate2");
			pPrice1 = request.getParameter("price1");
			pPrice2 = request.getParameter("price2");
			pDiscount1 = request.getParameter("pdiscount1");
			pDiscount2 = request.getParameter("pdiscount2");
			//�ж�����ѯ�������Ƿ�����
			if(queryType!=null && queryType.equals(""))
				queryType = null;
			//���û�����뿪ʼ���ڣ����1970-01-01��ʼ����.
			if(pDate1==null || pDate1.equals(""))
				pDate1 =  null;
			//���û������������ڣ�����Ʒ���ڲ�ѯ����ǰ����Ϊ׼��
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
			if(queryCondition != null && !queryCondition.equals("")){//����
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
				//System.out.println("��ѯ�ӣѣ̣�"+sql[0]);				
				ResultSet rs = dataAccess.queryBySQL(sql[0]);
				out.println("<div style='overflow:auto;height:350px;'>");
				out.println("<table>");
				if(totalResult == 0){
					out.println("<tr><td height='20' align='center' valign='middle'>���޷�������ѯ����Ʒ!</td></tr>");
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
					out.println("<td width='76' height='25'>��Ʒid��</td>");
					out.println("<td width='169'>"+id+"</td>");
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
				out.println("<hr color='blue' style='width:576px;'>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
			}else{
				CommonTools.Error(request,response,session,"��������,����ϵ����Ա!");
				return super.doEndTag();
			}

			out.flush();
			//out.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return super.doEndTag();
	}
	//���ݲ�ѯ�������ɲ�ѯSQL������ҳ��ѯ�����ӵ�ַ��
	private String[] returnQuerySQL(int n){
		//��ѯ���ͣ�1Ϊͳ�Ʋ�ѯ��2Ϊ��ϸ��ѯ��
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
		//queryType��ѯ���ͷ֣��߼���ѯ��򵥲�ѯ
		//1Ϊ�߼���ѯ��0�����Ϊ�򵥲�ѯ
		if(queryType!=null && queryType.equals("1")){
			urlAddress += "&querytype="+queryType;
			
			//��������
			if(pDate1!=null){
				//�Ƿ������ڣ�
				try{
					new SimpleDateFormat("yyyy-MM-dd").parse(pDate1);
					//������
					urlAddress += "&pdate1="+pDate1;
					sql += " and "+TableInfo.PROT_pdate+" >='"+pDate1+"' ";
				}
				catch(Exception err){
					//��������
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ�������ڿ�ʼ��Χ����ȷ,��ʽ(yyyy-MM-dd)!");
				}
			}
			else{
				//û����������,���1970-01-01��ʼ��ѯ
				sql += " and "+TableInfo.PROT_pdate+" >='1970-01-01' ";
			}
			
			if(pDate2!=null){
				//�Ƿ�������
				try{
					//�����ڣ���������ѯ��
					urlAddress += "&pdate��="+pDate2;
					sql += " and "+TableInfo.PROT_pdate+" <='"+pDate2+"' ";
				}catch(Exception err){
					//�������ڣ�����ת��
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ�������ڽ�����Χ����ȷ,��ʽ(yyyy-MM-dd)!");
				}
			}
			else{
				//û����������,���������Ե�ǰ����Ϊ�������ڡ�
				sql += " and "+TableInfo.PROT_pdate+" <='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' ";
			}
			
			
			
			//�۸�Χ,����ֻ����һ���������2������������	
			//��С�۸�Ŀ���
			if(pPrice1!=null){
				//�Ƿ��Ƿ����
				try{
					//�Ǹ�������
					Double.parseDouble(pPrice1);
					urlAddress += "&price1="+pPrice1;
					sql += " and "+TableInfo.PROT_pprice+" >= "+pPrice1+" ";
				}
				catch(Exception err){
					//���Ǹ���������ת
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ��ʼ��Χ��ȷ!");
				}
			}
			else{
				//û��������С����ԣ���Ϊ��С�۸�
				sql += " and "+TableInfo.PROT_pprice+" >=0 ";//��ʵ����ȡ����.
			}
			//���۸�Ŀ���
			if(pPrice2!=null){
				//�Ƿ��Ƿ����
				try{
					//�Ǹ�������
					Double.parseDouble(pPrice2);
					urlAddress += "&price2="+pPrice2;
					sql += " and "+TableInfo.PROT_pprice+" <= "+pPrice2+" ";
				}
				catch(Exception err){
					//���Ǹ���������ת
					CommonTools.Error(request,response,session,"��ȷ���������Ʒ��������Χ��ȷ!");
				}
			}
			else{
				//û��������С����ԣ���Ϊ��С�۸�
				//sql += " and "+TableInfo.PROT_pprice+" <=??? ";//��ʵ����ȡ����.
			}
			
			//��С�ۿ���
			if(pDiscount1!=null){
				//�Ƿ�������������Ҫ���ۿ���Ϊ0-100������,���Կ����û��������0-1��С���������ṹ���ı�
				try{
					Integer.parseInt(pDiscount1);
					urlAddress +="&pdiscount1="+pDiscount1;
					sql += " and "+TableInfo.PROT_pdiscount+" <= "+pDiscount1+" ";
				}
				catch(Exception err){
					//���Ǹ�����
					CommonTools.Error(request,response,session,"��ȷ��������ۿ��ʿ�ʼ��Χ��ȷ!(0-100)");
				}
			}
			else{
				//û�������ۿ��ʣ�����Ϊ��ѯ����
			}
			//����ۿ���
			if(pDiscount2!=null){
				//�Ƿ�������������Ҫ���ۿ���Ϊ0-100������,���Կ����û��������0-1��С���������ṹ���ı�
				try{
					Integer.parseInt(pDiscount2);
					urlAddress +="&pdiscount2="+pDiscount2;
					sql += " and "+TableInfo.PROT_pdiscount+" <= "+pDiscount2+" ";
				}
				catch(Exception err){
					//���Ǹ�����
					CommonTools.Error(request,response,session,"��ȷ��������ۿ��ʿ�ʼ��Χ��ȷ!(0-100)");
				}
			}
			else{
				//û�������ۿ��ʣ�����Ϊ��ѯ����
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
		return new String[]{sql,urlAddress};
	}

}
