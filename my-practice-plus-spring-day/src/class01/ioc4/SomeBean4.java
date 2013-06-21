package class01.ioc4;

public class SomeBean4
{
	private final String name;
	private final String alian;

	public SomeBean4(String name, String alian)
	{
		super();
		this.name = name;
		this.alian = alian;
	}

	public void infos()
	{
		System.out.println("name : " + name + " ; alian : " + alian);
	}

}
