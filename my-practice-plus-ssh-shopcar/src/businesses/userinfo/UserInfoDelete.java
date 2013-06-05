/**
 * ˵�����û�ע����Ϣע��
 * �ļ���userinfo/UserInfoDelete.java->userinfo/userinfodelete.jsp
 * ʱ�䣺08-05-18
 * ��д��tarena
 */
package businesses.userinfo;

import global.DataAccessImpl;
import global.TableInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import businesses.tools.CommonTools;
import businesses.tools.QueryTools;

public class UserInfoDelete extends HttpServlet {

	private HttpSession session=null;
	private DataAccessImpl dataAccess =null;
	
	public UserInfoDelete() {
		super();
	}

	/**
	 * ˵�����û���Ϣע��
	 * ʹ�ã�userinfodelete.jsp?uid=uid
	 * ������
	 * 	1.����û�idΪuid�Ƿ��ڱ��д���
	 * 	2.����û�idΪuid�Ľ��׼�¼�����Ƿ��ж���
	 * 	3.�����û�������cookie��Ϊ��Ч
	 * 	4.��session��ɾ���û���¼��session
	 * 	5.ɾ���û�idΪuid�Ľ��׼�¼���е����н��׼�¼
	 * 	6.���û�����ɾ���û�idΪuid���û�
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();
		dataAccess = DataAccessImpl.newInstance();
		
		String uid = session.getAttribute("sc_user").toString();
		if(uid == null)
			CommonTools.Error(request,response,session);
		//�ж��û�����
		else if(QueryTools.isExistColumn(dataAccess, TableInfo.USER_uid, TableInfo.TABLE_UserInfo, TableInfo.USER_uid+"="+uid)){
			//�жϽ��׼�¼�����Ƿ��ж���
			//���׼�¼������Ʒ��־ΪtshipedΪ0[δ����]��1[�ѷ���]ʱ������ע��
			//�������tshipedΪ1ʱ,��������־Ϊ2ʱ����ע��,�����û���Զ����ע��
			if(QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tuid+"="+uid+" and "+TableInfo.TRAN_tshiped+"<2")){
				//���cookie
				try {
					Cookie[] cookies = request.getCookies();
					for(Cookie cookie:cookies){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					//ɾ������session��Ϣ
					session.invalidate();
					//�˴������Ƿ����쳣ʱ������ع�
					//�Ӷ�������ɾ���û�Ϊuid�����н��׼�¼			
					String sql = "delete from "+TableInfo.TABLE_Transactions +" where ";
					sql += TableInfo.TRAN_tuid+"="+uid;
					//System.out.println("ɾ���û�idΪ"+uid+"���׼�¼��䣺"+sql);
					dataAccess.executeSQL(sql);
					
					sql = "delete from "+TableInfo.TABLE_UserInfo +" where ";
					sql += TableInfo.USER_uid+"="+uid;
					//System.out.println("ɾ���û�idΪ"+uid+"�û���䣺"+sql);
					dataAccess.executeSQL(sql);
				} catch (RuntimeException e) {
					System.out.println("�û�idΪ"+uid+"ע��ʱ��������!");
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			else{
				CommonTools.Error(request,response,session,"������Ķ�������ɾ����������ע�������û�!");
			}
		}else
		{
			CommonTools.Error(request,response,session,"��Ч���û�id");
		}
	}

}
