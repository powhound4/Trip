package edu.csu2017fa314.T04.View;


public class distanceObject {
	public String startID ="";
	public String endID = "";
	public double totalDistance;

	
	distanceObject(String startID, String endID, double totalDistance){
		this.startID = startID;
		this.endID = endID;
		this.totalDistance = totalDistance;
		
	}
	
	public String getStartID(){
		return startID;
	}
	
	public String getEndID(){
		return endID;
	}
	
	public Double getTotalDistance(){
		return totalDistance;
	}

}
