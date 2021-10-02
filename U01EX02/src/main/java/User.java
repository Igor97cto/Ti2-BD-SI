import java.time.LocalDate;

public class User
{
	private int id;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private char gender;
	private LocalDate ldt;

	public User(String name, String lastname, String email,
			String password, char gender, int day, int month, int year)
	{
		this.id = -1;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.ldt = LocalDate.of(year, month, day);
	}

	public User(int id, String name, String lastname, String email,
			String password, char gender, LocalDate ldt)
	{
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.ldt = ldt;
	}


	public int getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}


	public String getLastname()
	{
		return lastname;
	}


	public String getEmail()
	{
		return email;
	}


	public String getPassword()
	{
		return password;
	}


	public char getGender()
	{
		return gender;
	}


	public LocalDate getLdt()
	{
		return ldt;
	}
	
	
	public String getBirthday()
	{
		return ldt.getDayOfMonth() + "-" + ldt.getMonth() + "-" + ldt.getYear();
	}


	public void setId(int id)
	{
		this.id = id;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}


	public void setEmail(String email)
	{
		this.email = email;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	public void setGender(char gender)
	{
		this.gender = gender;
	}


	public void setLdt(LocalDate ldt)
	{
		this.ldt = ldt;
	}
	
	
	public void serBirthday(int day, int month, int year)
	{
		ldt = LocalDate.of(year, month, day);
	}
	
	
	@Override
	public String toString()
	{
		return "Id: " + getId() + "\n"
				+ "Nome: " + getName() + " " + getLastname() + "\n"
				+ "E-mail: " + getEmail() + "\n"
				+ "Senha: " + getPassword() + "\n"
				+ "Sexo: " + getGender() + "\n"
				+ "Data de nascimento: " + getBirthday() + "\n";
	}
}
