package cn.javass.point.dao.hibernate;

import java.util.List;

import cn.javass.commons.Constants;
import cn.javass.dao.hibernate.BaseHibernageDao;
import cn.javass.point.dao.IGoodsCodeDao;
import cn.javass.point.model.GoodsCodeModel;

public class GoodsCodehibernateDao extends BaseHibernageDao<GoodsCodeModel, Integer> implements IGoodsCodeDao
{

	@Override
	public int countAllByGoodsId(int goodsid)
	{
		String hql = getCountAllHql() + " where goods.id=?";
		GoodsCodeModel gcm = unique(hql, goodsid);
		Number result = gcm == null ? 0 : 1;
		return result.intValue();
	}

	@Override
	public List<GoodsCodeModel> listAllByGoodsId(int pn, int goodsid)
	{
		String hql = getListAllHql() + " where goods.id=?";
		return list(hql, pn, Constants.DEFAULT_PAGE_SIZE, goodsid);
	}

	@Override
	public GoodsCodeModel getOneNotExchanged(int goodsid)
	{
		return null;
	}

}
