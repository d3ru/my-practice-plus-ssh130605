package cn.javass.point.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_goods")
public class GoodsModel implements Serializable
{

	private static final long serialVersionUID = 3336093245006627270L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private int id;

	@Column(name = "id", length = 100, nullable = false)
	private String name;

	@Column(name = "description", length = 100, nullable = false)
	private String description;

	@Column(name = "original_point", length = 100, nullable = false)
	private int originalPoint;

	@Column(name = "now_point", length = 100, nullable = false)
	private int nowPoint;

	@Column(name = "published", nullable = false)
	private boolean published;

	@Column(name = "isdelete", nullable = false)
	private boolean isDelete;

	@Version
	@Column(name = "version", nullable = false, length = 10)
	private int version;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getOriginalPoint()
	{
		return originalPoint;
	}

	public void setOriginalPoint(int originalPoint)
	{
		this.originalPoint = originalPoint;
	}

	public int getNowPoint()
	{
		return nowPoint;
	}

	public void setNowPoint(int nowPoint)
	{
		this.nowPoint = nowPoint;
	}

	public boolean isPublished()
	{
		return published;
	}

	public void setPublished(boolean published)
	{
		this.published = published;
	}

	public boolean isDelete()
	{
		return isDelete;
	}

	public void setDelete(boolean isDelete)
	{
		this.isDelete = isDelete;
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
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
		GoodsModel other = (GoodsModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
