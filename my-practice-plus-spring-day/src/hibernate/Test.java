package hibernate;

import hibernate.demo1.service.StockService;
import hibernate.demo1.service.StockServiceAppException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

	/**
	 * 用hibernate方案实现jdbc.demo2
	 */
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("hibernate\\demo1\\context.xml");
		StockService service = (StockService) context.getBean("stockService");

		service.openAccount("xiaoQ", 10000);
		service.openStock("ali");
		try
		{
			service.buyStock("xiaoQ", 800, "ali");
		}
		catch (StockServiceAppException e)
		{
			e.printStackTrace();
		}

		System.out.println(service.getBalance("xiaoQ"));
		System.out.println(service.getQty("ali"));
	}

}
