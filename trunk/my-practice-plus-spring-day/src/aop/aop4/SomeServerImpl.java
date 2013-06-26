package aop.aop4;

public class SomeServerImpl implements SomeServer
{

	@Override
	public void doSome()
	{
		System.out.println("SomeServerImpl.doSome()");
	}

	@Override
	public void doOther()
	{
		System.out.println("SomeServerImpl.doOther()");
	}

	@Override
	public void doOther2()
	{
		System.out.println("SomeServerImpl.doOther2()");
	}

}
