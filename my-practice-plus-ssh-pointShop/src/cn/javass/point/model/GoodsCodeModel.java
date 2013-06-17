package cn.javass.point.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "goods_code_tb")
public class GoodsCodeModel implements Serializable
{

	private static final long serialVersionUID = 3778848630248704574L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private int id;

	@ManyToOne
	private GoodsModel goods;

	@Column(name = "code", length = 100, nullable = false)
	private String code;

	@Column(name = "username", length = 100, nullable = false)
	private String username;

	@Column(name = "exchange_time")
	private Date exchangeTime;

	@Column(name = "exchanged")
	private boolean exchanged;

	@Version
	@Column(name = "version", length = 10, nullable = false)
	private int version;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public GoodsModel getGoods()
	{
		return goods;
	}

	public void setGoods(GoodsModel goods)
	{
		this.goods = goods;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Date getExchangeTime()
	{
		return exchangeTime;
	}

	public void setExchangeTime(Date exchangeTime)
	{
		this.exchangeTime = exchangeTime;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public boolean isExchanged()
	{
		return exchanged;
	}

	public void setExchanged(boolean exchanged)
	{
		this.exchanged = exchanged;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsCodeModel other = (GoodsCodeModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
