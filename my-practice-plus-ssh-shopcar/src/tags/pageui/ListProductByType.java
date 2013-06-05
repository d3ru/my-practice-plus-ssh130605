package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.Str;
import global.TableInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import businesses.tools.CommonTools;
import businesses.tools.ShowPage;

public class ListProductByType extends BodyTagSupport {
	private String typeid;
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	@Override
	public int doEndTag() throws JspException {
		ServletRequest request = pageContext.getRequest();
		// TODO Auto-generated method stub
		JspWriter out=pageContext.getOut();
		/**
		 * һЩԼ����
		 * pageResult:ÿҳ��ʾ�ļ�¼����,Ĭ��10
		 * currPage:��ǰҳ,Ĭ��1
		 * totalPage:ȫ����ҳ��
		 * totalResult:�ܼ�¼��
		 * urlAddress:��ʾ��ҳʱ���ӵ�ҳ��
		 */
		int pageResult=2;
		int currPage = 1;
		int totalPage = 1;
		int totalResult = 0;
		String  condition=request.getParameter("cond");
		condition=condition==null?"":condition;
		try {
			condition=new String(condition.getBytes("iso-8859-1"),"gb2312");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlAddress ="listproductbytype.jsp?typeid="+request.getParameter("typeid")+"&cond=" +condition;
		String page = request.getParameter("page");
		
		currPage = CommonTools.StringToInt(page);
		if(currPage == -1)
			currPage = 1;
		try {
			IDataAccess ida=DataAccessImpl.newInstance();
			String sql="select * from "+TableInfo.TABLE_ProductType+" where "+TableInfo.TYPE_ptid+"="+Integer.parseInt(typeid);
			ResultSet rs=ida.queryBySQL(sql);
			String csql="select count(*) as total from "+TableInfo.TABLE_Products+" where "+TableInfo.PROT_ptypeid+"="+request.getParameter("typeid")+ " and "+TableInfo.PROT_pname+"  like '%"+condition+"%'";
			ResultSet rs3=ida.queryBySQL(csql);
			if(rs3.next()){
				totalResult=rs3.getInt("total");
			}
			totalPage = totalResult%pageResult==0?totalResult/pageResult:totalResult/pageResult+1;
			//�����ҳ��������ҳ��ʱ,��ʾ��1ҳ
			if(currPage>totalPage)
				currPage = 1;
			int i=0;
			while(rs.next()){
				int id=rs.getInt(1);
				String ptname=rs.getString(2);
				String sql2="select * from "+TableInfo.TABLE_Products+" where "+TableInfo.PROT_ptypeid+"="+id+" and "+TableInfo.PROT_pname+" like '%"+condition+"%' limit "+((currPage-1)*pageResult)+","+pageResult;;
				ResultSet rs2=ida.queryBySQL(sql2);
				out.println("<center>��"+ptname+"���б�<center>");
				//showPage.printPage(out, currPage, totalPage, urlAddress);
				out.println("<div style='overflow:auto;height:300px;'>");
				
				out.println("<table>");
				while(rs2.next()){
					out.println("<tr height='155px'>");
					out.println("<td>");
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String date=df.format(rs2.getDate("pdate"));
					
					out.println("<table width='100%' height='100%' border='0'>");
					out.println("<tr align='left'>");
					out.println("<td width='156' rowspan='4'><img src='displaypphoto.do?pid="+rs2.getInt(1)+"' width='70' height='90' border='0'/></td>");
					out.println("<td width='76' height='25' align='right'>��Ʒid��</td>");
					out.println("<td width='169'>"+rs2.getInt("pid")+"</td>");
					out.println("<td width='57' align='right'>��Ʒ����</td>");
					out.println("<td width='192'>"+rs2.getString("pname")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25' align='right'>�������ڣ�</td>");
					out.println("<td>"+date+"</td>");
					out.println("<td align='right'>��棺</td>");
					out.println("<td>"+rs2.getInt("pamount")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='25' align='right'>���ۣ�</td>");
					out.println("<td>"+rs2.getDouble("pprice")+"</td>");
					out.println("<td align='right'>��Ʒ��ע:</td>");
					out.println("<td>"+rs2.getString("pnotes")+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align='right'>��ϸ������</td>");
					out.println("<td colspan='2'>"+rs2.getString("pdescription")+"</td>");
					//out.println("</tr>");
					//out.println("<tr>");
					//ShopCarts����������Ʒ��Ϣ���빺�ﳵ��
					//��һ����������Ʒ��id���ڶ�����������Ʒ������ͨ����ʼ��ӵ�ʱ�������ǣ���
					out.println("<td ><button class='bstyle' onclick='ShopCarts("+rs2.getInt("pid")+",1);' />���빺�ﳵ</button></td>");
					out.println("</tr>");
					out.println("<hr color='white' style='width:576px;'>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("</div>");
				ShowPage.printPage(out, currPage, totalPage, urlAddress);
				
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
