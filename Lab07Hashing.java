package CS211;

import java.util.*;

//uses Hashing to add 90,000 words to a hash table with 99991 slots.
//outputs the total number of collisions when outputting all the words.
public class Lab07Hashing 
{   
	public static void main (String[] args)
	{
		FileIO reader = new FileIO();
        Scanner scan = new Scanner(System.in);
 
        String[] inputs = reader.load("X:\\CS211eclipse\\CS211\\src\\CS211\\dictionary.txt");
        String[] contents = new String[90000];
        int items = contents.length;
        for (int i = 0; i < items; i++)
        {
        	contents[i] = inputs[i];
        }
        
		int size=99991;
		Solution mysolution = new Solution();
		String[] hashtable=mysolution.fill(size, contents);
		HashTable mytable = new HashTable(hashtable);
		Solution   mysolution2   =   new   Solution();   //prevents cheating by using memory
		for(int i=0;i<items;i++)
		{
			int rand = (int)(Math.random()*items);
			String temp = contents[i];
			contents[i]=contents[rand];
			contents[rand]=temp;
		}
		int total=0;
		for(int i=0;i<items;i++)
		{
			int    slot    =    mysolution2.find(size,    mytable, contents[i]);
			if(!hashtable[slot].equals(contents[i]))
			{
				System.out.println("error!");
			}
		}
		System.out.println(mytable.gettotal());
	}
}

class HashTable
{
	private String[] hashTable;
	private int total=0;
	public HashTable(String[] input)
	{
		hashTable = input;
	}
	public boolean check(int slot, String check)
	{
		if(hashTable[slot].equals(check))
		{
			return true;
		}
		else
		{
			total++;
			return false;
		}
	}
	public int gettotal()
	{
		return total;
	}
}

class Solution
{
	public int find(int size, HashTable mytable, String word)
	{
	//fill this in so as to minimize collisions
	//takes in the HashTable object and the word to be found
	//the only thing you can do with the HashTable object is call "check"
	//this  method  should  return  the  slot  in  the  hashtable where the word is
		int runningValue = 123456;
		for(int j=0; j<word.length(); j++)
		{
			int num = word.charAt(j);
			runningValue*=num;
			runningValue = runningValue%size;
		}
		int n = 1;
		while (mytable.check(runningValue, word)==false)
		{
			//runningValue += 17;
			runningValue += (n*n);
			runningValue=runningValue%size;
			n+=1;
		}
		return runningValue;
	}
	
	public String[] fill(int size, String[] array)
	{
	//takes in the size of the hashtable, and the array of Strings to be placed in the hashtable
	//this should add all the words into the hashtable using some system
	//then it should return the hashtable array
		String[] hashtable = new String[size];
		for(int i=0;i<array.length;i++)
		{
			String word = array[i];
			int runningValue = 123456;
			for(int j=0; j<word.length(); j++)
			{
				int num = word.charAt(j);
				runningValue*=num;
				runningValue = runningValue%size;
			}
			int n = 1;
			while (hashtable[runningValue]!=null)
			{
				//runningValue+=17;
				runningValue+=(n*n);
				runningValue=runningValue%size;
				n+=1;
			}
			
			hashtable[runningValue]=word;
		}
		return hashtable;
	}  
}

