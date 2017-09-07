package edu.csu2017fa314.T04.View;


public class distanceObject {
	public  String startID ="";
	public  String endID ="";
	public String lat1 ="";
	public String lat2 = "";
	public String long1 = "";
	public String long2 = "";
	public int totalDistance;

	public distanceObject(String startID, String endID, String lat1, String long1, String lat2, String long2){
		
		this.startID = startID;
		this.endID = endID;
		this.lat1 = lat1;
		this.lat2 = lat2;
		this.long1 = long1;
		this.long2 = long2;
		
		computeDistance();
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
	
	//convert lattitude and longitude to a decimal value
	public double toDecimal(String deg){ 
	    String[] decdegree = deg.split("[°' \"]");
	    return Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60
	    		+Double.parseDouble(decdegree[2])/3600;
	}

	
	public int computeDistance(){
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
		totalDistance = (int)Math.round(distance);
		return totalDistance;
	}
}
