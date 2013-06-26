package aop.aop9;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BankServerAspect
{
	@Before("execution(* openAccount(..))")
	public void log(JoinPoint jp)
	{
		String methodName = jp.getSignature().getName();
		for (Object arg : jp.getArgs())
		{
			System.out.println("arg is : " + arg);
		}
		System.out.println(methodName + " execute at " + new Date());
	}

	@AfterReturning("execution(* openAccount(..))")
	public void welcome(JoinPoint jp)
	{
		System.out.println("welcome to use");
	}
}
