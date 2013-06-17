package cn.javass.commons.pagination;

import java.util.List;

public class QuickPageContext<E> implements IPageContext<E>
{
	private final int totalNumber;
	private final int pageSize;
	private List<E> items;

	public QuickPageContext(int totalNumber, int pageSize, List<E> items)
	{
		this.totalNumber = totalNumber;
		this.pageSize = pageSize == 0 ? 10 : pageSize;
		if (items != null)
			this.items = items;
	}

	@Override
	public int getPageCount()
	{
		int div = totalNumber / pageSize;
		return (totalNumber % pageSize) == 0 ? div : div + 1;
	}

	@Override
	public Page<E> getPage(int index)
	{
		int index2 = index > getPageCount() ? 1 : index;
		Page<E> page = new Page<E>();
		page.setHasNext(index2 < getPageCount());
		page.setHasPre(index2 > 1);
		page.setIndex(index2);
		page.setItems(items);
		return page;
	}

	@Override
	public int getPageSize()
	{
		return this.pageSize;
	}

	@Override
	public int getTotal()
	{
		return this.totalNumber;
	}

}
