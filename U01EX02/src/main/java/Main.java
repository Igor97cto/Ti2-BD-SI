import java.util.Scanner;

public class Main
{
	static Scanner scn;
	static DAO dao;
	static User usr0;
	
	public static User getUser() throws Exception
	{
		String name;
		String lastname;
		String email;
		String password;
		char gender;
		int day;
		int month;
		int year;
		
		System.out.println("Digite o nome: ");
		name= scn.nextLine();
		System.out.println("Digite o sobrenome: ");
		lastname= scn.nextLine();
		System.out.println("Digite o e-mail: ");
		email= scn.next();
		System.out.println("Digite a senha: ");
		password= scn.next();
		System.out.println("Digite o sexo (M ou F):");
		gender= scn.next().charAt(0);
		
		if(gender != 'M' || gender != 'F')
		{
			throw new Exception("Sexo precisa ser M ou F!");
		}
		
		System.out.println("Digite o dia do nascimento: ");
		day= scn.nextInt();
		System.out.println("Digite o mês do nascimento: ");
		month= scn.nextInt();
		System.out.println("Digite o ano do nascimento: ");
		year= scn.nextInt();
		
		return new User(name, lastname, email, password,
				gender, day, month, year);
	}
	
	public static boolean setOperation(int opt) throws Exception
	{	
		switch(opt)
		{
			case 1:
				System.out.println("=======INSERIR======n");
				return dao.insert(getUser());
			
			default:
				System.out.println("Opção inválida!");
				return true;
		}
	}
	
	
	
	
	public static void showMenu()
	{
		System.out.println(
				"========MENU========\n"
				+ "1- Inserir\n"
				+ "2- Atualizar\n"
				+ "3- Listarzn\n"
				+ "4- Excluir\n"
				);
	}
	

	public static void main(String[] args)
	{
		usr0= new User("Igor", "de Castro e Carneiro", "igor97cto@outlook.com",
				"dummy0", 'M', 17, 3, 1997);
		
		scn= new Scanner(System.in);
		dao= new DAO();
		
		dao.insert(usr0);
	}
}