package hibernate.demo1.service;

public class StockServiceAppException extends Exception
{

	private static final long serialVersionUID = -2392999144580550454L;

	public StockServiceAppException()
	{
		super();
	}

	public StockServiceAppException(String message)
	{
		super(message);
	}

}
