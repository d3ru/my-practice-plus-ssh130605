/**
 * �ļ���UserInfoFilter.java
 * ˵�����û������û���Ϣ��ά���Ĺ�����
 * ʱ�䣺08-05-21
 * ��д��tarena
 */
package businesses.userinfo;

import global.TableInfo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInfoFilter implements Filter {

	public void destroy() {
		// TODO �Զ����ɷ������

	}

	public void doFilter(ServletRequest res, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		// TODO �Զ����ɷ������
		HttpServletRequest request=(HttpServletRequest)res;
		HttpServletResponse reponse=(HttpServletResponse)rep;
		HttpSession session=request.getSession();
		//û�е�¼���û�Ӧ�ñ����ء�
		//System.out.println(request.getRequestURI());
		//System.out.println(request.getRequestURL());
		//System.out.println(request.getServletPath());
		String url=request.getServletPath();
		String querystring=request.getQueryString();
		querystring=querystring==null?"":querystring;
		if(session==null || session.getAttribute("uname")==null){
			//�������Ϣ���Ǳ��ڵ�¼���ٶ��򵽸�ҳ�洦��������Ϻ���ո�sessionֵ
			session.setAttribute("urlstr", url+"?"+querystring);
			//System.out.println("�����ص�URL��"+url+"?"+querystring);
			request.setAttribute("notlogin", "����¼��ʹ�òſ���ʹ�øù��ܣ���");
			RequestDispatcher rd=res.getRequestDispatcher("/login/userlogin.jsp");
			rd.forward(res, rep);
			
		}
		else{
			chain.doFilter(request, reponse);
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
