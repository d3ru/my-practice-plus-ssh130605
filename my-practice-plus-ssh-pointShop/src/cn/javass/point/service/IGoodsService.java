package cn.javass.point.service;

import cn.javass.commons.pagination.Page;
import cn.javass.point.model.GoodsModel;
import cn.javass.service.IBaseService;

public interface IGoodsService extends IBaseService<GoodsModel, Integer>
{
	public Page<GoodsModel> listAllPublished(int pn);
}
