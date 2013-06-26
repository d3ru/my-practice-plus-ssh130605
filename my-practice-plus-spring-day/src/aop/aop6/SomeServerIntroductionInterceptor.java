package aop.aop6;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class SomeServerIntroductionInterceptor implements OtherServer, IntroductionInterceptor
{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable
	{
		// 判断调用的方法是指定类的方法
		if (implementsInterface(arg0.getMethod().getDeclaringClass()))
		{
			return arg0.getMethod().invoke(this, arg0.getArguments());
		}
		return arg0.proceed();
	}

	/**
	 * 判断intf是否是OtherServer接口的实现
	 */
	@Override
	public boolean implementsInterface(Class intf)
	{
		return intf.isAssignableFrom(OtherServer.class);
	}

	@Override
	public void doOther()
	{
		System.out.println("SomeServerIntroductionInterceptor.doOther()");
	}

}
