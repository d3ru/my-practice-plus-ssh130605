package ioc.ioc6App;


public class StaticFactoryBean
{
	public static MailConfig getMailConfig(String mailconfig)
	{
		if ("companyOne".equals(mailconfig))
		{
			return new MailConfigOfCompanyOne();
		}
		if ("companyTwo".equals(mailconfig))
		{
			return new MailConfigOfCompanyTwo();
		}
		return null;
	}
}
