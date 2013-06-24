package aop.aop1;

/**
 * 代理类
 * 
 * @author Jert
 * 
 */
public class SomeServerProxyImpl implements SomeServerProxy
{
	private final SomeServerProxy target;

	public SomeServerProxyImpl(SomeServerProxy target)
	{
		this.target = target;
	}

	@Override
	public void doSome()
	{
		System.out.println("begin transaction... ...");
		target.doSome();
		System.out.println("end transaction... ...");
	}

}
