package class01.ioc1;

public class HelloBean
{
	private String greetingWord;

	private int number;

	public void setGreetingWord(String greetingWord)
	{
		this.greetingWord = greetingWord;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public HelloBean()
	{
		System.out.println("HelloBean default's constructor.");
	}

	public String sayHello()
	{
		String sayword = "";
		for (int i = 0; i < number; i++)
		{
			sayword += greetingWord + " ";
		}
		return sayword;
	}

}
