package aop.aop4;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

public class SomeServerPointcut implements Pointcut
{

	@Override
	public ClassFilter getClassFilter()
	{
		return new ClassFilter()
		{

			@Override
			public boolean matches(Class clazz)
			{
				return true;
			}
		};
	}

	@Override
	public MethodMatcher getMethodMatcher()
	{
		return new MethodMatcher()
		{

			@Override
			public boolean matches(Method method, Class targetClass, Object[] args)
			{
				return false;
			}

			@Override
			public boolean matches(Method method, Class targetClass)
			{
				return method.getName().equals("doSome");
			}

			@Override
			public boolean isRuntime()
			{
				return false;
			}
		};
	}

}
