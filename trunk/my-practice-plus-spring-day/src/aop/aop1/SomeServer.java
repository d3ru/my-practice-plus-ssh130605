package aop.aop1;

/**
 * 目标类
 * 
 * @author Jert
 * 
 */
public class SomeServer implements SomeServerProxy
{

	@Override
	public void doSome()
	{
		System.out.println("SomeServer.doSome()");
	}

}
