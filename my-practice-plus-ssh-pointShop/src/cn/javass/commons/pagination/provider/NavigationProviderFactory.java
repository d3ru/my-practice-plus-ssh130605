package cn.javass.commons.pagination.provider;

import java.util.HashMap;
import java.util.Map;

import cn.javass.commons.pagination.INavigationProvider;

public class NavigationProviderFactory
{
	private static Map<String, INavigationProvider> provider = new HashMap<String, INavigationProvider>();

	static
	{
		provider.put("v1", new NavigationProvider1());
		provider.put("v2", new NavigationProvider2());
	}

	public static INavigationProvider getNavigationProvider(String version)
	{
		if (version == null)
			return provider.get("v1");

		return provider.get(version);
	}
}
