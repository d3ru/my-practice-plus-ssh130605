package hibernate.demo1.dao;

import hibernate.demo1.bo.Stock;

public interface StockDao
{
	void save(Stock s);

	Stock findByName(String name);

	void modify(Stock s);
}
