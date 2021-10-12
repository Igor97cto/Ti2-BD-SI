import java.util.Scanner;

public class Main
{
	static Scanner scn;
	static DAO dao;
	static User usr0;
	
	public static boolean delete()
	{
		User usr= null;
		boolean status= false;	
		
		System.out.println("Insira a id a ser deletada: ");
		usr= dao.read(scn.nextInt());
		
		if(usr!= null)
		{
			System.out.println("====DELETADO====");
			System.out.println(usr);
			status= dao.delete(usr.getId());
		}
		
		return status;
	}
	
	public static User getUserInfo() throws Exception
	{
		String name;
		String lastname;
		String email;
		String password;
		char gender;
		int day;
		int month;
		int year;
		
		scn.nextLine();
		System.out.print("Digite o nome: ");
		name= scn.nextLine();
		System.out.print("Digite o sobrenome: ");
		lastname= scn.nextLine();
		System.out.print("Digite o e-mail: ");
		email= scn.next();
		System.out.print("Digite a senha: ");
		password= scn.next();
		System.out.print("Digite o sexo (M ou F):");
		gender= scn.next().charAt(0);
		
		if(gender != 'M' && gender != 'F')
		{
			throw new Exception("Sexo precisa ser M ou F!");
		}
		
		System.out.print("Digite o dia do nascimento: ");
		day= scn.nextInt();
		System.out.print("Digite o mês do nascimento: ");
		month= scn.nextInt();
		System.out.print("Digite o ano do nascimento: ");
		year= scn.nextInt();
		
		return new User(name, lastname, email, password,
				gender, day, month, year);
	}
	
	
	public static boolean listUsers() throws Exception
	{
		boolean status= false;
		User[] usr=  dao.list();
		
		if(usr != null)
		{
			for(User aux : usr)
			{
				System.out.println(aux);
			}
			
			status= true;
		}
		
		return status;
	}
	
	
	public static boolean setOperation(int opt) throws Exception
	{	
		switch(opt)
		{
			case 1:
				System.out.println("======INSERIR=====\n");
				return dao.insert(getUserInfo());
			
			case 2:

				System.out.println("=====ATUALIZAR====\n");
				return dao.update(updateUser());
				
			case 3:
				System.out.println("======LISTAR======\n");
				return listUsers();
				
			case 4:
				System.out.println("======EXCLUIR=====\n");
				return delete();
				
			case 5:
				System.out.println("=======SAIR=======\n");
				return false;
				
			default:
				scn.nextLine();
				System.out.println("Opção inválida!");
				return true;
		}
	}
		
	
	public static int showMenu()
	{
		System.out.print(
				"\n========MENU========\n"
				+ "1- Inserir\n"
				+ "2- Atualizar\n"
				+ "3- Listar\n"
				+ "4- Excluir\n"
				+ "5- Sair\n"
				+ "> "
				);
		
		return scn.nextInt();
	}
	
	
	public static int showUpdateMenu()
	{
		System.out.print("\nO que atualizar?\n"
							+ "1- Nome\n"
							+ "2- Sobrenome\n"
							+ "3- E-mail\n"
							+ "4- Senha\n"
							+ "5- Sexo\n"
							+ "6- Dia de nascimento\n"
							+ "7- Mês de nascimento\n"
							+ "8- Ano de nascimento\n"
							+ "> ");
		
		return scn.nextInt();
	}
	
	
	public static User updateUser() throws Exception
	{
		User usr= null;
		int opt= 0;
		char gender;
		
		System.out.print("Insira a ID do usuário: ");
		usr= dao.read(scn.nextInt());
		
		if(usr != null)
		{
			opt= showUpdateMenu();
			
			switch(opt)
			{
				case 1:
					scn.nextLine();
					System.out.print("Insira o novo nome: ");
					usr.setName(scn.next());
					break;
					
				case 2:
					scn.nextLine();
					System.out.print("Insira o novo Sobrenome: ");
					usr.setLastname(scn.nextLine());
					break;
					
				case 3:
					scn.nextLine();
					System.out.print("Insira o novo e-mail: ");
					usr.setEmail(scn.next());
					break;
					
				case 4:
					scn.nextLine();
					System.out.print("Insira a nova senha: ");
					usr.setPassword(scn.nextLine());
					break;
					
				case 5:				
					scn.nextLine();
					System.out.print("Insira o novo sexo: ");
					gender= scn.next().charAt(0);
					
					if(gender != 'M' || gender != 'F')
					{
						throw new Exception("Sexo precisa ser M ou F!");
					}
					
					usr.setGender(gender);
					break;
					
				case 6:
					scn.nextLine();
					System.out.print("Insira o dia do nascimento: ");
					usr.setBirthDay(scn.nextInt());
					break;
					
				case 7:
					scn.nextLine();
					System.out.print("Insira o mês do nascimento: ");
					usr.setBirthMonth(scn.nextInt());
					break;
					
				case 8:
					scn.nextLine();
					System.out.print("Insira o ano do nascimento: ");
					usr.setBirthYear(scn.nextInt());
					break;
			}
		}
		
		return usr;
	}
	

	public static void main(String[] args) throws Exception
	{	
		boolean test= false;
		dao= new DAO();
		scn= new Scanner(System.in);
		
		if(test)
		{
			if(dao.drop())
			System.out.println("BD DELETADO!");
		}
		
		while(setOperation(showMenu()));
		
		dao.close();
		
		//listUsers();
	}
}