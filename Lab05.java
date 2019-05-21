//Stephen Ryan 18146112 - Allan Murray 18146082
//Hangman game with bonus ASCII art!
package CS211;
import java.util.*;

public class Lab05 {

public static void main(String args[]){
        
        FileIO reader = new FileIO();
        Scanner scan = new Scanner(System.in);
 
        String[] inputs = reader.load("X:\\CS211eclipse\\CS211\\src\\CS211\\dictionary.txt");    //Reading the File as a String array
        
        int choice = (int)(Math.random()*inputs.length);
		
        String newword = inputs[choice];
        
        int wordlen = newword.length();
        int lives = 6;
        
        String current ="";

        char[] array = new char[wordlen];
        
        for(int i= 0 ; i<wordlen-1 ; i++){
     	   
     	   array[i] = '_';
        }
        
       printA(array);
       System.out.println("");
       System.out.println("");
       //eSystem.out.println(inputs[choice]);
       do{
       Scanner read = new Scanner(System.in);
       System.out.println("Choose a letter");
       String letter = read.nextLine();
       boolean flag = false;
       for (int j = 0; j<wordlen; j++)
       {
    	   if (newword.charAt(j) == letter.charAt(0))
    	   {
    		   array[j] = letter.charAt(0);
    		   flag = true;
    	   }
       }
       if (flag == false)
       {
    	   lives-=1;
       }
       flag = false;
       printA(array);
       draw(lives);
       System.out.println("You have "+lives+" lives remaining!");
       if(lives == 0){
    	   System.out.println("GAME OVER - YOU HAVE LOST!");
       }
       }while(checkA(array)&&lives>0);
            
       
       System.out.println(inputs[choice]);
       
       if(lives>0){
       System.out.println("You've won!!");
       }
    }
	public static void printA(char[] array){
		for (int i =0 ; i < array.length; i++){
			System.out.print(array[i]+" ");
			
		}
		System.out.println();
	}
	
	public static boolean checkA(char[] array){
		for (int i =0 ; i < array.length; i++){
			if (array[i] == '_'){
				return true;
			}
		}
		return false;	
	}
	
	public static void draw (int lives){
		if(lives==6){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
		}
		if(lives==5){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
		}
		if(lives==4){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|     |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
		}
		if(lives==3){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|     |\\");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
		}
		if(lives==2){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|   / |\\");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|___");
		}
		if(lives==1){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|    /|\\");
			System.out.println("|     |");
			System.out.println("|    /  ");
			System.out.println("|___");
		}
		if(lives==0){
			System.out.println("______");
			System.out.println("|     |");
			System.out.println("|     O");
			System.out.println("|    /|\\");
			System.out.println("|     |");
			System.out.println("|    / \\");
			System.out.println("|___");
		}
	}
}