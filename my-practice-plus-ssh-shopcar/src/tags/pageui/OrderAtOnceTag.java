package tags.pageui;

import global.DataAccessImpl;
import global.IDataAccess;
import global.Str;
import global.TableInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OrderAtOnceTag extends BodyTagSupport {
	public int doEndTag() throws JspException {		
		JspWriter out=pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		HttpServletResponse response=(HttpServletResponse)pageContext.getResponse();
		Cookie[] cookies=request.getCookies();
		try {
			IDataAccess ida=DataAccessImpl.newInstance();
			Cookie cartproducts[]=request.getCookies();//ͨ����ookie�ӿͻ���ȡѡ����Ʒ��Ϣ.
			String cpid="";
			int pid;
			int pronum;
			double totalmoney=0;
			out.print("<p>&nbsp;</p>");
			out.print("<table border='1' width='80%' cellspacing='0' cellpadding='0'>");
			out.print("<tr style='background-color:#333333;color:blue;'>");
			out.print("<td>��Ʒ����</td>");
			out.print("<td>��Ʒ�۸�</td>");
			out.print("<td>��Ʒ˵��</td>");
			out.print("<td>ѡ������</td>");
			out.print("<td>�۸�С��</td>");
			out.print("</tr>");
			//
			for(Cookie cook:cartproducts){//ѭ������Cookie�е�ѡ����Ʒ��Ϣ��
				cpid=cook.getName();//��ookie����ѡ����Ʒ��pro��ͷ��
				if(cpid.startsWith("pro_")){
					try {
						pid = Integer.parseInt(cpid.substring(4));//��Ʒ�ɣ�
						pronum = Integer.parseInt(cook.getValue());//��Ʒ����
						String sql = "select * from "+TableInfo.TABLE_Products+" where "+TableInfo.PROT_pid+"=" + pid;
						ResultSet rs = ida.queryBySQL(sql);
						while(rs.next()){
							out.print("<tr>");
							String pname = rs.getString("pname");
							String pdescription = rs.getString("pdescription");
							double pprice = rs.getDouble("pprice");
							
							
							out.println("<td>"+ pname +"</td>");//��Ʒ����
							out.println("<td>"+ pprice +"</td>");//���ۼ�
							out.println("<td>"+ pdescription +"</td>");//�����
							out.print("<td>"+ pronum + "</td>");
							out.println("<td>"+ pronum * pprice +"</td>");			
							totalmoney+=pronum * pprice;
							out.print("<tr>");
						}
					} catch (Exception e) {
						e.printStackTrace();
						//System.out.print(e.getMessage());
					}
					//���Cookie
					cook.setMaxAge(0);//��:��ָ��������ɾ��,0:����ɾ��,��:������رպ�ɾ��.
					//cook.setValue("");					
					response.addCookie(cook);
					//response.setHeader("refresh", "0;url='"+request.getContextPath()+"/index.jsp'");
					
					//System.out.println("Cookie����");
				}				
			}
			//out.print("<tr  align='center'><td colspan='5' align='center'><B>�ܽ��:</b><font color=red>"+totalmoney+"</font></td></tr>");
			out.print("<tr  align='center'><td colspan='5' align='center'><B>�ܽ��:</b><font color=red>"+totalmoney+"</font></td></tr>");
			out.print("</table>");
			out.print("<p>&nbsp;</p>");
			out.print("<a href='"+request.getContextPath()+"/index.jsp'><u>����</u></a>");
			response.flushBuffer();
			
		}catch(Exception err)
		{
			
		}
		return super.doEndTag();
	}
}
