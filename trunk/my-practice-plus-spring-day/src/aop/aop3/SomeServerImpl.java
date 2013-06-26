package aop.aop3;

/**
 * @author Jert
 * 
 */
public class SomeServerImpl implements SomeServer
{

	@Override
	public void doSome(int value) throws SomeServerAppException
	{
		try
		{
			Thread.sleep(1500);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if (value > 100)
		{
			throw new SomeServerAppException("value > 100");
		}

		System.out.println("SomeServerImpl.doSome()");
	}

}
