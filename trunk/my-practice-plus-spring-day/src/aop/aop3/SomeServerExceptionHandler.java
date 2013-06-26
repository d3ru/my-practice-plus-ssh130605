package aop.aop3;

import org.springframework.aop.ThrowsAdvice;

public class SomeServerExceptionHandler implements ThrowsAdvice
{
	public void afterThrowing(SomeServerAppException exception)
	{
		System.out.println("SomeServerExceptionHandler >>> " + exception.getMessage() + " disponsed.");
	}

	public void afterThrowing(Exception exception)
	{
		System.out.println("SomeServerExceptionHandler >>> " + exception.getMessage() + " disponsed.");
	}
}
