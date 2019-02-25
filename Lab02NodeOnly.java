/*
 * Binary tree algorithm for Lab02 using my own code.
 * The only objects used are "Nodes". All points in the binary tree created are Nodes.
 */

package CS211;

//import java.util.Scanner;
import java.util.*;

public class Lab02NodeOnly 

{
	public static char globalVar1 = (char)255;
	public static char globalVar2 = (char)255;
	
	public static void main(String [] args)
	{
		/*
		System.out.println("Enter your sentence: ");
		Scanner sc = new Scanner(System.in); //create scanner object
		String testInput = sc.nextLine(); //set user input to variable testInput.
		sc.close();
		*/
		String testInput = "never say never"; // test input when the Scanner isn't being used.
		int[] freqList = new int[128]; //declare a int array with a length of 128.
		int inputLen = testInput.length(); //set inputLen to length of string input by user.
		int asciiCode; //declare variable to hold asciiCode value in for loop below.
		
		
		for (int i = 0; i < inputLen; i++) //for loop to step through the characters in testInput one at a time.
		{
			asciiCode = (int) testInput.charAt(i); //set asciiCode to the ASCII code of the char in testInput at position i.
			freqList[asciiCode]++; //increment the freqList at the position associated with the value currently in asciiCode.
			//System.out.print(sevenDigits(Integer.toBinaryString(asciiCode))+" "); //print out the binary value (uses sevenDigits method).
		}
		
		ArrayList<Node> letterList = new ArrayList<Node>();
	
		for (int i = 0; i < 128; i++) //for loop to step through freqList array and print out the results.
		{
			if (freqList[i]>0) //if the value at position i of freqList is great than 1 then the char is printed (using i as this is the char's ascii number).
			{
				//System.out.println("'"+(char)i + "' appeared "+freqList[i]+" times"); //print statement
				Node current = new Node((char)i,freqList[i]);
				addNode(current, letterList);
			}
		}
		//printNodeFreq(letterList);
		
		while (letterList.size() > 1) //convert list of nodes into binary tree by selectively joining the nodes
		{							  //to newly created nodes.
			Node current = new Node(); //create new node.
			current.leftChild = letterList.get(0); //point new node to first two nodes on the sorted ArrayList.
			current.rightChild = letterList.get(1);
			current.freq = letterList.get(0).freq + letterList.get(1).freq; //add the freq. from the child nodes
			letterList.remove(1);											//to the new node.
			letterList.remove(0); //delete nodes that have been added.
			addNode(current, letterList); //add the new created node to the ArrayList at the correct position.
		}
		
		outputBinaryTree(letterList.get(0),""); //print the entire binary table for perusal.
		
		for (int i = 0; i < testInput.length(); i++) //print out the original String input but now encoded.
		{
			printWordInBinary(letterList.get(0), testInput.charAt(i), ""); //final array is still in letterList.
		}																   //this is the root Node.
	}
	
	public static void addNode(Node n, ArrayList<Node> arr) //this method adds a new node to the ArrayList.
	{
		int count = 0;
		int size = arr.size();
		if (size == 0) //if the ArrayList is empty then simply add node at position 0.
		{
			arr.add(n);
			return; //return to main method
		}
		else
		{
		while (count<size) //while statement to step through the ArrayList and add new node in correct position.
			{
				if (arr.get(count).freq > n.freq) //check if .freq of node to be added is less than .freq
				{							      //of the current node in the ArrayList.
					arr.add(count,n); //if it is, add node before the current node the ArrayList.
					return;
				}
				else if (arr.get(count).freq == n.freq) //check if both nodes have the same .freq value.
				{
					if (compareNodeTrees(arr.get(count), n)) //if frequencies are equal then use compareNodeTrees
					{										 //method to see if node should be added at this point.
						arr.add(count,n); //node is added.
						return;
					}
				}
				count++; //iterate count.
			}
		}
		arr.add(size,n); //loop has gone through the ArrayList without adding node, therefore add node at end.
	}
	
	public static void printNodeFreq(ArrayList<Node> arr) //method to print ArrayList at early stage.
	{
		int size = arr.size();
		System.out.println("size of the Node array: "+size);
		for (int i = 0; i < size; i++)
		{
			System.out.println(arr.get(i).freq + "___" + arr.get(i).letter +" **** "+i);
		}
	}
	
	//recursive program to print all letters in binary tree and associated binary Strings.
	public static void outputBinaryTree(Node n, String s)
	{
		if (n.leftChild != null)
		{
			outputBinaryTree (n.leftChild, s+"0");
		}
		if (n.rightChild !=null)
		{
			outputBinaryTree (n.rightChild, s+"1");
		}
		if (n.letter != (char)255)
		{
			System.out.println(n.letter+" = "+s);
			return;
		}
	}
	
	//recursively travels through the binary tree and prints current binary String, s, when letter is found.
	public static void printWordInBinary(Node n, char c, String s)
	{
		if (n.leftChild != null)
		{
			printWordInBinary(n.leftChild, c, s+"0");
		}
		if (n.rightChild !=null)
		{
			printWordInBinary(n.rightChild, c, s+"1");
		}
		if (n.letter == c )
		{
			System.out.print(s);
		}
	}
	
	//recursively travels through Nodes to find the .letter with the lowest ASCII code.
	//This method calls two recursive methods, one for each node being compared.
	//This information is used, ultimately, to decide if an a Node is to be added to the list.
	//global variables are used and reset before every use. Another strategy is required to use return statements.
	public static boolean compareNodeTrees(Node arrayn, Node n)
	{
		globalVar1 = (char)255;
		globalVar2 = (char)255;
		lowestChar(arrayn);
		lowestChar2(n);
		if (globalVar1>globalVar2)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	//recursive method to find char with lowest ASCII code in Node.
	public static void lowestChar(Node n)
	{
		if (n.leftChild != null)
		{
			lowestChar(n.leftChild);
		}
		if (n.rightChild !=null)
		{
			lowestChar(n.rightChild);
		}
		if (globalVar1 - n.letter > 0 )
		{
			globalVar1 = n.letter;
		}
		
	}
	//recursive method to find char with lowest ASCII code in Node.
	public static void lowestChar2(Node n)
	{
		if (n.leftChild != null)
		{
			lowestChar2(n.leftChild);
		}
		if (n.rightChild !=null)
		{
			lowestChar2(n.rightChild);
		}
		if (globalVar2 - n.letter > 0 )
		{
			globalVar2 = n.letter;
		}
		
	}
	
}

class Node //class for nodes in binary tree
{
	Node leftChild;
	Node rightChild;
	char letter = (char)255; //char initialised to unused character.
	int freq;
	
	Node(char l, int f) //constructor for nodes with letters, i.e. leaves.
	{
		this.letter = l;
		this.freq = f;
	}
	
	Node() //default constructor for nodes that aren't leaves.
	{
		
	}
}
