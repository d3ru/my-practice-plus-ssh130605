package ioc.ioc10;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ToUppercaseBean implements BeanPostProcessor
{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		Field[] fields = bean.getClass().getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		try
		{
			for (Field field : fields)
			{
				if (field.getType().equals(String.class))
				{
					String t = (String) field.get(bean);
					field.set(bean, t.toUpperCase());
				}
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

}
