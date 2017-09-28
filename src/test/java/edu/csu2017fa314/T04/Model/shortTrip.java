package edu.csu2017fa314.T04.Model;
import edu.csu2017fa314.T04.View.*;

import java.util.ArrayList;

public class shortTrip {
	  
	ArrayList<Brewery> bestSoFar = new ArrayList<>();
	ArrayList<Brewery> visited = new ArrayList<>();
	ArrayList<Brewery> locations;
    int totalTrip = 0;
    public int bestTrip = Integer.MAX_VALUE;
    
  

	public shortTrip(ArrayList<Brewery> breweryList) {
		this.locations = breweryList;
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Brewery> getShortestTrip(ArrayList<Brewery> locations){
    	visited.clear();
    	bestSoFar = findShortestTrip(locations.get(0));
    	//bestTrip = totalTrip;
    	
    	
    	for(int i = 1; i < locations.size(); i++){
    		visited.clear(); //clear all vars to calculate new trip
    		totalTrip =0;
    		
    		findShortestTrip(locations.get(i));
    	
    		if(totalTrip < bestTrip){    //if this is the shortest so far, update bestTrip
    			
    			bestTrip = totalTrip;
    			bestSoFar = (ArrayList<Brewery>)visited.clone();  //save itinerary for the best trip
    		}
    	}
    	
    	return bestSoFar;
    }

    public ArrayList<Brewery> findShortestTrip(Brewery startLocation){
    	
    	Brewery nextClosest;
    	visited.add(startLocation); //add start location to visited array
    	
    	while(visited.size() < locations.size()){
    		
    		nextClosest = findClosest(startLocation);       //find the next closest location
    		visited.add(nextClosest);
    		startLocation = nextClosest;
                  
    	}
    	
    	visited.add(visited.get(0));
    	totalTrip += startLocation.computeDistance(visited.get(0));
    	//System.out.println("TotalTrip: " + totalTrip);
    	return visited;
    	
    }
    
    public Brewery findClosest(Brewery current){
    	int actualMin = 1000000;   //set to arbitrarily large value- fix this
    	int distance = 0;
    	Brewery closest = null; 
    	
    	for(int i = 0; i < locations.size(); i ++){
    		
    		distance = current.computeDistance(locations.get(i));       //loop and calculate all distances
    		
    		//this is the same location, keep going
    		if(current.getId().equals(locations.get(i).getId())){
    			continue;                                                
    		}
    		//location has been visited, keep going
    		if(isVisited(locations.get(i))){
    			continue;
    		}
    		//if distance is less than the current min distance, this is the new min
    		if(distance < actualMin){
    			actualMin = distance;
    			closest = locations.get(i); //this is the new closest
    		}
    	}
    	
    	totalTrip += current.computeDistance(closest);      //keep running total of the trip
    	return closest;
    }
    
    public boolean isVisited(Brewery location){
    	return(visited.contains(location));
    }

}
