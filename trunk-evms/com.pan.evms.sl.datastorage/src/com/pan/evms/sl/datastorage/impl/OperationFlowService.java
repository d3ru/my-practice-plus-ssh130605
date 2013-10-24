package com.pan.evms.sl.datastorage.impl;

import java.util.List;

import com.pan.evms.dal.interfaces.IOperationFlowDal;
import com.pan.evms.domain.OperationFlow;
import com.pan.evms.sl.datastorage.interfaces.IOperationFlowService;

public class OperationFlowService implements IOperationFlowService
{

	private IOperationFlowDal operationFlowDal;

	public void setOperationFlowDal(IOperationFlowDal operationFlowDal)
	{
		this.operationFlowDal = operationFlowDal;
	}

	@Override
	public void save(OperationFlow of)
	{
		operationFlowDal.save(of);
	}

	@Override
	public OperationFlow get(String id)
	{
		return null;
	}

	@Override
	public List<OperationFlow> getByBranchId(String branchid)
	{
		return operationFlowDal.getByBranchId(branchid);
	}

}
