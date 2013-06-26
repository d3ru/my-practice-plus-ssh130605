package aop;

import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.aop1.SomeServer;
import aop.aop1.SomeServerProxy;
import aop.aop1.SomeServerProxyImpl;
import aop.aop2.OtherServer;
import aop.aop2.OtherServerProxy;
import aop.aop2.TransactionInvocationHandler;
import aop.aop3.SomeServerAppException;
import aop.aop7.OtherServerImpl;
import aop.aop9.BankServer;

public class Test
{

	public static void main(String[] args)
	{
		testExec();
	}

	/**
	 * proxy 代理 target 做一些事情
	 * 
	 * 适合的应用：比如日志。
	 */
	private static void testAop1()
	{
		SomeServerProxy target = new SomeServer();
		SomeServerProxy proxy = new SomeServerProxyImpl(target);
		proxy.doSome();
	}

	/**
	 * "增值服务"
	 */
	private static void testAop2()
	{
		aop.aop2.SomeServerProxy target = new aop.aop2.SomeServer();
		aop.aop2.SomeServerProxy proxy = (aop.aop2.SomeServerProxy) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new TransactionInvocationHandler(target));
		proxy.doSome();
		System.out.println(proxy.getClass().getName());

		System.out.println();

		OtherServer target2 = new OtherServer();
		OtherServerProxy proxy2 = (OtherServerProxy) Proxy.newProxyInstance(target2.getClass().getClassLoader(), target2.getClass().getInterfaces(), new TransactionInvocationHandler(target2));
		proxy2.doOther();
		System.out.println(proxy2.getClass().getName());
	}

	/**
	 * 切面的实现 几种类型： <br/>
	 * (1)org.springframework.aop.MethodBeforeAdvice <br/>
	 * (2)org.springframework.aop.AfterReturningAdvice <br/>
	 * (3)org.aopalliance.intercept.MethodInterceptor <br/>
	 * (4)org.springframework.aop.ThrowsAdvice <br/>
	 * 
	 * org.springframework.aop.framework.ProxyFactoryBean
	 * 
	 * 需要添加cglib.jar
	 */
	private static void testAop3()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop\\aop3\\context.xml");
		aop.aop3.SomeServer server = (aop.aop3.SomeServer) ac.getBean("someServer");
		try
		{
			server.doSome(80);
		}
		catch (SomeServerAppException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * pointcut : 根据方法和类决定在什么地方织入通知 <br/>
	 * org.springframework.aop.Pointcut <br/>
	 * org.springframework.aop.PointcutAdvisor <br/>
	 * 
	 */
	private static void testAop4()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop\\aop4\\context.xml");
		aop.aop4.SomeServer server = (aop.aop4.SomeServer) ac.getBean("someServer");
		server.doOther();
		server.doSome();
		server.doOther2();
	}

	/**
	 * org.springframework.aop.ClassFilter <br/>
	 * org.springframework.aop.support.NameMatchMethodPointcutAdvisor <br/>
	 */
	private static void testAop5()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop\\aop5\\context.xml");
		aop.aop4.SomeServer server = (aop.aop4.SomeServer) ac.getBean("someServer");
		server.doOther();
		server.doSome();
		server.doOther2();
	}

	/**
	 * Introduction引入,在不修改目标对象的源代码的情况下，为目标对象增加方法和属性
	 */

	private static void testAop6()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("aop\\aop6\\context.xml");
		aop.aop6.SomeServer server = (aop.aop6.SomeServer) context.getBean("someServer");
		server.doSome();
		((aop.aop6.OtherServer) server).doOther();
	}

	/**
	 * 自动代理：org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
	 */
	private static void testAop7()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("aop\\aop7\\context.xml");
		aop.aop6.SomeServer ss = (aop.aop6.SomeServer) context.getBean("someServerTarget");
		aop.aop6.OtherServer os = (OtherServerImpl) context.getBean("otherServerTarget");
		ss.doSome();
		os.doOther();
		System.out.println(ss.getClass().getName());
		System.out.println(os.getClass().getName());
	}

	/**
	 * 自动代理：org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
	 */
	private static void testAop8()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("aop\\aop8\\context.xml");
		aop.aop6.SomeServer ss = (aop.aop6.SomeServer) context.getBean("someServerTarget");
		aop.aop6.OtherServer os = (aop.aop6.OtherServer) context.getBean("otherServerTarget");
		ss.doSome();
		os.doOther();
		System.out.println(ss.getClass().getName());
		System.out.println(os.getClass().getName());
	}

	/**
	 * 使用aspectj.jar; <br/>
	 * 配置中使用<aop:aspectj-autoproxy />
	 */
	private static void testAop9()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("aop\\aop9\\context.xml");
		BankServer bs = (BankServer) ac.getBean("bankServer");
		System.out.println(bs.getClass().getName());
		bs.openAccount("admin", "123456", 1000);
	}

	/**
	 * org.springframework.aop.framework.ProxyFactoryBean
	 */
	private static void testExec()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("aop\\exec\\context.xml");
		aop.exec.SomeServer server = (aop.exec.SomeServer) context.getBean("someServer");
		server.doSome(200);
	}

}
