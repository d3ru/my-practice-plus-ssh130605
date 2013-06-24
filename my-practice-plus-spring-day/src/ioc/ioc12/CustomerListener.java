package ioc.ioc12;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class CustomerListener implements ApplicationListener
{

	@Override
	public void onApplicationEvent(ApplicationEvent event)
	{
		if (event instanceof CustomerEvent)
		{
			System.out.println("illegal name.");
		}
	}

}
