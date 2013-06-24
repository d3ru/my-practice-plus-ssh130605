package ioc.ioc11;

import java.util.Date;

public class Customer
{
	private Date birthday;

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String printInfo()
	{
		return "birthday : " + birthday;
	}
}
