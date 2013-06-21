package class01.ioc2;

public class OtherBean
{
	private String name;

	public OtherBean()
	{
		System.out.println("OtherBean default's constructor.");
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return this.name;
	}
}
