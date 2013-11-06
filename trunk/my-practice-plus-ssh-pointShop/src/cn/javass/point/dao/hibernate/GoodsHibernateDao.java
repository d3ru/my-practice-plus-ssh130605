package cn.javass.point.dao.hibernate;

import java.util.List;

import cn.javass.commons.Constants;
import cn.javass.dao.hibernate.BaseHibernageDao;
import cn.javass.point.dao.IGoodsDao;
import cn.javass.point.model.GoodsModel;

public class GoodsHibernateDao extends BaseHibernageDao<GoodsModel, Integer> implements IGoodsDao
{

	@Override
	public void delete(Integer id)
	{
		GoodsModel goods = get(id);
		goods.setDelete(true);
		update(goods);
	};

	@Override
	// 覆盖掉父类的getCountAllHql方法，查询不包括逻辑删除的记录
	protected String getCountAllHql()
	{
		return super.getCountAllHql() + " where deleted=false";
	}

	@Override
	// 覆盖掉父类的getListAllHql方法，查询不包括逻辑删除的记录
	protected String getListAllHql()
	{
		return super.getListAllHql() + " where deleted=false";
	}

	@Override
	public List<GoodsModel> listAllPublished(int pn)
	{
		String hql = getListAllHql() + " and published=true";
		return list(hql, pn, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public int countAllPublished()
	{
		String hql = getCountAllHql() + " and published=true";
		GoodsModel gm = unique(hql);
		Number result = gm == null ? 0 : 1;
		return result.intValue();
	}

}
