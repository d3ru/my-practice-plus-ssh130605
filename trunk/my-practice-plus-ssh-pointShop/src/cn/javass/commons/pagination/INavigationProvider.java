package cn.javass.commons.pagination;

public interface INavigationProvider
{
	public String build(Page<?> page, String url);
}
