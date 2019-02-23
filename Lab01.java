package CS211;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.math.*;

/*
 * Public Key (130 characters [0-9A-F]):
04146AD91B050CB77A5164A3927165EFD987680C8F2A32E50883AB5609163D8D6EB1E0B509C4D362BFBDB0EA0D390885811C78405546A9BF635E17FDC917BC7F02
Public Key (compressed, 66 characters [0-9A-F]):
02146AD91B050CB77A5164A3927165EFD987680C8F2A32E50883AB5609163D8D6E
 *
 *Public Address: VwEYJvzkQmnMhnom18bNz4b6z5nHQ5t1VN
 *Public Address Compressed: VmXdCKMnJFp9pi6yyENe8AZiXr6UG7aAH6
 */
public class Lab01 
{
	public static void main (String [] args)
	{
		String secret = "80"; //declare string and put "80" at the front.
		String bitCharString = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"; //string for decimal to base58
		String hexCharString = "0123456789ABCDEF"; //string for decimal to hex
		String finalOutput = ""; //declare string to hold final answer. 5KZV4cAuqAwsbvUo4zR4vFAAAX2SQ4ws5YaHuVbqcyxcZvBHL962xc
		
		for (int i = 0; i < 64; i++) //for loop to add 64 random numbers to secret in hex.
		{
			Random rand = new Random();
			int num = rand.nextInt(16);
			secret = secret + hexCharString.charAt(num);
		}
		
		try
		{
			secret = secret + (sha256(sha256(secret))).substring(0,8); //put secret through sha256 hash twice and then add first 8 numbers of the result to the original string secret.
		}catch (NoSuchAlgorithmException e){}
		
		System.out.println(secret);
		System.out.println(secret.length());
		
		BigInteger bignumber = new BigInteger(1, hexStringToByteArray(secret)); //uses method to convert secret to byte array and then to BigInteger.
		//BigInteger bignumber = new BigInteger("654321");
		System.out.println(bignumber);

		BigInteger BIFE = new BigInteger("58"); //declare useful BigInteger constants.
		BigInteger BIZERO = new BigInteger("0");
		System.out.println(bignumber.compareTo(BIZERO));
		//while loop to convert bignumber to base58 final answer.
		//58 is repeatedly divided into the number. The mod result at each step is added to finalOutput in base58.
		while (bignumber.compareTo(BIZERO) > 0)
		{
			finalOutput = (bitCharString.charAt(bignumber.mod(BIFE).intValue())) + finalOutput;
			bignumber = bignumber.divide(BIFE);
		}
		System.out.println(finalOutput);
	}


	static String sha256(String input) throws NoSuchAlgorithmException 
	{
		byte[] in = hexStringToByteArray(input);
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(in);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) 
		{
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	public static byte[] hexStringToByteArray(String s) 
	{
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) 
		{
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
			+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
}