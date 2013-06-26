package aop.aop7;

import aop.aop6.OtherServer;

public class OtherServerImpl implements OtherServer
{

	@Override
	public void doOther()
	{
		System.out.println("OtherServerImpl.doOther()");
	}

}
