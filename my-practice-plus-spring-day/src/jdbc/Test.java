package jdbc;

import jdbc.demo1.bo.Customer;
import jdbc.demo1.service.CustomerService;
import jdbc.demo1.service.CustomerServiceAppException;
import jdbc.demo2.service.StockService;
import jdbc.demo2.service.StockServiceAppException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

	public static void main(String[] args)
	{
		testDemo2();
	}

	/**
	 * Customer CURD demo.
	 */
	private static void testDemo1()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("jdbc\\demo1\\context2.xml");
		CustomerService service = (CustomerService) context.getBean("customerService");
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setName("zhanghua");
		service.register(c1);

		Customer c2 = new Customer();
		try
		{
			c2 = service.findCustomerByName("zhanghua");
		}
		catch (CustomerServiceAppException e)
		{
			e.printStackTrace();
		}
		System.out.println(c2.toString());
	}

	/**
	 * Account & Stock CURD demo.
	 */
	private static void testDemo2()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("jdbc\\demo2\\context.xml");
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
