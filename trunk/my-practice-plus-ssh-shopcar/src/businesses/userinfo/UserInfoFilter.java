/**
 * 文件：UserInfoFilter.java
 * 说明：用户访问用户信息自维护的过滤器
 * 时间：08-05-21
 * 编写：tarena
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
		// TODO 自动生成方法存根

	}

	public void doFilter(ServletRequest res, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		// TODO 自动生成方法存根
		HttpServletRequest request=(HttpServletRequest)res;
		HttpServletResponse reponse=(HttpServletResponse)rep;
		HttpSession session=request.getSession();
		//没有登录的用户应该被拦截。
		//System.out.println(request.getRequestURI());
		//System.out.println(request.getRequestURL());
		//System.out.println(request.getServletPath());
		String url=request.getServletPath();
		String querystring=request.getQueryString();
		querystring=querystring==null?"":querystring;
		if(session==null || session.getAttribute("uname")==null){
			//保存该信息就是便于登录后再定向到该页面处理。处理完毕后，清空该session值
			session.setAttribute("urlstr", url+"?"+querystring);
			//System.out.println("被拦截的URL："+url+"?"+querystring);
			request.setAttribute("notlogin", "您登录后使用才可以使用该功能！！");
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
