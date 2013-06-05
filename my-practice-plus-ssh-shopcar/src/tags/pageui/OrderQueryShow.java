/**
 * �ļ���orderQueryShow.java
 * ˵����������ѯ����ʾui
 * ʱ�䣺08-05-19
 * ��д��tarena
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
	 * ˵����������ѯ
	 * �򵥷�ҳ������ʵ��,�˴�����ʱ���ϵ,û�н���ҳ��װ����
	 * ��oracle���ݿ��в�ѯ������к�ʹ��α��ROWNUM��ʾ����1��ʼ����
	 * ����select * from employee where rownum<10 ����ǰ10����¼����ע��rownum���ڲ�ѯ֮������֮ǰ��ֵ��
	 * mySQL����ʹ��LIMIT�Ӿ䣺 select name, birthday from employee order by birthday LIMIT 99,20 
	 * 		limit 99,20 ��ʾ��ѯ�����99�к�ʼ,����20�еĽ�� ������ 100-119
	 * DB2��rownumber()�������ڻ�ȡ��ǰ������ 
	 */
	
	public int doEndTag() throws JspException {
		try {
			ServletRequest request = pageContext.getRequest();
			JspWriter out = pageContext.getOut();
			ServletResponse response = pageContext.getResponse();
			response.setContentType("text/html;charset=gb2312");
			/**
			 * һЩԼ����
			 * pageResult:ÿҳ��ʾ�ļ�¼����,Ĭ��10
			 * currPage:��ǰҳ,Ĭ��1
			 * totalPage:ȫ����ҳ��
			 * totalResult:�ܼ�¼��
			 * urlAddress:��ʾ��ҳʱ���ӵ�ҳ��
			 */
			int pageResult=10;
			int currPage = 1;
			int totalPage = 1;
			int totalResult = 0;
			String urlAddress =queryPage;
			String uid = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			//��session��ȡ���û���,�����û�����ѯ�û�id
			//String uname = pageContext.getSession().getAttribute(TableInfo.SC_User).toString();
			//String uid = queryTools.getColumnValue(dataAccess, TableInfo.USER_uid, TableInfo.TABLE_UserInfo, TableInfo.USER_uname+"="+uname);
			
			String pname = request.getParameter(TableInfo.PROT_pname);
			String pdescription = request.getParameter(TableInfo.PROT_pdescription);
			String tshipdate = request.getParameter(TableInfo.TRAN_tshipdate);
			String page = request.getParameter("page");
			//��ֹ��������ֵ
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
			
			//��ѯ�ܼ�¼��,������ҳ����
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
			System.out.println("��ѯ�ܼ�¼����sql���:\n"+c_sql);
			ResultSet rs = dataAccess.queryBySQL(c_sql);
			if(rs.next()){
				totalResult = rs.getInt("total");
				System.out.println("come-->"+totalResult);
				totalPage = totalResult%pageResult==0?totalResult/pageResult:totalResult/pageResult+1;
				//�����ҳ��������ҳ��ʱ,��ʾ��1ҳ
				if(currPage>totalPage)
					currPage = 1;
				//��ѯ���������ļ�¼
				//��ѯ�������Ʒ����,��Ʒ�۸�,��Ʒ�ۿ���,����id,������Ʒ������
				String sql ="select "+TableInfo.TABLE_Products+"."+TableInfo.PROT_pname+",";//��Ʒ����
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
				System.out.println("��ѯ��¼�����\n"+sql);
				rs = dataAccess.queryBySQL(sql);
				
				out.println("<table width='90%' border='1' cellspacing='0' cellpadding='0' style='font-size:12px'>");
				out.println("<tr>");
				out.println("<td width='5%' height='24' align='center' valign='middle'>������</td>");
				out.println("<td width='30%' align='center' valign='middle'>��Ʒ����</td>");
				out.println("<td width='8%' align='center' valign='middle'>����</td>");
				out.println("<td width='8%' align='center' valign='middle'>����</td>");
				out.println("<td width='8%' align='center' valign='middle'>�ۿ���</td>");
				out.println("<td width='10%' align='center' valign='middle'>���</td>");
				out.println("<td width='15%' align='center' valign='middle'>��������</td>");
				out.println("<td width='8%' align='center' valign='middle'>�����޸�</td>");
				out.println("<td width='8%' align='center' valign='middle'>������</td>");
				out.println("</tr>");
				if(totalResult == 0)
					out.println("<tr><td height='20' align='center' valign='middle' colspan='9'>���޶���!</td></tr>");
				while(rs.next()){
					out.println("<tr>");
					out.println("<td height='20' align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tid)+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pname)+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tamount)+"</td>");
					out.println(" <td align='center' valign='middle'>"+rs.getString(TableInfo.PROT_pprice)+"</td>");
					out.println(" <td align='center' valign='middle'>"+CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pdiscount))/10+"</td>");
					out.println("<td align='center' valign='middle'>"+CommonTools.StringToDouble(rs.getString(TableInfo.PROT_pprice))*CommonTools.StringToInt(rs.getString(TableInfo.PROT_pdiscount))*CommonTools.StringToInt(rs.getString(TableInfo.TRAN_tamount))/100+"</td>");
					out.println("<td align='center' valign='middle'>"+rs.getString(TableInfo.TRAN_tdate)+" "+rs.getString(TableInfo.TRAN_ttime)+"</td>");
					out.println("<td align='center' valign='middle'><a href='ordermodify.jsp?orderid="+rs.getString(TableInfo.TRAN_tid)+"'>�޸�</a></td>");
					out.println("<td align='center' valign='middle'><a href='javascript:isDeleteOrder(\"order_manager.jsp?method=delete&orderid="+rs.getString(TableInfo.TRAN_tid)+"\")'>ɾ��</a></td>");
					out.println("</tr>");
					//javascript:isDeleteOrder(url)
				} 
				out.println("</table><br>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
					
			}else
				out.println("��������,����ϵ����Ա!");
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
