package com.pan.evms.dal.interfaces;

import java.util.List;

import com.pan.evms.domain.OperationFlow;

public interface IOperationFlowDal
{
	void save(OperationFlow of);

	OperationFlow get(String id);

	List<OperationFlow> getByBranchId(String branchid);

	void update(OperationFlow of);
}
