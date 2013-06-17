package cn.javass.service;

import java.io.Serializable;
import java.util.List;

import cn.javass.commons.pagination.Page;

public interface IBaseService<M extends Serializable, pk extends Serializable>
{
	public M save(M model);

	public void saveOrUpdate(M model);

	public void update(M model);

	public void merye(M model);

	public void delete(pk id);

	public void deleteObject(M model);

	public M get(pk id);

	public int countAll();

	public List<M> listAll();

	public Page<M> listAll(int pn);

	public Page<M> listAll(int pn, int pageSize);
}
