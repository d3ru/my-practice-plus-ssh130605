package cn.javass.commons.pagination;

import java.util.List;

public class PageUtil
{
	public static int getStart(int pageNumber, int pageSize)
	{
		return getStart(Integer.MAX_VALUE, pageNumber, pageSize);
	}

	public static int getStart(int totalNumber, int pageNumber, int pageSize)
	{
		int start = (pageNumber - 1) * pageSize;
		if (start >= totalNumber)
			start = 0;
		return start;
	}

	public static <E> Page<E> getPage(int totalNumber, int pageNumber, int pageSize, List<E> items)
	{
		IPageContext<E> context = new QuickPageContext<E>(totalNumber, pageSize, items);
		return context.getPage(pageNumber);
	}
}
