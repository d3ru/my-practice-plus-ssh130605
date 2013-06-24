package ioc.ioc12;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring容器会自动地通过set方法把自己注入到实现了ApplicationContextAware接口的对象中<br/>
 * 这样CustomerServer的实现就可以通过所在的容器发布任何事件了，很简单，调用容器的publishEvent方法并传入所要发布的事件就行了。
 * 
 * @author Jert
 * 
 */
public class CustomerServer implements ApplicationContextAware
{
	private ApplicationContext context;

	public void register(String name)
	{
		if (name.equals("baduser"))
		{
			// 通过容器发布事件，这样容器内的监听器组件才会知道事件发生了。
			// BeanFactory容器没有发布事件的功能，所以就要用特性更为丰富的ApplicationContext。
			// 而容器中的Java对象要想知道它所在的容器，就一定要实现ApplicationContextAware接口，因此这个对象就难免要和Spring框架建立联系了，为了实现发布事件的功能，与框架的耦合在所难免。
			context.publishEvent(new CustomerEvent(this));
		}
		else
		{
			System.out.println(name + " register success.");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.context = applicationContext;
	}

}
