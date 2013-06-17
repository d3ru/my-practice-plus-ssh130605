package cn.javass.point.service;

import cn.javass.commons.pagination.Page;
import cn.javass.point.exception.NotCodeException;
import cn.javass.point.model.GoodsCodeModel;
import cn.javass.service.IBaseService;

public interface IGoodsCodeService extends IBaseService<GoodsCodeModel, Integer>
{
	public Page<GoodsCodeModel> listAllByGoods(int pn, int goodsId);

	public void save(int goodsId, String[] codes);

	public GoodsCodeModel buy(String username, int goodsId) throws NotCodeException;
}
