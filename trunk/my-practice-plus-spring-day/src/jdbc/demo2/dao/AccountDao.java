package jdbc.demo2.dao;

import jdbc.demo2.bo.Account;

public interface AccountDao
{
	void save(Account a);

	Account findByName(String name);

	void modify(Account a);
}
