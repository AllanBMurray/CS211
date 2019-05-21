//Program led to the 6th best result in the class (out of 152 answers).
package CS211;

import java.util.*;

public class Lab10
{
	public static void main (String [] args)
	{
/*
22.67147680211382
(65,39,68,49,22,4,45,93,81,94,89,29,100,18,90,83,48,43,46,37,76,34,77,92,3,64,70,56,40,1,73,8,42,26,30,88,15,97,51,11,67,61,66,19,44,99,84,41,6,91,60,23,16,71,7,31,21,36,79,10,62,75,54,38,27,24,32,86,35,17,55,2,50,52,82,13,53,28,20,87,63,69,95,12,25,96,72,47,80,5,58,14,98,33,59,9,74,57,85,78)
*/
	//set the number of trials.
	int noOfTrials = 10000000;
		
	FileIO reader = new FileIO();
        Scanner scan = new Scanner(System.in);
        //text file with location data
        String[] inputs = reader.load("X:\\CS211eclipse\\CS211\\src\\CS211\\burritodata.txt");
        
        //create an array of house objects
        House[] houseArr = new House[101];
        //set the first House coordinates equal to the starting point, the restaurant.
        houseArr[0] = new House("0","restautant","12345667","53.38195","-6.59192");
        //input the House data from the data from the external file.
        for (int i = 1; i < inputs.length; i++)
        {
        	String [] tempArr = inputs[i].split(",");
        	houseArr[i] = new House(tempArr[0],tempArr[1],tempArr[2],tempArr[3],tempArr[4]);
        }
        
        double bestTotalDelay = 10000;
        
        //creates a matrix to hold all distances to be used as a look up table.
        double[][] disMat = new double[101][101];
        for (int m = 0; m < 101; m++)
        {
        	for (int n = 0; n < 101; n++)
        	{
        		disMat[m][n] = Haversine.distance(houseArr[m].north, houseArr[m].west, houseArr[n].north, houseArr[n].west);
        	}
        }
        
        //for loop for all the trials.
        for (int j = 0; j < noOfTrials; j++)
        {
        	//sets variables to be used for each trial
        	int burDel = 0;
        	int[] ansArray = new int[100];
        	int ansIter = 0;
            int nearestH = 0;
            int currentH = 0;
            double totalTravelled = 0.0;
            double curMin = 300;
            double curDis = 0;
            double curTime = 60;
            double totalDelay = 0;
            double curDisAndRand = 0;
            double curMinDAR = 300;
            //start a trial
        	while (burDel < 100)
	        {
		        for (int i = 1; i < 101; i++)
		        {
		        	if (houseArr[i].visited==false)
		        	{
		        		curDis = disMat[i][currentH];
		        		curDisAndRand = curDis + Math.random()/4.2; //this part adds randomness
		     
			        	if (curDisAndRand < curMinDAR)
			        	{
			        		curMinDAR = curDisAndRand;
			        		curMin = curDis; //the curDis holds the distance to be travelled.
			        		nearestH = i;
			        	}
		        	}
		        }
		        //updated number of burritos delivered, total travelled and the current time.
		        burDel++;
		        totalTravelled+=curMin;
		        curTime+=60*curMin/80;
		        if (curTime - houseArr[nearestH].time - 30 > 0)
		        {
		        	totalDelay+=curTime - houseArr[nearestH].time - 30;
		        }
		        currentH = nearestH;
		        
		        //record that the house has been visited
		        houseArr[nearestH].visited=true;
		        //add the house to the answer array and iterate the position marker by 1.
		        ansArray[ansIter] = nearestH;
		        ansIter+=1;
		      
		        
		        curMin = 300;
		        curMinDAR = 300;
		        
	        }
        	//code for when a new best value is found.
        	//The values are swapped and moved to try and optimise the solution.
        	if (totalDelay<bestTotalDelay)
        	{
        		bestTotalDelay = totalDelay;
        		System.out.println("Total travelled: "+totalTravelled);
        		System.out.println("Total delay: "+totalDelay/60);
        		printArray(ansArray);
        		
        		//swap around all values and check for improvements.
        		//after a bad result the swap is undone.
        		double tempTotalDelay;
        		double newBestTotalDelay = bestTotalDelay;
        	    for (int z = 0; z < 10; z++)
        	    {
        		    for (int i = 0; i < 99; i++)
        		    {
        		    	for (int k = i+1; k < 100; k++)
        		    	{
        		    		int temp = ansArray[i];
        		    		ansArray[i] = ansArray[k];
        		    		ansArray[k] = temp;
        		    		tempTotalDelay = BurritoUpgradePartial.answerCalc(ansArray, houseArr);
        		    	    if(tempTotalDelay < newBestTotalDelay)
        		    	    {
        		    	    	newBestTotalDelay = tempTotalDelay;
        		    	    	System.out.println(BurritoUpgradePartial.answerCalc(ansArray, houseArr));
        		    	    	printArray(ansArray);
        		    	    }
        		    	    else
        		    	    {
        		    	    	temp = ansArray[i];
        			    		ansArray[i] = ansArray[k];
        			    		ansArray[k] = temp;
        		    	    }
        		    		
        		    	}   				
        		    }
        	    }
        	    
        	    //the last part of the program reverses part of the route, 2-opt, and checks the results.
        	    //after a bad result the change is undone.
        	    for (int z = 0; z < 100; z++)
        	    {
        		    for (int i = 0; i < 90; i++)
        		    {
        		    	for (int p = 2; p < 10; p++)
        		    	{
        		    		int[] tempArr = new int[p];
        		    		for (int k = 0; k <= p/2; k++)
        		    		{
        		    			int temp = ansArray[i+k];
        		    			ansArray[i+k] = ansArray[i+p-k];
        		    			ansArray[i+p-k] = temp;
        		    		}
        		    		tempTotalDelay = BurritoUpgradePartial.answerCalc(ansArray, houseArr);
        		    	    if(tempTotalDelay < newBestTotalDelay)
        		    	    {
        		    	    	newBestTotalDelay = tempTotalDelay;
        		    	    	System.out.println(BurritoUpgradePartial.answerCalc(ansArray, houseArr));
        		    	    	printArray(ansArray);
        		    	    }
        		    	    else
        		    	    {
        		    	    	for (int k = 0; k <= p/2; k++)
        			    		{
        			    			int temp = ansArray[i+k];
        			    			ansArray[i+k] = ansArray[i+p-k];
        			    			ansArray[i+p-k] = temp;
        			    		}
        		    	    }	
        		    	}   				
        		    }
        	    }
        	}
	        for (House home : houseArr)
	        {
	        	home.visited = false;
	        }
        }
	}
	
