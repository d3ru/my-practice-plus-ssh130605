package transaction;

import hibernate.demo1.service.StockService;
import hibernate.demo1.service.StockServiceAppException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

	/**
	 * 事务机制
	 * 
	 * context.xml 用配置代理事务让hibernate.demo1拥有事务机制<br/>
	 * context2.xml 用spring的注解事务机制让hibernate.demo1拥有事务机制
	 */
	public static void main(String[] args)
	{

		ApplicationContext context = new ClassPathXmlApplicationContext("transaction\\context2.xml");
		StockService service = (StockService) context.getBean("stockService");

		service.openAccount("xiaoQ", 10000);
		service.openStock("ali");
		try
		{
			service.buyStock("xiaoQ", 1200, "ali");
		}
		catch (StockServiceAppException e)
		{
			try
			{
				service.buyStock("xiaoQ", 800, "ali");
			}
			catch (StockServiceAppException e1)
			{
				e1.printStackTrace();
			}
		}

		System.out.println(service.getBalance("xiaoQ"));
		System.out.println(service.getQty("ali"));

	}

}
