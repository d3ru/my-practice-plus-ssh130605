package tests;

import java.util.List;
import java.util.Random;

import com.pan.evms.domain.OperationFlow;
import com.pan.evms.sl.datastorage.ServiceManager;
import com.pan.evms.sl.datastorage.interfaces.IOperationFlowService;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-24
 * @time 上午8:57:20
 */
public class OperationFlowServiceTest
{
	private static IOperationFlowService service;

	public static void main(String[] args)
	{
		service = ServiceManager.getOperationFlowService();

		service.save(new OperationFlowServiceTest().genera());

		List<OperationFlow> list = service.getByBranchId("branch-1001");
		for (OperationFlow operationFlow : list)
		{
			System.out.println(operationFlow.toString());
		}
	}

	private OperationFlow genera()
	{
		OperationFlow of = new OperationFlow();
		of.setBranchid("branch-1001");
		of.setCounterid("counter-" + new Random().nextInt());
		of.setStaffid("staff-" + new Random().nextInt());
		return of;
	}
}
