package ioc.ioc2App;

public class BankServer
{
	private Driver driver;

	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

	public void report()
	{
		driver.print();
	}
}
