package hibernate.demo1.dao;

import hibernate.demo1.bo.Account;

public interface AccountDao
{
	void save(Account a);

	Account findByName(String name);

	void modify(Account a);
}
