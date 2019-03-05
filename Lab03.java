package CS211;

import java.util.Scanner;

public class Lab03 {
	
	public static void main (String [] args) 
	{
		//test String[] initialised called theArray.
		String[] theArray = {"abt","massa","rowland","digrassi","0","t","q","y","z","d","e","f","p","aat","bat","atb","bbb","test","tsetse","typ","hello!","goodbye","wtf","[][][]","kgjh","kjre"};
		int listSize = theArray.length;
		/*
		Scanner sc = new Scanner(System.in); //create scanner object (for Hackerrank).
		int listSize = sc.nextInt(); //scan in list size
		sc.nextLine(); //move scanner to the next line
		String[] theArray = new String[listSize]; //create array to hold Strings to be sorted.
		for (int i = 0; i < listSize; i++)
		{
			String testInput = sc.nextLine(); //put Strings into the array with Scanner.
		}
		sc.close(); //Close Scanner.
		*/
		recQuickSort(0, listSize-1, theArray); //start sorting.
		printWords(theArray); //print the sorted words. 
		printArray(theArray); //print the sorted array.
	}

	public static boolean compareStrings(String a, String b)
	{ //compares two strings, first by greatest contained letter (by ASCII code) and then alphabetically.
	  //returns true if a comes before b in list.
		char greatestA = (char)0; //variable to hold greatest char in a. Set at minimum value.
		for (int i = 0; i < a.length(); i++)
		{
			if ((int)a.charAt(i)>greatestA)
			{
				greatestA = a.charAt(i);
			}
		}
		boolean flag = false; //flag to record if a char in b is equal to greatest char in a.
		for (int i = 0; i < b.length(); i++) //compare each char in b to greatest char in a.
		{
			if ((int)b.charAt(i)>greatestA)
			{ //if b has a char with a greater value than the greatest in a, return false.
				return false;
			}
			else if ((int)b.charAt(i)==greatestA) //set flag to true.
			{
				flag = true;
			}
		}
		if (!flag) //all chars in b less than greatest in a, return true.
		{
			return true;
		}
		else //flag is true, so the greatest char in a and b is the same.
		{
			if(a.compareTo(b)<0) //compare Strings alphabetically and return value as appropriate.
			{
				return false;
			}
		}
		return true;
	}
	
	public static void swap(int a, int b, String[] arr)
	{ //simple method to swap Strings in main array at positions a and b.
		String tempString = arr[b];
		arr[b] = arr[a];
		arr[a] = tempString; 
		
	}
	
	public static int partitionIt(int left, int right, String pivot, String[] arr)
	{ //method that carries out sorting.
		 int leftPtr = left-1; // left (after ++)
		 int rightPtr = right; // right-1 (after --)
		 while(true) {
			 while(!compareStrings(arr[++leftPtr],pivot)){} // scan to the right
			 while(rightPtr > 0 && compareStrings(arr[--rightPtr],pivot)){} // scan to the left
			 //System.out.println(leftPtr+" p "+rightPtr);
			 if(leftPtr >= rightPtr) // if pointers cross,
				 break; // partition done
			 else // not crossed, so swap elements. Elements continue swapping until pointers cross.
				 swap(leftPtr, rightPtr, arr);
		 	}
		 	swap(leftPtr, right-1, arr); // swap pivot into correct place. Using medianOf3 this is at right-1.
		 	return leftPtr; // return pivot location
		}

	public static String medianOf3(int left, int right, String[] arr) 
	{ //method takes first, last and median elements. Elements sorted with the correct elements put at
	  //first and last position. The median element is the pivot and goes at the last-1 position.
		int center = (left+right)/2; 
		if(compareStrings(arr[left],arr[center])) // order left & center
		{
			swap(left, center, arr); 
		}	
		if(compareStrings(arr[left],arr[right])) // order left & right
		{
			swap(left, right, arr);
		}	
		if(compareStrings(arr[center],arr[right])) // order center & right
		{
			swap(center, right, arr);
		}
		swap(center, right-1,arr); // put pivot at right-1, largest of the 3 values is in position right.
		//printArray(arr);
		//System.out.println();
		return arr[right-1]; // return median value
	}
	
	public static void recQuickSort(int left, int right, String[] arr) 
	{ //method to sort array by recursively calling revQuickSort. Small chunks (<10) are sorted by insertionSort.
		//printArray(arr);
		//System.out.println(left +"--"+right);
		int size = right-left+1;
		if(size < 10) // insertion sort if small
			insertionSort(left, right, arr);
		else{ // quicksort if large
			String median = medianOf3(left, right, arr);
			//System.out.println(median);
			int partition = partitionIt(left, right, median, arr);
			recQuickSort(left, partition-1, arr);
			recQuickSort(partition+1, right, arr);
		}
	}
	
	public static void insertionSort(int left, int right, String[] arr)
	{ //method to sort parts of the list with less than 10 values by insertion sort.
		for (int outer = left; outer < right+1; outer++) 
		{
			// outer is the next element to be sorted
			String temp = arr[outer]; //back it up
			int inner = outer; // inner used to track shifts
			while (inner > 0 && compareStrings(arr[inner - 1],temp)) 
			{
				arr[inner] = arr[inner - 1];// swap
				inner--;
			} //shift them all right until one is smaller
		arr[inner] = temp;
		}
	}
	
	public static void printArray(String[] arr)
	{ //method to print out array.
		System.out.print("{");
		for (int i = 0; i < arr.length-1; i++)
		{
			System.out.print(arr[i]+",");
		}
		System.out.print(arr[arr.length-1]+"}");
	}
	
	public static void printWords(String[] arr)
	{ //method to print out array one word at a time.
		for (int i = 0; i < arr.length-1; i++)
		{
			System.out.println(arr[i]);
		}
	}
}
