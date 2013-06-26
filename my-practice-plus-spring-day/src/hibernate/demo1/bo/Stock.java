package hibernate.demo1.bo;

import java.io.Serializable;

public class Stock implements Serializable
{
	private static final long serialVersionUID = 868185639353767533L;

	private long id;
	private String name;
	private double qty;

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

	public double getQty()
	{
		return qty;
	}

	public void setQty(double qty)
	{
		this.qty = qty;
	}

}
