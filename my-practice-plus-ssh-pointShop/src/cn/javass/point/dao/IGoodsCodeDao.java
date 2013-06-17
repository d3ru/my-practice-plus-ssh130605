package cn.javass.point.dao;

import java.util.List;

import cn.javass.dao.IBaseDao;
import cn.javass.point.model.GoodsCodeModel;

public interface IGoodsCodeDao extends IBaseDao<GoodsCodeModel, Integer>
{
	public int countAllByGoodsId(int goodsid);

	public List<GoodsCodeModel> listAllByGoodsId(int pn, int goodsid);

	public GoodsCodeModel getOneNotExchanged(int goodsid);
}
