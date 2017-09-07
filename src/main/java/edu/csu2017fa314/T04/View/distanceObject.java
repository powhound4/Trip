package edu.csu2017fa314.T04.View;


public class distanceObject {
	public String startID ="";
	public String endID = "";
	public double totalDistance;

	public distanceObject(String startID, String endID, double totalDistance){
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
	
	public double toDecimal(String degree){
	   	//convert lattitude and longitude to a decimal value
		String[] decdegree = degree.split("[°' \"]");
		return Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60
			+Double.parseDouble(decdegree[2])/3600;
	}
	
	public distanceObject computeDistance(String ID1, String ID2, String lat1, String long1, String lat2, String long2){
		double latA = 0.0; double latB = 0.0; double longA = 0.0; double longB = 0.0;
		double sin = 0.0; double cos = 0.0; double distance = 0.0;
		double deltalat = 0.0; double deltalong = 0.0; double earthradmi = 3958.7613;
			
		latA = Math.toRadians(toDecimal(lat1));		//φ1
		longA = Math.toRadians(toDecimal(long1)); 	//φ2
		latB = Math.toRadians(toDecimal(lat2));		//λ1
		longB = Math.toRadians(toDecimal(long2));	//λ2
		deltalong = Math.abs(longB - longA); 		//Δλ
		
		sin = Math.sqrt((Math.cos(latB) * Math.sin(deltalong)) * (Math.cos(latB) * (Math.sin(deltalong))) 
				+ ((((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB)) 
					* (Math.cos(deltalong))) * ((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB)) 
						* (Math.cos(deltalong))))));
		cos = Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(deltalong);
		
		deltalat = Math.atan(sin/cos);
		distance = earthradmi * deltalat;
		distanceObject d = new distanceObject(ID1, ID2, distance);
		totalDistance = distance;
		return d;
	}

}
