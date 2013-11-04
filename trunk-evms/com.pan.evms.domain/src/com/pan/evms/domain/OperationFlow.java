package com.pan.evms.domain;

import java.io.Serializable;

/**
 * 
 * @author 杨俊涛 yangjuntao@p-an.com
 * 
 * @date 2013-10-24
 * @time 上午8:58:48
 */
public class OperationFlow implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3160774661873965119L;

	private Long id;
	private String branchid;
	private String counterid;
	private String staffid;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getBranchid()
	{
		return branchid;
	}

	public void setBranchid(String branchid)
	{
		this.branchid = branchid;
	}

	public String getCounterid()
	{
		return counterid;
	}

	public void setCounterid(String counterid)
	{
		this.counterid = counterid;
	}

	public String getStaffid()
	{
		return staffid;
	}

	public void setStaffid(String staffid)
	{
		this.staffid = staffid;
	}

	@Override
	public String toString()
	{
		return "OperationFlow [id=" + id + ", branchid=" + branchid + ", counterid=" + counterid + ", staffid=" + staffid + "]";
	}

}
