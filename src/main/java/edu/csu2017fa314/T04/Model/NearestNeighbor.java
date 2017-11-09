package edu.csu2017fa314.T04.Model;

import edu.csu2017fa314.T04.View.distanceObject;
import edu.csu2017fa314.T04.Server.*;
import java.util.ArrayList;
import java.util.Arrays;

public class NearestNeighbor {
	  
	private ArrayList<Destination> locations;
	private int disTable[][];
	private int visTable[][];
	private int bestTrip[];
	private int currentTrip[];
	private int curTripPtr;
	private int minTotalDistM;
    	private int curTotalDistM;
    	private int minTotalDistK;
    	private int curTotalDistK;
    	public static String [] nnUnits = {"miles"};
    	public String kilometers = "kilometers";
    	public String miles = "miles";

	public NearestNeighbor(ArrayList<Destination> locations) {
		this.locations = locations;
		disTable = new int[locations.size()][locations.size()];
		visTable = new int[locations.size()][locations.size()];
		currentTrip = new int[disTable.length];
		curTripPtr = 0;
       		minTotalDistM = Integer.MAX_VALUE;
        	curTotalDistM = 0;
        	minTotalDistK = Integer.MAX_VALUE;
        	curTotalDistK = 0;
        	setNnUnits();
		fillInMap();
	}
	
   	public static void setNnUnits() {
        	Server serveNn = new Server();
        	if (serveNn.distUnits == null){
            		String [] nnStart = {"miles"};
            		nnUnits = nnStart;
        	}
        	else{
            		nnUnits = serveNn.distUnits;
        	}
     	}
	
    	public int getTotalDistanceM() {
        	return minTotalDistM;
    	}
    
    	public int getTotalDistanceK() {
        	return minTotalDistK;
    	}

	private void fillInMap() {
		for (int i = 0; i < locations.size(); i++) {
			for (int j = 0; j < locations.size(); j++) {
				if (i == j) {
					disTable[i][j] = Integer.MAX_VALUE;
				} else {
                    			if (nnUnits[0].equals(miles)){
                        			disTable[i][j] = 
							locations.get(i).computeDistanceM(locations.get(j));
					}
					else{
                        			disTable[i][j] = 
							locations.get(i).computeDistanceK(locations.get(j));
					}
				}
			}
		}
	}

	public ArrayList<distanceObject> getNearestNeighborTrip() {
		bestTrip = calcShortestTrip();
		twoOpt(bestTrip);
		ArrayList<Destination> orderedDestinations = new ArrayList<>(locations.size());
		for(int i = 0; i < locations.size(); i++){
			orderedDestinations.add(locations.get(bestTrip[i]));
		}
		return disObjectify(orderedDestinations);
	}

	private int[] twoOptSwap(int[] trip, int i1, int k){
		/*
		Implemented from sprint 3 slides
		 */
		int temp;
		while(i1 < k){
			temp = trip[i1];
			trip[i1] = trip[k];
			trip[k] = temp;
			i1++; k--;
		}
		return trip;
	}

	private void twoOpt(int[] possibleTrip){
		/*Implemented from sprint 3 slides*/
		boolean improvement = true;
		int delta;
		int trip[] = new int[possibleTrip.length+1];
		System.arraycopy(possibleTrip, 0, trip, 0, possibleTrip.length);
		trip[trip.length-1] = trip[0]; //round trip
		int n = trip.length-1;
		while (improvement) {
			improvement = false;
			//0 <= i < i+1 < k < k+1 <= n
			for (int i = 0; i <= n - 3; i++) {
				for (int k = i + 2; k <= n - 1; k++) {
					delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[k]][trip[k + 1]])
							+ (disTable[trip[i]][trip[k]]) + (disTable[trip[i + 1]][trip[k + 1]]);
					if (delta < 0) {  //subtract the change from totalDist. FIXME might need to change dist w/in swap call
                        			trip = twoOptSwap(trip, i + 1, k);
                        			improvement = true;
                       				curTotalDistM += delta;  
                        			curTotalDistK += delta;
                    			}
				}
			}
		}
		bestTrip = trip;
	}

	private ArrayList<distanceObject> disObjectify(ArrayList<Destination> orderedLocations){

		ArrayList<distanceObject> trip = new ArrayList<distanceObject>(orderedLocations.size()+1);
		int j = 1;
		for(int i = 0; j < orderedLocations.size(); i++){
			distanceObject dObj = new distanceObject(orderedLocations.get(i), orderedLocations.get(j));
			trip.add(dObj);
			j++;
		}
		trip.add(new distanceObject(orderedLocations.get(orderedLocations.size()-1), orderedLocations.get(0)));//connect last to first
		return trip;
	}

	private int[] calcShortestTrip(){//loops through each starting node and calls calDist with that start node
		int trip[] = new int[disTable.length];
		for(int i = 0; i < disTable.length; i++){
			currentTrip[curTripPtr] = i;	//always currentTrip[0] = i;
			calDist(i);
			twoOpt(currentTrip);
			//add the distance of the last destination to the first destination
			//NOTE: at this point curTotalDist holds the Nearest Neighbor distance of that starting node
            		curTotalDistM += disTable[currentTrip[currentTrip.length-1]][currentTrip[0]];
            		curTotalDistK += disTable[currentTrip[currentTrip.length-1]][currentTrip[0]];
            		if((curTotalDistM < minTotalDistM)&&(curTotalDistK < minTotalDistK)){
                		minTotalDistM = curTotalDistM;
                		minTotalDistK = curTotalDistK;
                		for(int k = 0; k < currentTrip.length; k++){
                    			trip[k] = currentTrip[k];
                		}
            		}
            		curTotalDistM = 0;
            		curTotalDistK = 0;
            		curTripPtr = 0;
            		visTable = new int[locations.size()][locations.size()]; //zero out table for next iteration
        	}
        	return trip;
	}

	private void calDist(int startIndex){
		//Set 1's in the startIndex column for all rows, so no destination can be picked twice
		for(int i = 0; i < disTable.length; i++){
			visTable[i][startIndex] = 1;
		}
		curTripPtr++;
		if(curTripPtr < currentTrip.length) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < disTable[startIndex].length; i++) {
				if(i == startIndex){
					continue;
				}
				if (disTable[startIndex][i] < min && visTable[startIndex][i] != 1) {
					min = disTable[startIndex][i];
					currentTrip[curTripPtr] = i;
				}
			}
                	curTotalDistM += min;
                	curTotalDistK += min;
			calDist(currentTrip[curTripPtr]);
		}
	}
}
