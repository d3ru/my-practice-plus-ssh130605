package aop.aop2;

public class OtherServer implements OtherServerProxy
{

	@Override
	public void doOther()
	{
		System.out.println("OtherServer.doOther()");
	}

}
