package cn.javass.point.service.impl;

import java.util.Date;
import java.util.List;

import cn.javass.commons.Constants;
import cn.javass.commons.pagination.Page;
import cn.javass.commons.pagination.PageUtil;
import cn.javass.point.dao.IGoodsCodeDao;
import cn.javass.point.exception.NotCodeException;
import cn.javass.point.model.GoodsCodeModel;
import cn.javass.point.model.GoodsModel;
import cn.javass.point.service.IGoodsCodeService;
import cn.javass.point.service.IGoodsService;
import cn.javass.service.impl.BaseServiceImpl;

public class GoodsCodeServiceImpl extends BaseServiceImpl<GoodsCodeModel, Integer> implements IGoodsCodeService
{
	private IGoodsService goodsService;

	public IGoodsService getGoodsService()
	{
		return goodsService;
	}

	private IGoodsCodeDao getGoodsCodeDao()
	{
		return (IGoodsCodeDao) getDao();
	}

	@Override
	public Page<GoodsCodeModel> listAllByGoods(int pn, int goodsId)
	{
		int count = getGoodsCodeDao().countAllByGoodsId(goodsId);
		List<GoodsCodeModel> items = getGoodsCodeDao().listAllByGoodsId(pn, goodsId);
		return PageUtil.getPage(count, pn, Constants.DEFAULT_PAGE_SIZE, items);
	}

	@Override
	/**
	 * save方法中如果兑换码有上千个怎么办？这时就需要批处理了，通过批处理比如20条一提交数据库来提高性能。
	 */
	public void save(int goodsId, String[] codes)
	{
		GoodsModel goodsModel = goodsService.get(goodsId);
		for (String code : codes)
		{
			if (code != null && !code.isEmpty())
			{
				GoodsCodeModel goodsCodeModel = new GoodsCodeModel();
				goodsCodeModel.setCode(code);
				goodsCodeModel.setGoods(goodsModel);
				save(goodsCodeModel);
			}
		}
	}

	@Override
	/**
	 * buy方法就要考虑多个用户同时购买同一个兑换码如何处理？
	 */
	public GoodsCodeModel buy(String username, int goodsId) throws NotCodeException
	{
		GoodsCodeModel goodsCodeModel = getGoodsCodeDao().getOneNotExchanged(goodsId);
		if (goodsCodeModel == null)
		{
			throw new NotCodeException();
		}
		goodsCodeModel.setExchanged(true);
		goodsCodeModel.setExchangeTime(new Date());
		goodsCodeModel.setUsername(username);
		save(goodsCodeModel);
		return goodsCodeModel;
	}

}
