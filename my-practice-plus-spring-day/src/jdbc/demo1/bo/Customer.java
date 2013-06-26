package jdbc.demo1.bo;

import java.io.Serializable;

public class Customer implements Serializable
{
	private static final long serialVersionUID = 3144066828935394175L;

	private Long id;
	private String name;
	private int age;

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public int getAge()
	{
		return age;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "name : " + name + " age : " + age + " id : " + id + " .";
	}
}
