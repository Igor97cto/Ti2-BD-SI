import java.time.LocalDate;

public class User
{
	private int id;
	private String name;
	private String lastname;
	private String email;
	private int password;
	private char gender;
	private LocalDate ldt;

	public User(String name, String lastname, String email,
			String password, char gender, int day, int month, int year)
	{
		this.id = -1;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password.hashCode();
		this.gender = gender;
		this.ldt = LocalDate.of(year, month, day);
	}

	public User(int id, String name, String lastname, String email,
			int password, char gender, LocalDate ldt)
	{
		this.id= id;
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


	public int getPassword()
	{
		return password;
	}


	public char getGender()
	{
		return gender;
	}
	
	
	public int getBirthDay()
	{
		return ldt.getDayOfMonth();
	}
	
	
	public int getBirthMonth()
	{
		return ldt.getMonth().getValue();
	}
	
	
	public int getBirthYear()
	{
		return ldt.getYear();
	}
	
	
	public String getBirthday()
	{
		return getBirthDay() + "-" + getBirthMonth() + "-" + getBirthYear();
	}
	
	
	public LocalDate getLdt()
	{
		return ldt;
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
		this.password = password.hashCode();
	}


	public void setGender(char gender)
	{
		this.gender = gender;
	}
	
	
	public void setBirthDay(int day)
	{
		ldt = LocalDate.of(getBirthYear(), getBirthMonth(), day);
	}
	
	
	public void setBirthMonth(int month)
	{
		ldt = LocalDate.of(getBirthYear(), month, getBirthDay());
	}
	
	
	public void setBirthYear(int year)
	{
		ldt = LocalDate.of(year, getBirthMonth(), getBirthDay());
	}
	
	
	public void setBirthday(int day, int month, int year)
	{
		ldt = LocalDate.of(year, month, day);
	}
	
	
	public void setLdt(LocalDate ldt)
	{
		this.ldt= ldt;
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
