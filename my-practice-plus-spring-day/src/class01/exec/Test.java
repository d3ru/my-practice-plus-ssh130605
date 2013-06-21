package class01.exec;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import class01.ioc1.HelloBean;
import class01.ioc2.SomeBean;
import class01.ioc3.SomeBean3;
import class01.ioc4.SomeBean4;

/**
 * class01下的所有包的测试类。 方法名规则 ：test+包名
 * 
 * @author Jert
 * 
 */
public class Test
{

	public static void main(String[] args)
	{
		testIoc6();
	}

	/**
	 * set注入
	 */
	private static void testIoc1()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("class01\\ioc1\\applicationContext.xml");
		HelloBean hb = (HelloBean) context.getBean("helloBean");
		System.out.println(hb.sayHello());
	}

	/**
	 * set注入
	 */
	private static void testIoc2()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "class01\\ioc2\\somebean.xml", "class01\\ioc2\\otherbean.xml" });
		SomeBean sb = (SomeBean) context.getBean("someBean");
		System.out.println(sb.toString());
	}

	/**
	 * set注入
	 */
	private static void testIoc3()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("class01\\ioc3\\somebean.xml");
		SomeBean3 sb = (SomeBean3) context.getBean("someBean");
		sb.infos();
	}

	/**
	 * 构造器注入
	 */
	private static void testIoc4()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("class01\\ioc4\\somebean.xml");
		SomeBean4 sb = (SomeBean4) context.getBean("someBean");
		sb.infos();
	}

	/**
	 * 自动装配：自动检测
	 */
	private static void testIoc5()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("class01\\ioc5\\somebean.xml");
		class01.ioc5.SomeBean sb = (class01.ioc5.SomeBean) context.getBean("someBean");
		System.out.println(sb.toString());
	}

	private static void testIoc6()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("class01\\ioc6\\conn.xml");
		Connection conn = (Connection) context.getBean("conn");
		System.out.println(conn);
	}
}
