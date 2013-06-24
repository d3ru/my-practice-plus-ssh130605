package ioc.ioc8Expand;

public class Cat
{
	private String name;
	private String color;

	public void setName(String name)
	{
		this.name = name;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	@Override
	public String toString()
	{
		return "name : " + name + " color : " + color;
	}
}
