package aop.aop2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 将"增值服务"代码植入到目标代码中。
 * 
 * @author Jert
 * 
 */
public class TransactionInvocationHandler implements InvocationHandler
{
	private final Object target;

	public TransactionInvocationHandler(Object target)
	{
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		System.out.println("begining transaction... ...");
		Object result = method.invoke(target, args);
		System.out.println("endding transaction... ...");
		return result;
	}

}
