package cn.javass.commons.pagination;

import java.util.Collections;
import java.util.List;

public class Page<E>
{
	private boolean hasPre;
	private boolean hasNext;
	private int index;
	private List<E> items;

	public boolean isHasPre()
	{
		return hasPre;
	}

	public void setHasPre(boolean hasPre)
	{
		this.hasPre = hasPre;
	}

	public boolean isHasNext()
	{
		return hasNext;
	}

	public void setHasNext(boolean hasNext)
	{
		this.hasNext = hasNext;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public List<E> getItems()
	{
		return items == null ? Collections.<E> emptyList() : this.items;
	}

	public void setItems(List<E> items)
	{
		this.items = items;
	}

}
