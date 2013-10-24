package tests;

import java.util.List;
import java.util.Random;

import com.pan.evms.domain.OperationFlow;
import com.pan.evms.sl.datastorage.ServiceManager;
import com.pan.evms.sl.datastorage.interfaces.IOperationFlowService;

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
