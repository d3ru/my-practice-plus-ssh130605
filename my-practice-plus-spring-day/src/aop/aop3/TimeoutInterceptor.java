package aop.aop3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeoutInterceptor implements MethodInterceptor
{
	private long timeout;

	public void setTimeout(long timeout)
	{
		this.timeout = timeout;
	}

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable
	{
		long b = System.currentTimeMillis();
		Object resoObject = arg0.proceed();
		long e = System.currentTimeMillis();
		long t = e - b;
		if (t > timeout)
		{
			System.out.println("TimeoutInterceptor >>> Warning.");
		}
		System.out.println("TimeoutInterceptor >>> execute cost " + t);
		return resoObject;
	}

}
