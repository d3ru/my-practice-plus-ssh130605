package jdbc.demo2.dao;

import jdbc.demo2.bo.Stock;

public interface StockDao
{
	void save(Stock s);

	Stock findByName(String name);

	void modify(Stock s);
}
