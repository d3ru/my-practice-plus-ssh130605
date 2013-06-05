/**
 * 说明：用户注册信息注销
 * 文件：userinfo/UserInfoDelete.java->userinfo/userinfodelete.jsp
 * 时间：08-05-18
 * 编写：tarena
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
	 * 说明：用户信息注销
	 * 使用：userinfodelete.jsp?uid=uid
	 * 方法：
	 * 	1.检测用户id为uid是否在表中存在
	 * 	2.检测用户id为uid的交易记录表中是否有订单
	 * 	3.将该用户的所有cookie置为无效
	 * 	4.从session中删除用户登录的session
	 * 	5.删除用户id为uid的交易记录表中的所有交易记录
	 * 	6.从用户表中删除用户id为uid的用户
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();
		dataAccess = DataAccessImpl.newInstance();
		
		String uid = session.getAttribute("sc_user").toString();
		if(uid == null)
			CommonTools.Error(request,response,session);
		//判断用户存在
		else if(QueryTools.isExistColumn(dataAccess, TableInfo.USER_uid, TableInfo.TABLE_UserInfo, TableInfo.USER_uid+"="+uid)){
			//判断交易记录表中是否有订单
			//交易记录表中商品标志为tshiped为0[未发货]或1[已发货]时不允许注销
			//如果存在tshiped为1时,必须等其标志为2时才能注销,否则用户永远不能注销
			if(QueryTools.isExistColumn(dataAccess, TableInfo.TRAN_tid, TableInfo.TABLE_Transactions, TableInfo.TRAN_tuid+"="+uid+" and "+TableInfo.TRAN_tshiped+"<2")){
				//清空cookie
				try {
					Cookie[] cookies = request.getCookies();
					for(Cookie cookie:cookies){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					//删除所有session信息
					session.invalidate();
					//此处不考虑发生异常时的事务回滚
					//从订单表中删除用户为uid的所有交易记录			
					String sql = "delete from "+TableInfo.TABLE_Transactions +" where ";
					sql += TableInfo.TRAN_tuid+"="+uid;
					//System.out.println("删除用户id为"+uid+"交易记录语句："+sql);
					dataAccess.executeSQL(sql);
					
					sql = "delete from "+TableInfo.TABLE_UserInfo +" where ";
					sql += TableInfo.USER_uid+"="+uid;
					//System.out.println("删除用户id为"+uid+"用户语句："+sql);
					dataAccess.executeSQL(sql);
				} catch (RuntimeException e) {
					System.out.println("用户id为"+uid+"注销时发生错误!");
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			else{
				CommonTools.Error(request,response,session,"请从您的订单表中删除订单后再注销您的用户!");
			}
		}else
		{
			CommonTools.Error(request,response,session,"无效的用户id");
		}
	}

}
