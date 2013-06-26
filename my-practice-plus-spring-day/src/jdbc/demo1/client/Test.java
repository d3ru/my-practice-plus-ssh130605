package jdbc.demo1.client;

import jdbc.demo1.bo.Customer;
import jdbc.demo1.service.CustomerService;
import jdbc.demo1.service.CustomerServiceAppException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{

	public static void main(String[] args)
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

}
