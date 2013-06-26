package aop.exec;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import aop.aop3.SomeServerAppException;

public class AuditAdvice implements MethodBeforeAdvice
{
	private int maxValue;

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable
	{
		String methodName = method.getName();
		if (methodName.startsWith("do"))
		{
			if (args != null)
			{
				int t = (Integer) args[0];
				if (t > maxValue)
				{
					System.out.println("AuditAdvice.before() >>> value is " + t);
					throw new SomeServerAppException("value is " + t + " > maxValue " + maxValue);
				}
			}
		}
	}

}
