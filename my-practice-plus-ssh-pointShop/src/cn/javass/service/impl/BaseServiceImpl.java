package cn.javass.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.javass.commons.Constants;
import cn.javass.commons.pagination.Page;
import cn.javass.commons.pagination.PageUtil;
import cn.javass.dao.IBaseDao;
import cn.javass.service.IBaseService;

public abstract class BaseServiceImpl<M extends Serializable, pk extends Serializable> implements IBaseService<M, pk>
{
	private IBaseDao<M, pk> dao;

	public void setDao(IBaseDao<M, pk> dao)
	{
		this.dao = dao;
	}

	public IBaseDao<M, pk> getDao()
	{
		return dao;
	}

	public M save(M model)
	{
		dao.save(model);
		return model;
	}

	@Override
	public void saveOrUpdate(M model)
	{
		dao.saveOrUpdate(model);
	}

	@Override
	public void update(M model)
	{
		dao.update(model);
	}

	@Override
	public void merye(M model)
	{
		dao.merge(model);
	}

	@Override
	public void delete(pk id)
	{
		dao.delete(id);
	}

	@Override
	public void deleteObject(M model)
	{
		dao.deleteObject(model);
	}

	@Override
	public M get(pk id)
	{
		return dao.get(id);
	}

	@Override
	public int countAll()
	{
		return dao.countAll();
	}

	@Override
	public List<M> listAll()
	{
		return dao.listAll();
	}

	@Override
	public Page<M> listAll(int pn)
	{
		return this.listAll(pn, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public Page<M> listAll(int pn, int pageSize)
	{
		Integer count = countAll();
		List<M> items = dao.listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, pageSize, items);
	};

}
