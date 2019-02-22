package CS211Package;

import java.util.*;

public class Lab02 
{
	public static void main (String [] args)
	{
		System.out.println("Enter your sentence: ");
		Scanner sc = new Scanner(System.in);
		String testInput = sc.nextLine();
		int[] freqList = new int[128];
		int inputLen = testInput.length();
		int asciiCode;
		sc.close();
		
		for (int i = 0; i < inputLen; i++)
		{
			asciiCode = (int) testInput.charAt(i);
			freqList[asciiCode]++;
			System.out.print(sevenDigits(Integer.toBinaryString(asciiCode))+" ");
			if (i%7==6)
			{
				System.out.println();
			}
		}
		
		System.out.println("\n"+"\n"+"Letter Frequency");
		for (int i = 0; i < 128; i++)
		{
			if (freqList[i]>1)
			{
				System.out.println("'"+(char)i + "' appeared "+freqList[i]+" times");
			}
			else if(freqList[i]==1)
			{
				System.out.println("'"+(char)i + "' appeared "+freqList[i]+" time");
			}
		}	
	}
	
	public static String sevenDigits(String a)
	{
		if (a.length() == 7)
		{
			return a;
		}
		else
		{
			return sevenDigits("0"+a);
		}
	}
}
