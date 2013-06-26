package aop.aop3;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class WelcomeAdvice implements AfterReturningAdvice
{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable
	{
		System.out.println("WelcomeAdvice >>> welcome to use.");
	}

}
