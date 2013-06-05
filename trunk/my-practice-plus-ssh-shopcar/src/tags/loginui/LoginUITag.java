package tags.loginui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoginUITag extends BodyTagSupport
{
	private static final long serialVersionUID = 1L;
	private String verifycodeurl;
	private String registryurl;
	private String actionurl;
	private boolean isajax;
	private boolean background;
	private String backcss;
	private String submitevent;

	@Override
	public int doEndTag() throws JspException
	{
		// TODO Auto-generated method stub
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String path = request.getContextPath();
		try
		{
			if (pageContext.getSession().getAttribute("uname") == null)
			{
				if (background)
				{
					out.println("<div>");
				}
				else
				{
					out.println("<div class='backcss'>");
				}
				out.println("<form id='loginform' action='" + path + "/" + actionurl + "' method='post'>");
				out.println("<table style='margin-top:-25px;padding-top:-25px;margin-bottom:-25px;padding-bottom:-25px;' align='center' height='100%' width='90%'> ");
				out.println("<tr>");
				out.println("<td>用户名:");
				out.println("</td>");
				out.println("<td colspan='2'><input id='username' type='text' name='username' style='width: 125px;'>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>口&nbsp;&nbsp;令:");
				out.println("</td>");
				out.println("<td colspan='2'><input id='password' type='password' name='password' style='width: 125px;'>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>附加码:");
				out.println("</td>");
				out.println("<td valign='bottom'><input id='img' type='text' name='img' style='width: 60px;'></td><td align='right' style='padding-bottom:3px;' valign='bottom'><img valign='bottom' width='57' height='21' alt='看不清请单击！' src='"
						+ verifycodeurl + "' onclick=\"this.src='" + verifycodeurl + "?dt='+new Date();\"></td>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				if (isajax)
				{
					out.println("<td style='margin-top:-2px;padding-top:-2px;' colspan='3'><button class='bstyle'" + " onclick=\"" + submitevent + ";\">登录</button>&nbsp;&nbsp;&nbsp;&nbsp"
							+ ";&nbsp;<button onclick='cancel()' class='bstyle'>取消</button>&nbsp;&nbsp;&nbsp;&nbsp;" + "&nbsp;<button class='bstyle' onclick=\"location.href('" + registryurl + "');\">注册</button>");
					out.println("</td>");
				}
				else
				{
					out.println("<td colspan='3'><button class='bstyle'" + " onclick='document.all.loginform.submit();'>登录</button>&nbsp;&nbsp;&nbsp;&nbsp" + ";&nbsp;<button onclick='cancel()'  class='bstyle'>取消</button>&nbsp;&nbsp;&nbsp;&nbsp;"
							+ "&nbsp;<button class='bstyle' onclick=\"location.href('" + registryurl + "');\">注册</button>");
					out.println("</td>");
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</div>");
			}
			else
			{
				out.println("<table  height='100%' width='90%' border='0'> ");
				out.println("<tr align='center'  width='200'>");
				out.println("<td width='100%' colspan='2'><b>用户信息自维护</b>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='right'><img src='" + path + "/images/doc.gif'  width='20'  height='20'></td>");
				out.println("<td align='left'  valign='bottom'><a href='" + path + "/userinfo/userinfomodify.jsp' target='_self'>注册信息修改</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr align='left'>");
				out.println("<td align='right'><img src='" + path + "/images/doc.gif'  width='20'  height='20'></td>");
				out.println("<td valign='bottom'><a href='" + path + "/userinfo/orderquery.jsp' target='_self'>订单信息</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr align='left'>");
				out.println("<td align='right'><img src='" + path + "/images/doc.gif'  width='20'  height='20'></td>");
				out.println("<td valign='bottom'><a href='" + path + "/userinfo/transactionquery.jsp' target='_self'>交易信息</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr align='left'>");
				out.println("<td align='right'><img src='" + path + "/images/doc.gif'  width='20'  height='20'></td>");
				out.println("<td valign='bottom'><a href=\"javascript:isCancle('" + path + "/userinfo/userinfodelete.jsp')\" target='_self'>注销用户</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr align='left'>");
				out.println("<td align='right'><img src='" + path + "/images/doc.gif'  width='20'  height='20'></td>");
				out.println("<td valign='bottom'><a href=\"javascript:isUnregister('" + path + "/logout.do')\">退出登录</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getActionurl()
	{
		return actionurl;
	}

	public void setActionurl(String actionurl)
	{
		this.actionurl = actionurl;
	}

	public String getBackcss()
	{
		return backcss;
	}

	public void setBackcss(String backcss)
	{
		this.backcss = backcss;
	}

	public boolean getBackground()
	{
		return background;
	}

	public void setBackground(boolean background)
	{
		this.background = background;
	}

	public boolean isIsajax()
	{
		return isajax;
	}

	public void setIsajax(boolean isajax)
	{
		this.isajax = isajax;
	}

	public String getRegistryurl()
	{
		return registryurl;
	}

	public void setRegistryurl(String registryurl)
	{
		this.registryurl = registryurl;
	}

	public String getSubmitevent()
	{
		return submitevent;
	}

	public void setSubmitevent(String submitevent)
	{
		this.submitevent = submitevent;
	}

	public String getVerifycodeurl()
	{
		return verifycodeurl;
	}

	public void setVerifycodeurl(String verifycodeurl)
	{
		this.verifycodeurl = verifycodeurl;
	}
}
