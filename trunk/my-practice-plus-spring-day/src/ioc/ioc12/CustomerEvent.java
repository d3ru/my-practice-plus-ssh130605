package ioc.ioc12;

import org.springframework.context.ApplicationEvent;

public class CustomerEvent extends ApplicationEvent
{

	private static final long serialVersionUID = 1L;

	public CustomerEvent(Object source)
	{
		super(source);
	}

}
