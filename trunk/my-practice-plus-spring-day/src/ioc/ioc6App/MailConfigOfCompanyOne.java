package ioc.ioc6App;

public class MailConfigOfCompanyOne implements MailConfig
{

	@Override
	public void doIt()
	{
		System.out.println("MailConfigOfCompanyOne.doIt()");
	}

}
