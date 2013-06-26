package aop.aop4;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class SomeServerPointcutAdvisor implements PointcutAdvisor
{

	private Pointcut pointcut;
	private Advice advice;

	public void setAdvice(Advice advice)
	{
		this.advice = advice;
	}

	public void setPointcut(Pointcut pointcut)
	{
		this.pointcut = pointcut;
	}

	@Override
	public Advice getAdvice()
	{
		return advice;
	}

	@Override
	public boolean isPerInstance()
	{
		return true;
	}

	@Override
	public Pointcut getPointcut()
	{
		return pointcut;
	}

}
