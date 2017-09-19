package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Model.Brewery;

public class distanceObject {
	public  String startID ="";
	public  String endID ="";
	public String lat1 ="";
	public String lat2 = "";
	public String long1 = "";
	public String long2 = "";
	public int totalDistance;

	public distanceObject(Brewery start, Brewery end){ //not sure if we need all this in constructor,might be able to access this info when we write the json file
		
		this.startID = start.getId();
		this.endID = end.getId();
		this.lat1 = start.getLatitude();
		this.lat2 = start.getLongitude();
		this.long1 = end.getLatitude();
		this.long2 = end.getLongitude();
		
		this.totalDistance = start.computeDistance(end);
	}
	
	
	public String getStartID(){
		return startID;
	}
	
	public String getEndID(){
		return endID;
	}
	
	public int getTotalDistance(){
		return totalDistance;
	}

}
