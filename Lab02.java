package CS211;

import java.util.*;

public class Lab02 {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String sentence = in .nextLine();
        in.close();

        int[] array = new int[256]; //an array to store all the frequencies

        for (int i = 0; i < sentence.length(); i++) { //go through the sentence
            array[(int) sentence.charAt(i)]++; //increment the appropriate frequencies
        }

        PriorityQueue < Tree > PQ = new PriorityQueue < Tree > (); //make a priority queue to hold the forest of trees    

        for (int i = 0; i < array.length; i++) { //go through frequency array
        	if (array[i] > 0) { //print out non-zero frequencies - cast to a char
    			Tree currentTree = new Tree(array[i]);	//create new tree, using frequency in the constructor
            	Node currentNode = new Node((char)i,(char)i); //create new Node, using the letter in the constructor
            	currentTree.root = currentNode; //connect the Node to the Tree
            	PQ.add(currentTree); //add the new Tree to the PQ, in the correct position.
            }
        }
        int combinedFreq;
        while (PQ.size() > 1) { //while there are two or more Trees left in the forest
    		Node current = new Node(); //create new node.
    		Tree currentTree = PQ.remove(); //remove first tree from PQ.
    		current.leftChild = currentTree.root; //point the new Node to the Node of Tree removed from PQ.
    		combinedFreq = currentTree.frequency; //record frequency from the current Tree.
    		currentTree = PQ.remove(); //remove second tree from PQ.
    		current.rightChild = currentTree.root; //point new Node to the the Node of Tree removed from PQ.
    		System.out.println(combinedFreq+" & "+currentTree.frequency); //print freq values of both Nodes.
    		combinedFreq += currentTree.frequency; //record the sum of both frequencies.
    		//if statement to determine the correct letter for the "smallestLetter" of the new tree.
    		if (current.leftChild.smallestLetter - current.rightChild.smallestLetter<0) {
    			current.smallestLetter = current.leftChild.smallestLetter;
    		}
    		else {
    			current.smallestLetter = current.rightChild.smallestLetter;
    		}
    		System.out.println(current.leftChild.smallestLetter+" & "+current.rightChild.smallestLetter+" = "+current.smallestLetter);
    		System.out.println("**********");
    		Tree newTree = new Tree(combinedFreq); //create new tree to add to PQ.
    		newTree.root = current; //point root of new tree to current node.
    		PQ.add(newTree); //add new tree to PQ at the correct position.
        }
        
        Tree HuffmanTree = PQ.remove(); //set Tree in PQ to HuffmanTree.

        for (int i = 0; i < sentence.length();i++)
        {
        	System.out.print(HuffmanTree.getCode(sentence.charAt(i))); //Print out binary code using Tree and "sentence".
        }
    }
}

class Node {
    public char letter = (char)255; //stores letter
    public char smallestLetter = (char)255;  //a nice idea it to track the smallest letter in the tree in a special variable like this
				
    public Node leftChild; // this node's left child
    public Node rightChild; // this node's right child
    
    Node (char l, char sl) //constructor added for calling to add letters.
    {
    	this.letter = l;
    	this.smallestLetter = sl;
    }
    
    Node(){ //no-arg constructor for nodes above the leaves.
    	
    }

} // end class Node





class Tree implements Comparable < Tree > {
    public Node root; // first node of tree
    public int frequency = 0;

    public Tree(int f) // constructor, initialized with frequency value.
    {
        root = null;
        this.frequency = f;
    } // no nodes in tree yet

    //the PriorityQueue needs to be able to somehow rank the objects in it
    //thus, the objects in the PriorityQueue must implement an interface called Comparable
    //the interface requires you to write a compareTo() method so here it is:

    public int compareTo(Tree object) {
        if (frequency - object.frequency > 0) { //compare the cumulative frequencies of the tree
            return 1;
        } else if (frequency - object.frequency < 0) {
            return -1; //return 1 or -1 depending on whether these frequencies are bigger or smaller
        } else {
            // Sort based on letters
            char a = this.root.smallestLetter;
            char b = object.root.smallestLetter;

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
            return 0;
        }
    }

    String path = "error"; //this variable will track the path to the letter we're looking for 

    public String getCode(char letter) { //we want the code for this letter

        return this._getCode(letter, this.root, ""); //return the path that results
    }

    private String _getCode(char letter, Node current, String path) {
        if (current == null) {
            return null;
        }
        if (current.letter == letter) {
            return path;
        }

        String leftPath = this._getCode(letter, current.leftChild, path + "0");
        if (leftPath != null) {
            return leftPath;
        }

        String rightPath = this._getCode(letter, current.rightChild, path + "1");
        return rightPath;
    }

} // end class Tree
