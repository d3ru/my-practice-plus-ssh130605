package class01.ioc5;

public class SomeBean
{
	private OtherBean otherBean;

	public void setOtherBean(OtherBean otherBean)
	{
		this.otherBean = otherBean;
	}

	public SomeBean()
	{
		System.out.println("SomeBean default's constructor.");
	}

	// public SomeBean(OtherBean otherBean)
	// {
	// this.otherBean = otherBean;
	// }

	@Override
	public String toString()
	{
		return "SomeBean' toString() : " + otherBean.getName();
	}
}
