package class01.ioc3;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SomeBean3
{
	private Set<?> setprops;
	private List<?> listprops;
	private Map<?, ?> mapprops;
	private Properties props;

	public void setListprops(List<?> listprops)
	{
		this.listprops = listprops;
	}

	public void setSetprops(Set<?> setprops)
	{
		this.setprops = setprops;
	}

	public void setMapprops(Map<?, ?> mapprops)
	{
		this.mapprops = mapprops;
	}

	public void setProps(Properties props)
	{
		this.props = props;
	}

	public void infos()
	{
		System.out.println("setprops : " + setprops);
		System.out.println("listprops : " + listprops);
		System.out.println("mapprops : " + mapprops);
		System.out.println("props : " + props);
	}
}