	//prints array
	public static void printArray(int[] arr)
	{
		System.out.print("(");
		for(int a : arr)
		{
			System.out.print(a+",");
		}
		System.out.println(")");
	}
	
	//calculates total delay for a string of numbers.
	public static double answerCalc(int[] arr, House[] houseArr)
	{
		double totalDelay = 0.0;
		double curLat = 53.38195;
        double curLon = -6.59192;
        double curDis = 0.0;
        double curTime = 60;
        
		for (int i: arr)
		{
			curDis = Haversine.distance(curLat, curLon, houseArr[i].north, houseArr[i].west);
			curTime+=60*curDis/80;
			if (curTime - houseArr[i].time - 30 > 0)
	        {
	        	totalDelay+=curTime - houseArr[i].time - 30;
	        }
	        curLat = houseArr[i].north;
	        curLon = houseArr[i].west;
		}
		return totalDelay/60;
	}
}

//house class, location information and whether house has been visited.
class House
{
	int order;
	String address;
	int time;
	double north;
	double west;
	boolean visited;
	
	House(String o, String ad, String ti, String n, String w)
	{
		order = Integer.parseInt(o);
		address = ad;
		time = Integer.parseInt(ti.substring(2,4));
		north = Double.parseDouble(n);
		west = Double.parseDouble(w);
		visited = false;
	}
	House()
	{
		visited = true;
	}
}

class Haversine {
	//https://github.com/jasonwinn/haversine
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}

