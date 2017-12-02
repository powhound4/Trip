package edu.csu2017fa314.T04.Model;

import edu.csu2017fa314.T04.View.distanceObject;
import edu.csu2017fa314.T04.Server.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NearestNeighbor {

	private ArrayList<Destination> locations;
	private int disTable[][];
	private int visTable[][];
	private int bestTrip[];
	private int currentTrip[];
	private int curTripPtr;
	private int minTotalDistM;
	private int curTotalDistM;
	//public static String [] nnUnits = {"miles"};
	//public String kilometers = "kilometers";
	//public String miles = "miles";
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

	/*
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
*/

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
	/*
        public int getTotalDistanceK() {
            return minTotalDistK;
        }
    */
	public String getOptimization(){
		return this.optimization;
	}

	private void fillInMap() {
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
			System.out.println("In Order");
			return disObjectify(locations);      //no optimization, just return disobject array
		}


		bestTrip = calcShortestTrip();
		curTotalDistM = calculateDistance(bestTrip);
		System.out.println("this is curTotaldist m for nn " + curTotalDistM );

		if(this.optimization.equals("2 Opt")){
			System.out.println("Calling 2 opt");
			twoOpt(bestTrip);
		}
		System.out.println("this is this.optimization " + this.optimization);
		if(this.optimization.equals("3 Opt")){
			threeOpt(bestTrip);
			System.out.println("Calling 3 opt");
		}
		ArrayList<Destination> orderedDestinations = new ArrayList<>(locations.size());
		for(int i = 0; i < locations.size(); i++){
			orderedDestinations.add(locations.get(bestTrip[i]));
		}
		System.out.println("this sis the final trip " + Arrays.toString(bestTrip));
		return disObjectify(orderedDestinations);
	}

	private int[] twoOptSwap(int[] trip, int i1, int k){
		/*
		Implemented from sprint 3 slides
		 */

		int temp;
		/*while(i1 < k){
				temp = trip[i1];
				trip[i1++] = trip[k];
				trip[k--] = temp;
		}*/
		while(i1 < k){
			temp = trip[i1];
			trip[i1] = trip[k];
			trip[k] = temp;
			i1++; k--;
		}
		//System.out.println("this is in twoOptSwapt " + Arrays.toString(trip));
		return trip;
	}
	private int [] rotateTrip(int[] trip, int start, int middle , int end){
		twoOptSwap(trip, start, middle);
		twoOptSwap(trip, middle+1, end);
		twoOptSwap(trip, start, end);

		return trip;
	}

	private void twoOpt(int[] possibleTrip){
		System.out.println("this is possible trip: " + Arrays.toString(possibleTrip));
		/*Implemented from sprint 3 slides*/
		boolean improvement = true;
		int delta;
		int trip[] = new int[possibleTrip.length];
		System.arraycopy(possibleTrip, 0, trip, 0, possibleTrip.length);
		System.out.println("this is trip after copy: " + Arrays.toString(trip));
		//trip[trip.length-1] = trip[0]; //round trip
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
						curTotalDistM = calculateDistance(trip);
					}
				}
			}
		}
		//trip[trip.length-1] = trip[0]; //round trip
		curTotalDistM = calculateDistance(trip);
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
		//if(trip.get(trip.size()-1).get)
		//trip.add(new distanceObject(orderedLocations.get(orderedLocations.size()-1), orderedLocations.get(0)));//connect last to first
		return trip;
	}

	private int[] calcShortestTrip(){//loops through each starting node and calls calNearNeigh with that start node
		int trip[] = new int[disTable.length];
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
			trip[trip.length-1] = trip[0];
		return trip;
	}

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



	private void threeOpt(int[] possibleTrip) {
		System.out.println("this is possible trip in 3 opt: " + Arrays.toString(possibleTrip));
		int currentTotalDistance = calculateDistance(possibleTrip);


		boolean improvement = true;
		int trip[] = new int[possibleTrip.length];
		System.arraycopy(possibleTrip, 0, trip, 0, possibleTrip.length);
		//trip[trip.length - 1] = trip[0]; //round trip

		int n = trip.length - 1;
		//while (improvement) {
		improvement = false;
		int[] distArr = new int[7];

		//0 <= i < i+1 < j < j+1 < k < k+1 <= n
		for (int i = 0; i <= n - 3; i++) {
			for (int j = i + 1; j <= n - 2; j++) {
				for (int k = j + 1; k <= n - 1; k++) {

					distArr[0] = twoOpt1(trip, i, j);
					distArr[1] = twoOpt2(trip, i, j, k);
					distArr[2] = twoOpt3(trip, i, j, k);
					distArr[3] = threeOpt1(trip, i, j, k);
					distArr[4] = threeOpt2(trip, i, j, k);
					distArr[5] = threeOpt3(trip, i, j, k);
					distArr[6] = threeOpt4(trip, i, j, k);

					//System.out.println("THIS IS THE DIST ARRAY : " + Arrays.toString(distArr));
					int currentDistance = disTable[trip[i]][trip[i + 1]] + disTable[trip[j]][trip[j + 1]] + disTable[trip[k]][trip[k + 1]];
					//System.out.println("this is the currentTotalDistance variable " + currentDistance);


					int index = -1;
					int min = Integer.MAX_VALUE;
					System.out.println("Max Value min " + min);
					for (int a = 0; a < distArr.length; a++) {
						if (distArr[a] < min) { //smallest of the negative one

							min = distArr[a];
							//System.out.println("this is the min: " + min);
							index = a;
						}
					}
					//System.out.println("min " + min);
					if(min < 0) {
						System.out.println("Min: " + min);
						System.out.println("INIFINE");
						System.out.println("Index - " + index);
						System.out.println("i, j, k - " + i + ", " + j + ", " + k);
						for(int l: distArr)
						System.out.println(distArr[i]);
						//System.out.println("Making improvement with index: " + index);
						//System.out.println("This is before improvement " + Arrays.toString(trip));
						trip = makeImprovement(trip, index, i, j, k);
						improvement = true;
					}

							/*if(tmp > currentTotalDistance ){
								System.out.println("Undoing......");
								//undo
								trip = makeImprovement(trip, index, i, j, k);
							}*/
					//else{
					currentTotalDistance = calculateDistance(trip);
					//System.out.println("this is cur Totaldistm " + currentTotalDistance);
					//improvement = true;
					//}


				/*		if (improvement) {
							System.out.println("making improvement...");

							//improvement = true;
						} else {
							continue;
						}*/
					//trip = makeImprovement(trip, index, i, j, k);
//						/curTotalDistM += distArr[index];
				}
			}
		}
/*			if (improvement)
				System.out.println("Improvement");
				} */

	//	}

		bestTrip = trip;
		System.out.println(calculateDistance(bestTrip));
	}

	private int[] makeImprovement(int[] trip, int index, int i, int j, int k) {
		assert (index >= 0 && index <= 6);
		int[] imprTrip = new int[trip.length];
		switch (index) {
			case 0:
				//2opt1
				//System.out.println("this is case 0");
				imprTrip = twoOptSwap(trip, i + 1, j);
				//System.out.println(Arrays.toString(imprTrip));

				break;
			case 1:
				//System.out.println("this is case 1");

				imprTrip = twoOptSwap(trip, j + 1, k);
				//System.out.println(Arrays.toString(imprTrip));

				break;
			case 2:
				//2opt3
				//System.out.println("this is case 2");

				imprTrip = twoOptSwap(trip, i, k+1);
				//System.out.println(Arrays.toString(imprTrip));

				break;
			case 3:
				//3opt1
				//System.out.println("this is case 3");
				//System.out.println(Arrays.toString(trip));
				imprTrip = twoOptSwap(trip, i + 1, j);
				//System.out.println(Arrays.toString(imprTrip));

				imprTrip = twoOptSwap(imprTrip, j + 1, k);
				//System.out.println(Arrays.toString(imprTrip));

				break;
			case 4:
				//System.out.println("this is case 4");

				imprTrip = twoOptSwap(trip,i+1, j);
				imprTrip = twoOptSwap(imprTrip, k+1, i);
				//imprTrip = rotateTrip(imprTrip, i+1,j,k);
/*				System.out.println("i = " + i);
				System.out.println("i+1 = " + (i+1));
				System.out.println("j = " + j);
				System.out.println("j+1 = " + (j+1));
				System.out.println("k = " + k);
				System.out.println("k+1 = " + (k+1));*/



				//imprTrip = twoOptSwap(trip, i + 1, k);
				//System.out.println("Printing in case 4 " + Arrays.toString(imprTrip));

				//imprTrip = twoOptSwap(imprTrip, j, i+1);
				//System.out.println("Printing in case 4 " + Arrays.toString(imprTrip));

				break;
			case 5:
				//System.out.println("this is case 5");

				imprTrip = twoOptSwap(trip,j+1, k);
				imprTrip = twoOptSwap(imprTrip, k+1, i);
				//imprTrip = rotateTrip(imprTrip, i+1,j,k);

				break;
			case 6:
				//System.out.println("this is case 6");

				//3opt4

				//imprTrip = rotateTrip(trip,i+1,j,k);
				imprTrip = twoOptSwap(trip, i+1, j);
				imprTrip = twoOptSwap(imprTrip, j+1, k);
				imprTrip = twoOptSwap(imprTrip, k+1, i);
				//imprTrip = twoOptSwap(imprTrip, k, i+1);
				//imprTrip = twoOptSwap(imprTrip, j+1, j);
				//System.out.println(Arrays.toString(imprTrip));


				//System.out.println(Arrays.toString(imprTrip));

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
	//		case1: [i][j], [i+1][k], [j+1][k]
	//		case2: [i][k], [j+1][i+1], [j][k+1]
	//		case3: [i][j+1], [k][j], [i+1][k+1]
	//		case4: [i][j+1], [k][i+1], [j][k+1]

	private int twoOpt1(int[] trip, int i, int j) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]])
				+ (disTable[trip[i]][trip[j]]) + (disTable[trip[i + 1]][trip[j + 1]]);
		return delta;
	}

	private int twoOpt2(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[k]][trip[k + 1]]) - (disTable[trip[j]][trip[j + 1]])
				+ (disTable[trip[j]][trip[k]]) + (disTable[trip[j + 1]][trip[k + 1]]);
		return delta;
	}

	private int twoOpt3(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[k]]) + (disTable[trip[i + 1]][trip[k + 1]]);
		return delta;
	}

	private int threeOpt1(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[j]]) + (disTable[trip[i + 1]][trip[k]]) + (disTable[trip[j + 1]][trip[k+1]]);
		return delta;
	}

	private int threeOpt2(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[k]]) + (disTable[trip[j + 1]][trip[i + 1]]) + (disTable[trip[j]][trip[k + 1]]);
		return delta;
	}

	private int threeOpt3(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[j + 1]]) + (disTable[trip[k]][trip[j]]) + (disTable[trip[i + 1]][trip[k + 1]]);
		return delta;
	}

	private int threeOpt4(int[] trip, int i, int j, int k) {
		int delta = -(disTable[trip[i]][trip[i + 1]]) - (disTable[trip[j]][trip[j + 1]]) - (disTable[trip[k]][trip[k + 1]])
				+ (disTable[trip[i]][trip[j + 1]]) + (disTable[trip[k]][trip[i + 1]]) + (disTable[trip[j]][trip[k + 1]]);
		return delta;
	}


}

