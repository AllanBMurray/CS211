/*
 * Binary tree algorithm for Lab02 using my own code.
 * Encoding and decoding a word using Huffman algorithm.
 * The only objects used are "ANodes". All points in the binary tree created are ANodes.
 * 
 * When the letter frequency is equal, then the node tree which contains the lowest char (by ASCII value)
 * is placed to the left.
 */

package CS211;

//import java.util.Scanner;
import java.util.*;

public class Lab02NodeOnly 
{
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
		}
		ArrayList<ANode> letterList = new ArrayList<ANode>(); //create new ArrayList to hold sorted Anodes.
		//nodes are sorted by letter frequency (lowest first), and then by the lowest char (by ASCII code) in the node (or it's children).
		for (int i = 0; i < 128; i++) //for loop to step through freqList array and print out the results.
		{
			if (freqList[i]>0) //if the value at position i of freqList is greater than 1 then the char is printed (using variable i as this is the char's ascii number).
			{
				System.out.print("'"+(char)i + "':"+freqList[i]+";  "); //print statement
				System.out.println();
				ANode current = new ANode((char)i,freqList[i],(char)i); //create new node for each letter
				addANode(current, letterList); //add node to ArrayList in the correct position
			}
		}
		while (letterList.size() > 1) //convert list of nodes into binary tree by selectively joining the nodes
		{							  //to newly created nodes.
			ANode current = new ANode(); //create new node.
			current.leftChild = letterList.get(0); //point new node to first two nodes on the sorted ArrayList.
			current.rightChild = letterList.get(1);
			current.freq = letterList.get(0).freq + letterList.get(1).freq; //add the freq. from the child nodes
			if (current.leftChild.smallestLetter - current.rightChild.smallestLetter<0) { //put correct smallestLetter from the children into new node.
    			current.smallestLetter = current.leftChild.smallestLetter;
    		}
    		else {
    			current.smallestLetter = current.rightChild.smallestLetter;
    		}
			System.out.println(current.leftChild.smallestLetter+" & "+current.rightChild.smallestLetter+" = "+current.smallestLetter);
    		System.out.println("**********");
			letterList.remove(1); //delete nodes that have been added to the newly created node.
			letterList.remove(0); 
			addANode(current, letterList); //add the new created node to the ArrayList at the correct position.
		}
		
		outputBinaryTree(letterList.get(0),""); //print the entire binary table encoding list for perusal.
		
		for (int i = 0; i < testInput.length(); i++) //print out the original String input but now encoded.
		{
			printWordInBinary(letterList.get(0), testInput.charAt(i), ""); //final array is still in letterList.
		}																   //this is the root ANode.
	}
	
	public static void addANode(ANode n, ArrayList<ANode> arr) //this method adds a new node to the ArrayList.
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
					if (arr.get(count).smallestLetter>n.smallestLetter) //if frequencies are equal then compare smallestLetter values.
					{										 
						arr.add(count,n); //node is added.
						return;
					}
				}
				count++; //iterate count.
			}
		}
		arr.add(size,n); //loop has gone through the ArrayList without adding node, therefore add node at end.
	}
	
	//recursive program to print all letters in binary tree and associated binary Strings.
	public static void outputBinaryTree(ANode n, String s)
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
	
	//recursively travels through the binary tree and prints current binary String, s, when char c is found.
	public static void printWordInBinary(ANode n, char c, String s)
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
}

class ANode //class for nodes in binary tree
{
	ANode leftChild;
	ANode rightChild;
	char letter = (char)255; //char initialised to unused character.
	char smallestLetter = (char)255; //lowest char in node tree, initialised to unused character.
	int freq;
	
	ANode(char l, int f, char sl) //constructor for nodes with letters, i.e. leaves.
	{
		this.letter = l;
		this.freq = f;
		this.smallestLetter = sl;
	}
	
	ANode() //no-arg constructor for nodes that aren't leaves.
	{}
}

