package aop.aop5;

import org.springframework.aop.ClassFilter;

public class MyClassFilter implements ClassFilter
{

	@Override
	public boolean matches(Class clazz)
	{
		return clazz.getName().toString().contains("SomeServerImpl");
	}

}
