package cn.javass.commons.pagination.provider;

import cn.javass.commons.pagination.AbstractNavigationProvider;
import cn.javass.commons.pagination.Page;

public class NavigationProvider1 extends AbstractNavigationProvider
{

	@Override
	public String build(Page<?> page, String url)
	{
		StringBuilder sb = new StringBuilder();
		if (page.isHasPre())
		{
			String newUrl = append(url, "pn", page.getIndex() - 1);
			sb.append("<a href=\"" + newUrl + "\">上一页</a>&nbsp;");
		}
		if (page.isHasNext())
		{
			String newUrl = append(url, "pn", page.getIndex() + 1);
			sb.append("<a href=\"" + newUrl + "\">下一页</a>&nbsp;");
		}
		return sb.toString();
	}

}
