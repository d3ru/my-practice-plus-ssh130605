package ioc.ioc9;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Person implements ApplicationContextAware
{
	private Car car;
	private ApplicationContext context;

	public Car getCar()
	{
		return (Car) context.getBean("car");
	}

	public void setCar(Car car)
	{
		this.car = car;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.context = applicationContext;
	}

}
