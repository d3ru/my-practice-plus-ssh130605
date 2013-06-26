package jdbc.demo2.service.impl;

import jdbc.demo2.bo.Account;
import jdbc.demo2.bo.Stock;
import jdbc.demo2.dao.AccountDao;
import jdbc.demo2.dao.StockDao;
import jdbc.demo2.service.StockService;
import jdbc.demo2.service.StockServiceAppException;

public class StockServiceSpringImpl implements StockService
{

	private StockDao stockDao;
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	public void setStockDao(StockDao stockDao)
	{
		this.stockDao = stockDao;
	}

	@Override
	public void openAccount(String name, double balance)
	{
		Account account = new Account();
		account.setBalance(balance);
		account.setName(name);
		accountDao.save(account);
	}

	@Override
	public void openStock(String name)
	{
		Stock stock = new Stock();
		stock.setName(name);
		stockDao.save(stock);
	}

	@Override
	public void buyStock(String accountName, double amt, String stockName) throws StockServiceAppException
	{
		if (amt > 100)
		{
			new StockServiceAppException("amt > 1000");
		}
		Account account = accountDao.findByName(accountName);
		account.setBalance(account.getBalance() - amt);
		accountDao.modify(account);

		Stock stock = stockDao.findByName(stockName);
		stock.setQty(stock.getQty() + amt);
		stockDao.modify(stock);
	}

	@Override
	public double getBalance(String accountName)
	{
		Account account = accountDao.findByName(accountName);
		return account.getBalance();
	}

	@Override
	public double getQty(String stockName)
	{
		Stock stock = stockDao.findByName(stockName);
		return stock.getQty();
	}

}
