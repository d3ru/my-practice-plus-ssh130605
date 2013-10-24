package com.pan.evms.sl.datastorage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pan.evms.sl.datastorage.interfaces.IOperationFlowService;

public class ServiceManager
{
	public static IOperationFlowService getOperationFlowService()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		return (IOperationFlowService) context.getBean("operationFlowService");
	}

	public static String getResource()
	{
		return ServiceManager.class.getResource("/").getPath();
	}

	public static String getResourceN()
	{

		return ServiceManager.class.getResource("/").toString();
	}

	public static void main(String[] args)
	{
		System.out.println(getResource());
		System.out.println(getResourceN());
	}

}
