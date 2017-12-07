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
	public String optimization;

	public NearestNeighbor(ArrayList<Destination> locations, String optimization) {
		this.locations = locations;
		disTable = new int[locations.size()][locations.size()];
		visTable = new int[locations.size()][locations.size()];
		currentTrip = new int[disTable.length];
		curTripPtr = 0;
		minTotalDistM = Integer.MAX_VALUE;
		curTotalDistM = 0;
		//setNnUnits();
		this.optimization = optimization;
		fillInMap();
	}

	
	int calculateDistance(int[] array){
	int totalDistance = 0;
	for(int i = 0; i < array.length-1; i++)
	{
		totalDistance += disTable[array[i]][array[i+1]];
		}
		return totalDistance;
	}

	public int getTotalDistanceM() {
		return minTotalDistM;
	}
	
	public int[][] getDisTable(){
		return this.disTable;
	}

	public int[] getBestTrip(){
		return this.bestTrip;
	}
	
	public String getOptimization(){
		return this.optimization;
	}

	/**
	*fills in distance table
	*returns nothing
	*/
	public void fillInMap() {
		for (int i = 0; i < locations.size(); i++) {
			for (int j = 0; j < locations.size(); j++) {
				if (i == j) {
					disTable[i][j] = Integer.MAX_VALUE;
				} else {
						disTable[i][j] = locations.get(i).computeDistanceM(locations.get(j));
				}

			}
		}
	}


	public ArrayList<distanceObject> getNearestNeighborTrip() {

		if(this.optimization.equals("In Order")){
			return disObjectify(locations);      //no optimization, just return disobject array
		}

		bestTrip = calcShortestTrip();

		if(this.optimization.equals("2 Opt")){
			twoOpt(bestTrip);
		}
		if(this.optimization.equals("3 Opt")){
		threeOpt(bestTrip);
	
		}
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

	/**
	*Calculates the shortest trip, 2opt
	*returns nothing
	*/
	public void twoOpt(int[] possibleTrip){
		/*Implemented from sprint 3 slides*/
		boolean improvement = true;
		int delta;
		int trip [] = new int [possibleTrip.length];
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
		trip.add(new distanceObject(orderedLocations.get(orderedLocations.size()-1), orderedLocations.get(0)));
		//connect last to first
		return trip;
	}

	/**
	*Calculates the shortest trip, NN
	*returns int[] array of trip
	*/
	public int[] calcShortestTrip(){
		//loops through each starting node and calls calNearNeigh with that start node
		int trip [] = new int [disTable.length + 1];
		for(int i = 0; i < disTable.length; i++){
			currentTrip[curTripPtr] = i;	//always currentTrip[0] = i;
			calNearNeigh(i);
			//add the distance of the last destination to the first destination
			//NOTE: at this point curTotalDist holds the Nearest Neighbor distance of that starting node
			curTotalDistM += disTable[currentTrip[currentTrip.length-1]][currentTrip[0]];
			if((curTotalDistM < minTotalDistM)){
				minTotalDistM = curTotalDistM;
				for(int k = 0; k < currentTrip.length; k++){
					trip[k] = currentTrip[k];
				}
			}
			curTotalDistM = 0;
			curTripPtr = 0;
			visTable = new int[locations.size()][locations.size()]; //zero out table for next iteration
		}
		return trip;
	}

	/**
	*Calculates the shortest trip, NN
	*returns nothing
	*/
	private void calNearNeigh(int startIndex){
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
			calNearNeigh(currentTrip[curTripPtr]);
		}
	}



	public void threeOpt(int[] possibleTrip) {

		boolean improvement = true;
		int trip [] = new int [possibleTrip.length];
		System.arraycopy(possibleTrip, 0, trip, 0, possibleTrip.length);
		trip[trip.length - 1] = trip[0]; //round trip

		int n = trip.length - 1;
		while (improvement) {
			improvement = false;
			//0 <= i < i+1 < j < j+1 < k < k+1 <= n
			for (int i = 0; i <= n - 5; i++) {
				for (int j = i + 1; j < n - 3; j++) {
					for (int k = j + 1; k <= n - 1; k++) {

						int[] distArr = new int[7];
						distArr[0] = twoOpt1(trip, i, j);
						distArr[1] = twoOpt2(trip, i, j, k);
						distArr[2] = twoOpt3(trip, i, j, k);
						distArr[3] = threeOpt1(trip, i, j, k);
						distArr[4] = threeOpt2(trip, i, j, k);
						distArr[5] = threeOpt3(trip, i, j, k);
						distArr[6] = threeOpt4(trip, i, j, k);

						int min = Integer.MAX_VALUE;
						int index = -1;
						for (int a = 0; a < distArr.length; a++) {
							if (distArr[a] < min) {
								min = distArr[a];
								index = a;
							}
						}
						if (min < 0) {
							//improvement = true;
						} else {
							continue;
						}
						trip = makeImprovement(trip, index, i, j, k);
						curTotalDistM += distArr[index];
					}
				}
			}
			if (improvement)
				System.out.println("Improvement");
			bestTrip = trip;
		}
	}

	private int[] makeImprovement(int[] trip, int index, int i, int j, int k) {
		assert (index >= 0 && index <= 6);
		int[] imprTrip = new int[0];
		switch (index) {
			case 0:
				//2opt1
				imprTrip = twoOptSwap(trip, i + 1, j);
				break;
			case 1:
				imprTrip = twoOptSwap(trip, j + 1, k);
				break;
			case 2:
				//2opt3
				imprTrip = twoOptSwap(trip, i + 1, k);
				break;
			case 3:
				//3opt1
				imprTrip = twoOptSwap(trip, i + 1, j);
				imprTrip = twoOptSwap(imprTrip, j + 1, k);
				break;
			case 4:
				//imprTrip = twoOptSwap(trip,j+1, k);
				//imprTrip = rotateTrip(imprTrip, i,j,k);
				imprTrip = twoOptSwap(trip, i + 1, j);
				imprTrip = twoOptSwap(imprTrip, k + 1, i);
				break;
			case 5:
				//imprTrip = twoOptSwap(trip,i+1, j);
				//imprTrip = rotateTrip(imprTrip, i,j,k);
				imprTrip = twoOptSwap(trip, j + 1, k);
				imprTrip = twoOptSwap(imprTrip, k + 1, i);
				break;
			case 6:
				//3opt4
				//imprTrip = rotateTrip(trip,i,j,k);
				//imprTrip = rotateTrip(imprTrip,i,j,k);
				imprTrip = twoOptSwap(trip, i+1, j);
				imprTrip = twoOptSwap(imprTrip, j+1, k);
				imprTrip = twoOptSwap(imprTrip, k+1, i);
				break;
			default:
		}
		return imprTrip;
	}

	/*private int[] rotateTrip(int[] trip, int i, int j, int k) {
		int temp;
		while(j < k){
			temp = trip[j];
			trip[j] = trip[i];
			trip[i] = temp;
			i++; j++;
		}
		return trip;
	}
		int temp;
		while(j<k){
			temp = trip[i];
			trip[i] = j;

		}

	}*/
	//subtractions for 2 opt are always [i][i+1], [j][j+1]
	//subtractions for 3 opt are always [i][i+1], [j][j+1], [k][k+1]
	//additions for 2 opt are the border cases
	//		case1: [i][j], [i+1][j+1]
	//		case2: [j][k], [j+1][k+1]
	//		case3: [i][k], [i+1][k+1]
	//additions for 3 opt are border cases
	//		case1: [i][j], [i+1][k], [j+1][k+1]
	//		case2: [i][k], [j+1][i+1], [j][k+1]
	//		case3: [i][j+1], [k][j], [i+1][k+1]
	//		case4: [i][j+1], [k][i+1], [j][k+1]
	/**
	*twoOpt Case 1
	*Returns delta
	*/
