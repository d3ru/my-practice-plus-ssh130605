/**
 * 文件：userinfoUpdate.jsp -->userinfo/userinfoupdate.jsp 说明：用户注册信息修改 时间：08-05-22 编写：tarena
 */
package businesses.userinfo;

import global.DataAccessImpl;
import global.TableInfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businesses.tools.CommonTools;

public class UserInfoUpdate extends HttpServlet
{
	private static final long serialVersionUID = 4997657162838540591L;

	public UserInfoUpdate()
	{
		super();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		DataAccessImpl dataAccess = DataAccessImpl.newInstance();
		HttpSession session = request.getSession();
		String uid = session.getAttribute("sc_user").toString();

		String umail = request.getParameter(TableInfo.USER_umail);
		String uname = request.getParameter(TableInfo.USER_uname);
		String uaddress = request.getParameter(TableInfo.USER_uaddress);
		String uzip = request.getParameter(TableInfo.USER_uzip);
		String utele = request.getParameter(TableInfo.USER_utele);
		String umobile = request.getParameter(TableInfo.USER_umobile);

		// 这里只做简单的验证,对于精确验证请使用正则表达式验证
		if (uname == null || uname.equals(""))
		{
			CommonTools.Error(request, response, session, "用户名不允许为空");
			return;
		}
		if (uaddress == null || uaddress.equals(""))
		{
			CommonTools.Error(request, response, session, "地址不允许为空");
			return;
		}
		if (uzip == null || uzip.equals(""))
		{
			CommonTools.Error(request, response, session, "邮政编码不允许为空");
			return;
		}
		if (utele == null || utele.equals(""))
		{
			CommonTools.Error(request, response, session, "电话号码不允许为空");
			return;
		}
		if (umobile == null || umobile.equals(""))
		{
			CommonTools.Error(request, response, session, "手机号不允许为空");
			return;
		}
		String sql = "update " + TableInfo.TABLE_UserInfo + " set ";
		sql += TableInfo.USER_umail + "='" + umail + "',";
		sql += TableInfo.USER_uname + "='" + uname + "',";
		sql += TableInfo.USER_uaddress + "='" + uaddress + "',";
		sql += TableInfo.USER_uzip + "='" + uzip + "',";
		sql += TableInfo.USER_utele + "='" + utele + "',";
		sql += TableInfo.USER_umobile + "='" + umobile + "' ";
		sql += "where " + TableInfo.USER_uid + "='" + uid + "'";
		boolean b = dataAccess.executeSQL(sql);
		if (b)
		{
			// 改变Session中的uname
			request.getSession().setAttribute("uname", uname);
			response.sendRedirect(request.getContextPath() + "/userinfo/userinfo.jsp");
		}
		else
		{
			CommonTools.Error(request, response, session, "发生错误,请联系管理员!");
			return;
		}

	}

}
