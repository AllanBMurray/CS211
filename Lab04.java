package CS211;

public class Lab04 
{
	public static void main (String [] args)
	{
		String inputWord = "bananaramababcdefghijklmnopqestruvwxyz";
		
		System.out.println(diffLetters(inputWord, 0));
	}
	
	public static int diffLetters(String s, int n)
	{
		if (n == s.length())
		{
			return 0;
		}
		
		for (int i = n+1; i < s.length(); i++)
		{
			if (s.charAt(i) == s.charAt(n))
			{
				return (diffLetters(s,n+1));
			}
		}
			return (1+diffLetters(s,n+1));
	}
}