public int twoOpt1(int[] trip, int i, int j) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]])
				+ (disTable[trip[i]][trip[j]]) + (disTable[trip[i + 1]][trip[j + 1]]);
		System.out.println("test" + delta);
		return delta;
	}
	
	/**
	*twoopt case 2
	*Returns delta
	*/
	public int twoOpt2(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[k]][trip[k + 1]]) - (disTable[trip[j]][trip[j + 1]])
				+ (disTable[trip[j]][trip[k]]) + (disTable[trip[j + 1]][trip[k + 1]]);
		System.out.println("test2" + delta);

		return delta;
	}
	
	/**
	*twoOpt case 3
	*Returns delta
	*/
	public int twoOpt3(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[k]]) + (disTable[trip[i + 1]][trip[k + 1]]);
		System.out.println("test3" + delta);

		return delta;
	}
	
	/**
	*threeOpt Case 1
	*Returns delta
	*/
	public int threeOpt1(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) 
			- (disTable[trip[k]][trip[k + 1]])+ (disTable[trip[i]][trip[j]]) 
			+ (disTable[trip[i + 1]][trip[k]]) + (disTable[trip[j + 1]][trip[k+1]]);
		System.out.println("test4" + delta);

		return delta;
	}
	
	/**
	*threeOpt Case 2
	*Returns delta
	*/
	public int threeOpt2(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) 
			- (disTable[trip[k]][trip[k + 1]])+ (disTable[trip[i]][trip[k]]) 
			+ (disTable[trip[j + 1]][trip[i + 1]]) + (disTable[trip[j]][trip[k + 1]]);
		System.out.println("test5" + delta);

		return delta;
	}
	
	/**
	*threeOpt Case 3
	*Returns delta
	*/
	public int threeOpt3(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) 
			- (disTable[trip[k]][trip[k + 1]])+ (disTable[trip[i]][trip[j + 1]]) 
			+ (disTable[trip[k]][trip[j]]) + (disTable[trip[i + 1]][trip[k + 1]]);
		System.out.println("test6" + delta);

		return delta;
	}
	
	/**
	*threeOpt Case 4
	*Returns delta
	*/
	public int threeOpt4(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) 
			- (disTable[trip[k]][trip[k + 1]])+ (disTable[trip[i]][trip[j + 1]]) 
			+ (disTable[trip[k]][trip[i + 1]]) + (disTable[trip[j]][trip[k + 1]]);
		System.out.println("test7" + delta);

		return delta;
	}



}

