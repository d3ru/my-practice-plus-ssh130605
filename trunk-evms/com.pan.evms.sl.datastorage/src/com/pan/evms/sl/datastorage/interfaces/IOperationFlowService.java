package com.pan.evms.sl.datastorage.interfaces;

import java.util.List;

import com.pan.evms.domain.OperationFlow;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-24
 * @time 上午8:59:22
 */
public interface IOperationFlowService
{
	void save(OperationFlow of);

	OperationFlow get(String id);

	List<OperationFlow> getByBranchId(String branchid);
}
