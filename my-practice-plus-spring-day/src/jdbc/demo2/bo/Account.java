package jdbc.demo2.bo;

import java.io.Serializable;

public class Account implements Serializable
{
	private static final long serialVersionUID = -6492790908066926542L;

	private long id;
	private String name;
	private double balance;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}
}
