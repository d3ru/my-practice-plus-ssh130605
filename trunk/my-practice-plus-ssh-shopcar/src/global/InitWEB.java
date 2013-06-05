/*
 * 类名：InitWEB
 * 作用：在WEB初始化的时候，完成数据库的连接。
 * 日期：
 * 作者：
 * */
package global;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitWEB extends HttpServlet {

	public InitWEB() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void init() throws ServletException {
		// 初始化数据库连接
		initDB();
	}	
	private void initDB(){
		String driver = this.getServletContext().getInitParameter("driver");
		String url = this.getServletContext().getInitParameter("url");
		String username = this.getServletContext().getInitParameter("user");
		String password = this.getServletContext().getInitParameter("password");
		IDataAccess ida=DataAccessImpl.newInstance(driver,url,username,password);
		ida.openConnect();
	}
}
