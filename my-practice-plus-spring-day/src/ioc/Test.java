package ioc;

import ioc.ioc1.HelloBean;
import ioc.ioc11.Customer;
import ioc.ioc12.CustomerServer;
import ioc.ioc2.SomeBean;
import ioc.ioc2App.BankServer;
import ioc.ioc3.SomeBean3;
import ioc.ioc4.SomeBean4;
import ioc.ioc6App.MailConfig;
import ioc.ioc8.Student;
import ioc.ioc8Expand.Dog;
import ioc.ioc9.Car;
import ioc.ioc9.Person;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc下的所有包的测试类。 方法名规则 ：test+包名
 * 
 * @author Jert
 * 
 */
public class Test
{

	public static void main(String[] args)
	{
		testIoc12();
	}

	/**
	 * set注入
	 */
	private static void testIoc1()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc1\\applicationContext.xml");
		HelloBean hb = (HelloBean) context.getBean("helloBean");
		System.out.println(hb.sayHello());
	}

	/**
	 * set注入
	 */
	private static void testIoc2()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "ioc\\ioc2\\somebean.xml", "ioc\\ioc2\\otherbean.xml" });
		SomeBean sb = (SomeBean) context.getBean("someBean");
		System.out.println(sb.toString());
	}

	/**
	 * set注入
	 */
	private static void testIoc2App()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc2App\\server.xml");
		BankServer bs = (BankServer) context.getBean("bankServer");
		bs.report();
	}

	/**
	 * set注入
	 */
	private static void testIoc3()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc3\\somebean.xml");
		SomeBean3 sb = (SomeBean3) context.getBean("someBean");
		sb.infos();
	}

	/**
	 * 构造器注入
	 */
	private static void testIoc4()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc4\\somebean.xml");
		SomeBean4 sb = (SomeBean4) context.getBean("someBean");
		sb.infos();
	}

	/**
	 * 自动装配：自动检测
	 */
	private static void testIoc5()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc5\\somebean.xml");
		ioc.ioc5.SomeBean sb = (ioc.ioc5.SomeBean) context.getBean("someBean");
		System.out.println(sb.toString());
	}

	/**
	 * 静态工厂方法
	 */
	private static void testIoc6()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc6\\conn.xml");
		Connection conn = (Connection) context.getBean("connection");
		System.out.println(conn);
	}

	/**
	 * 静态工厂方法应用
	 */
	private static void testIoc6App()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc6App\\mailConfig.xml");
		MailConfig config = (MailConfig) context.getBean("mailConfig");
		config.doIt();
	}

	/**
	 * 实例工厂
	 */
	private static void testIoc7()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc7\\conn.xml");
		Connection conn = (Connection) context.getBean("connection");
		System.out.println(conn);
	}

	/**
	 * 描述父类，通过文件配置实现子类继承
	 */
	private static void testIoc8()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc8\\student.xml");
		Student student = (Student) context.getBean("stu1");
		System.out.println(student);
	}

	/**
	 * 描述子类，通过文件配置实现抽象父类
	 */
	private static void testIoc8Expand()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc8Expand\\animal.xml");
		Dog dog = (Dog) context.getBean("dog");
		System.out.println(dog);
	}

	/**
	 * scope： bean的作用域的使用
	 */
	private static void testIoc9()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc9\\context.xml");
		Person p1 = (Person) context.getBean("person");
		Person p2 = (Person) context.getBean("person");
		System.out.println(p1 == p2);

		Car c1 = p1.getCar();
		Car c2 = p2.getCar();
		System.out.println(c1 == c2);
	}

	/**
	 * BeanPostProcessor 使用
	 * 
	 * PropertyPlaceholderConfigurer 使用
	 */
	private static void testIoc10()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc10\\context.xml");
		ioc.ioc10.SomeBean sb = (ioc.ioc10.SomeBean) context.getBean("someBean");
		System.out.println(sb.getStr());
	}

	/**
	 * PropertyEditorSupport 使用
	 * 
	 * CustomEditorConfigurer 使用
	 */
	private static void testIoc11()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc11\\context.xml");
		Customer customer = (Customer) context.getBean("customer");
		System.out.println(customer.printInfo());
	}

	/**
	 * 事件监听
	 */
	private static void testIoc12()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ioc\\ioc12\\context.xml");
		CustomerServer cs = (CustomerServer) context.getBean("customerServer");
		cs.register("baduser");
		cs.register("admin");
	}

}
