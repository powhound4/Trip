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
		this.lat1 = lat1.replaceAll("\\s","");
		this.lat2 = lat2.replaceAll("\\s","");
		this.long1 = long1.replaceAll("\\s","");
		this.long2 = long2.replaceAll("\\s","");
		
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
		public double toDecimal(String degree){ 
		int countmin = 0; int countsec = 0; int countdeg = 0;
		boolean negative = false;
	    
	    for (int i = 0; i < degree.length(); i++){
	    	if (degree.charAt(i) == '\'')
	    		countmin++;
	    	if (degree.charAt(i) == '"')
	    		countsec++;
	    	if (degree.charAt(i) == '°')
	    		countdeg++;
	    	if (degree.charAt(i) == 'W' || degree.charAt(i) == 'S')
	    		negative = true;
	    }
	    
	    if ((countdeg == 1) && (countmin == 1) && (countsec == 1)){
	    	String[] decdegree = degree.split("[°' \"]");
	    	double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60
		    		+Double.parseDouble(decdegree[2])/3600;
	    	if (negative == true)
	    		return result * -1;
	    	return result;
	    }
	    
	    else if ((countdeg == 1) && (countmin == 1) && (countsec == 0)){
	    	String[] decdegree = degree.split("[°' ]");
	    	double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60;
	    	if (negative == true)
	    		return result * -1;
	    	return result;
	    }
	    
	    else if ((countdeg == 1) && (countmin == 0) && (countsec == 1)){
	    	String[] decdegree = degree.split("[° \"]");
	    	double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/3600;
	    	if (negative == true)
	    		return result * -1;
	    	return result;
	    }
		
	    else if ((countdeg == 1) && (countmin == 0) && (countsec == 0)){
	    	String[] decdegree = degree.split("[° ]");
	    	double result = Double.parseDouble(decdegree[0]);
	    	if (negative == true)
	    		return result * -1;
	    	return result;
	    }
	    else{
	    	String[] decdegree = new String[1];
	    	decdegree[0] = degree;
	    	double result = Double.parseDouble(decdegree[0]);
	    	if (negative == true)
	    		return result * -1;
	    	return result;
	    }
	    
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
		return Math.abs(totalDistance);
	}
}
