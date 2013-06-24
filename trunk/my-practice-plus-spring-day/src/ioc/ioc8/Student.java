package ioc.ioc8;

public class Student
{
	private String name;
	private String school;
	private String classes;

	public void setClasses(String classes)
	{
		this.classes = classes;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSchool(String school)
	{
		this.school = school;
	}

	@Override
	public String toString()
	{
		return "name : " + name + " school : " + school + " classes : " + classes;
	}
}
