package aop.aop3;

public class TransactionManager
{
	public static void begin()
	{
		System.out.println("TransactionManager >>> begin transaction");
	}

	public static void commit()
	{
		System.out.println("TransactionManager >>> commit transaction");
	}
}
