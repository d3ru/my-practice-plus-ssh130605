package aop;

import aop.aop1.SomeServer;
import aop.aop1.SomeServerProxy;
import aop.aop1.SomeServerProxyImpl;

public class Test
{

	public static void main(String[] args)
	{
		testAop1();
	}

	private static void testAop1()
	{
		SomeServerProxy target = new SomeServer();
		SomeServerProxy proxy = new SomeServerProxyImpl(target);
		proxy.doSome();
	}

	private static void testAop2()
	{

	}

	private static void testAop3()
	{

	}

	private static void testAop4()
	{

	}

	private static void testAop5()
	{

	}

	private static void testAop6()
	{

	}

}
