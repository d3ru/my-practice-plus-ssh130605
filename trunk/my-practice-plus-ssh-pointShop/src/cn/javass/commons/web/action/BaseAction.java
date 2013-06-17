package cn.javass.commons.web.action;

import cn.javass.commons.Constants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class BaseAction extends ActionSupport
{

	private static final long serialVersionUID = -7378392732483442609L;

	public final static String LIST = "list";
	public final static String REDIRECT = "redirect";
	public final static String ADD = "add";

	public final static String MODEL = "model";
	public final static String PAGE = Constants.DEFAULT_PAGE_NAME;
	public final static int DEFAULT_PAGE_SIZE = Constants.DEFAULT_PAGE_SIZE;

	private int pn;

	public int getPn()
	{
		return pn;
	}

	public void setPn(int pn)
	{
		this.pn = pn;
	}

	public ActionContext getActionContext()
	{
		return ActionContext.getContext();
	}

	public ValueStack getValueStack()
	{
		return getActionContext().getValueStack();
	}

}
