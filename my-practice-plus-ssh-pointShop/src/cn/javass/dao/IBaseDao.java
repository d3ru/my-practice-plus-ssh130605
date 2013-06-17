package cn.javass.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<M extends Serializable, pk extends Serializable>
{
	public void save(M m);

	public void saveOrUpdate(M m);

	public void update(M m);

	public void merge(M m);

	public void delete(pk id);

	public void deleteObject(M m);

	public M get(pk id);

	public int countAll();

	public List<M> listAll();

	public List<M> listAll(int pn, int pageSize);
}
