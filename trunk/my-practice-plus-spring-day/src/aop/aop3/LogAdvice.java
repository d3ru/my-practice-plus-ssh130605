package aop.aop3;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置通知
 * 
 * @author Jert
 * 
 */
public class LogAdvice implements MethodBeforeAdvice
{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable
	{
		System.out.println("LogAdvice >>> target : " + target.getClass().getName() + " , method : " + method.getName() + " is invoking... ...");
	}

}
