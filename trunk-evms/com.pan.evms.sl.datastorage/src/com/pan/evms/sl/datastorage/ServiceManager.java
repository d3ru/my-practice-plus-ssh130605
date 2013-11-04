package com.pan.evms.sl.datastorage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pan.evms.sl.datastorage.interfaces.IOperationFlowService;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-24
 * @time 上午8:58:38
 */
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
