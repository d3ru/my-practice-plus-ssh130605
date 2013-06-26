package jdbc.demo2.service;

public interface StockService
{

	void openAccount(String name, double balance);

	void openStock(String name);

	void buyStock(String accountName, double amt, String stockName) throws StockServiceAppException;

	double getBalance(String accountName);

	double getQty(String stockName);
}
