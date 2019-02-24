package CS211;

import java.util.*;

<<<<<<< HEAD
public class Lab01 
=======
public class Lab02 
>>>>>>> c9419443578de06625bd20d809ec8ecfa3a91a04
{
	public static void main (String [] args)
	{
		System.out.println("Enter your sentence: ");
		Scanner sc = new Scanner(System.in); //create scanner object
		String testInput = sc.nextLine(); //set user input to variable testInput.
		int[] freqList = new int[128]; //declare a int array with a length of 128.
		int inputLen = testInput.length(); //set inputLen to length of string input by user.
		int asciiCode; //declare variable to hold asciiCode value in for loop below.
		sc.close();
		
		for (int i = 0; i < inputLen; i++) //for loop to step through the characters in testInput one at a time.
		{
			asciiCode = (int) testInput.charAt(i); //set asciiCode to the ASCII code of the char in testInput at position i.
			freqList[asciiCode]++; //increment the freqList at the position associated with the value currently in asciiCode.
			System.out.print(sevenDigits(Integer.toBinaryString(asciiCode))+" "); //print out the binary value (uses sevenDigits method).
			if (i%7==6) //this causes the binary numbers to be printed with 7 values per line.
			{
				System.out.println();
			}
		}
		
		System.out.println("\n"+"\n"+"Letter Frequency");
		for (int i = 0; i < 128; i++) //for loop to step through freqList array and print out the results.
		{
			if (freqList[i]>1) //if the value at position i of freqList is great than 1 then the char is printed (using i as this is the char's ascii number).
			{
				System.out.println("'"+(char)i + "' appeared "+freqList[i]+" times");
			}
			else if(freqList[i]==1) //"times" is changed to "time" when the letter appears exactly once.
			{
				System.out.println("'"+(char)i + "' appeared "+freqList[i]+" time");
			}
		}	
	}
	
	public static String sevenDigits(String a) //recursive algorithm used to add leading zeros to binary values with less then 7 digits.
	{										   //binary values taken in and returned as Strings.
		if (a.length() == 7) //exit condition
		{
			return a;
		}
		else //recursion
		{
			return sevenDigits("0"+a);
		}
	}
}
