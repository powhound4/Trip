package edu.csu2017fa314.T04.Model;
import edu.csu2017fa314.T04.View.distanceObject;
import java.util.ArrayList;

public class NearestNeighbor {
	  
	private ArrayList<Destination> locations;
	private int disTable[][];
	private int visTable[][];
	private int bestTrip[];
	private int alreadyUsed[];
	private int usedPtr;
	private int minTotalDist;
	private int totalDist;

	public NearestNeighbor(ArrayList<Destination> locations) {
		this.locations = locations;
		disTable = new int[locations.size()][locations.size()];
		visTable = new int[locations.size()][locations.size()];
		alreadyUsed = new int[disTable.length];
		usedPtr = 0;
		minTotalDist = Integer.MAX_VALUE;
		totalDist = 0;
		fillInMap();
	}

	public int getTotalDistance() {
		return minTotalDist;
	}

	private void fillInMap() {
		for (int i = 0; i < locations.size(); i++) {
			for (int j = 0; j < locations.size(); j++) {
				if (i == j) {
					disTable[i][j] = Integer.MAX_VALUE;
				} else {
					disTable[i][j] = locations.get(i).computeDistance(locations.get(j));
				}
			}
		}
	}

	public ArrayList<distanceObject> getNearestNeighborTrip() {
		bestTrip= calcShortestTrip();
		ArrayList<Destination> orderedLocations = new ArrayList<Destination>(locations.size());
		for(int i = 0; i < locations.size(); i++){
			orderedLocations.add(locations.get(bestTrip[i]));
		}
		return disObjectify(orderedLocations);
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

	private int[] calcShortestTrip(){
		//loops through each starting node and calls calDist with that start node
		int trip[] = new int[disTable.length];
		for(int i = 0; i < disTable.length; i++){
			alreadyUsed[usedPtr] = i;
			calDist(i);
			//add the distance of the last destination to the first destination
			totalDist += disTable[alreadyUsed[alreadyUsed.length-1]][alreadyUsed[0]];

			if(totalDist < minTotalDist){
				minTotalDist = totalDist;
				for(int k = 0; k < alreadyUsed.length; k++){
					trip[k] = alreadyUsed[k];
				}
			}
			usedPtr = 0;
			totalDist = 0;
			visTable = new int[locations.size()][locations.size()]; //zero out table for next iteration
		}
		return trip;
	}

	private void calDist(int startIndex){
		//Set 1's in the startIndex column for all rows, so no destination can be picked twice
		for(int i = 0; i < disTable.length; i++){
			visTable[i][startIndex] = 1;
		}
		usedPtr++;
		if(usedPtr < alreadyUsed.length) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < disTable[startIndex].length; i++) {
				if(i == startIndex){
					continue;
				}
				if (disTable[startIndex][i] < min && visTable[startIndex][i] != 1) {
					min = disTable[startIndex][i];
					alreadyUsed[usedPtr] = i;
				}
			}
			totalDist += min;
			calDist(alreadyUsed[usedPtr]);
		}
	}

}
