package ioc.ioc9;

public class Car
{
	private String name;

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "name : " + name;
	}
}
