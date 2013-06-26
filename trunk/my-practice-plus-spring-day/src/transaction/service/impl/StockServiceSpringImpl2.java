package transaction.service.impl;

import hibernate.demo1.bo.Account;
import hibernate.demo1.bo.Stock;
import hibernate.demo1.dao.AccountDao;
import hibernate.demo1.dao.StockDao;
import hibernate.demo1.service.StockService;
import hibernate.demo1.service.StockServiceAppException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StockServiceSpringImpl2 implements StockService
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

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void openAccount(String name, double balance)
	{
		Account account = new Account();
		account.setBalance(balance);
		account.setName(name);
		accountDao.save(account);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void openStock(String name)
	{
		Stock stock = new Stock();
		stock.setName(name);
		stockDao.save(stock);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = StockServiceAppException.class)
	@Override
	public void buyStock(String accountName, double amt, String stockName) throws StockServiceAppException
	{
		Account account = accountDao.findByName(accountName);
		account.setBalance(account.getBalance() - amt);
		accountDao.modify(account);

		if (amt > 1000)
		{
			throw new StockServiceAppException("amt > 1000");
		}

		Stock stock = stockDao.findByName(stockName);
		stock.setQty(stock.getQty() + amt);
		stockDao.modify(stock);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public double getBalance(String accountName)
	{
		Account account = accountDao.findByName(accountName);
		return account.getBalance();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public double getQty(String stockName)
	{
		Stock stock = stockDao.findByName(stockName);
		return stock.getQty();
	}

}
