package ioc.ioc2;

public class SomeBean
{
	private OtherBean ob;

	public void setOb(OtherBean ob)
	{
		this.ob = ob;
	}

	public SomeBean()
	{
		System.out.println("SomeBean default's constructor.");
	}

	@Override
	public String toString()
	{
		return "SomeBean' toString() : " + ob.toString();
	}
}
