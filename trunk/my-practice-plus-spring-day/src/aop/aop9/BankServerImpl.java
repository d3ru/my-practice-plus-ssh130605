package aop.aop9;

public class BankServerImpl implements BankServer
{

	@Override
	public double openAccount(String username, String password, double awt)
	{
		System.out.println("BankServerImpl.openAccount()");
		return awt;
	}

}
