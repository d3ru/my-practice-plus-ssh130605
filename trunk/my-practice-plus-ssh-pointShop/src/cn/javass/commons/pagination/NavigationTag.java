package cn.javass.commons.pagination;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.javass.commons.Constants;
import cn.javass.commons.pagination.provider.NavigationProviderFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class NavigationTag extends TagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597776385139754304L;

	private String pageName = Constants.DEFAULT_PAGE_NAME;

	private String url;

	private String version = "v1.0";

	@Override
	public int doStartTag() throws JspException
	{
		Page<?> page = (Page<?>) getValueStack().findValue(pageName);
		if (page == null)
			return SKIP_BODY;
		try
		{
			JspWriter writer = pageContext.getOut();
			writer.print("<form action='" + url + "' method='post'>");
			String link = NavigationProviderFactory.getNavigationProvider(version).build(page, url);
			writer.print(link);
			writer.print("&nbsp;<input id='pn' size='3'/>");
			writer.print("<input type='submit' value='跳转'/>");
			writer.print("</form>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public void setPageName(String pageName)
	{
		this.pageName = pageName;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	private ValueStack getValueStack()
	{
		return ActionContext.getContext().getValueStack();
	}

}
