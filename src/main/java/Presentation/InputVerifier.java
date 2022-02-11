package Presentation;

import java.util.Scanner;

public class InputVerifier {

	public static int verifyInt(Scanner input)
	{
		int number = -1;
		if(input.hasNextInt())
			number = input.nextInt();
		input.nextLine();
		return number;
	}
	public static float verifyFloat(Scanner input)
	{
		float number = -1;
		if(input.hasNextFloat())
			number = input.nextFloat();
		input.nextLine();
		return number;
	}
}

