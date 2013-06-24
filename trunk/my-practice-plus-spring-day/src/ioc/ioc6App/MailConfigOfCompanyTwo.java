package ioc.ioc6App;

public class MailConfigOfCompanyTwo implements MailConfig
{

	@Override
	public void doIt()
	{
		System.out.println("MailConfigOfCompanyTwo.doIt()");
	}

}
