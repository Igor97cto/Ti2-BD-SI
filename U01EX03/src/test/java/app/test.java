package app;

import libs.SQLCV;
import model.User;

public class test
{
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException
	{
		User usr= new User("Igor", "de Castro e Carneiro", "igor97cto@outlook.com" , "dummy123", 'M', 17, 03, 1997);
		SQLCV sqlcv= new SQLCV(usr);
		//System.out.println(sqlcv);
		//System.out.println(sqlcv.createTable());
	}
}
