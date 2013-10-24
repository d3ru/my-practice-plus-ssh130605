package com.pan.evms.sl.datastorage.interfaces;

import java.util.List;

import com.pan.evms.domain.OperationFlow;

public interface IOperationFlowService
{
	void save(OperationFlow of);

	OperationFlow get(String id);

	List<OperationFlow> getByBranchId(String branchid);
}
