package cn.javass.point.dao;

import java.util.List;

import cn.javass.dao.IBaseDao;
import cn.javass.point.model.GoodsModel;

public interface IGoodsDao extends IBaseDao<GoodsModel, Integer>
{
	List<GoodsModel> listAllPublished(int pn);

	int countAllPublished();
}
