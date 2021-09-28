package u01ex01;

import java.util.Scanner;

class SunTwoNumbersMain
{
	public static int sum(int x, int y)
	{
		return x + y;
	}

	public static void main(String[] args)
	{
		int x;
		int y;
		
		Scanner scn= new Scanner(System.in);
		
		System.out.print("Insira o primeiro número: ");
		x= scn.nextInt();
		System.out.print("Insira o segundo número: ");
		y= scn.nextInt();
		
		System.out.println("Soma: " + sum(x, y));
	}

}
