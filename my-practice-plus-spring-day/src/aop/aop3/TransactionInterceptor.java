package aop.aop3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TransactionInterceptor implements MethodInterceptor
{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable
	{
		TransactionManager.begin();
		Object resObject = arg0.proceed();
		TransactionManager.commit();
		return resObject;
	}

}
