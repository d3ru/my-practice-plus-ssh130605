package cn.javass.point.service.impl;

import java.util.List;

import cn.javass.commons.Constants;
import cn.javass.commons.pagination.Page;
import cn.javass.commons.pagination.PageUtil;
import cn.javass.point.dao.IGoodsDao;
import cn.javass.point.model.GoodsModel;
import cn.javass.point.service.IGoodsService;
import cn.javass.service.impl.BaseServiceImpl;

public class GoodsServiceImpl extends BaseServiceImpl<GoodsModel, Integer> implements IGoodsService
{

	@Override
	public Page<GoodsModel> listAllPublished(int pn)
	{
		int count = getGoodsDao().countAllPublished();
		List<GoodsModel> items = getGoodsDao().listAllPublished(pn);
		return PageUtil.getPage(count, pn, Constants.DEFAULT_PAGE_SIZE, items);

	}

	IGoodsDao getGoodsDao()
	{
		return (IGoodsDao) getDao();
	}

}
