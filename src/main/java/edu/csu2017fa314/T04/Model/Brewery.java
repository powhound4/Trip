package edu.csu2017fa314.T04.Model;
import java.util.ArrayList;
import java.util.Arrays;


public class Brewery {
    
	protected String[] brewInfo = null;
	protected String[] labels = null;

	public Brewery(){
	}
	
	public Brewery(String[] info, String[] labels){
        this.brewInfo = info;
        //ArrayList<String> brewInfo = new ArrayList<String>(Arrays.asList(info));
        this.labels = labels;
	}
	private int getLabelIndex(String lab){
        	return Arrays.asList(this.labels).indexOf(lab);
	}
	public String[] getLabels(){
        	return this.labels;
	}
	
	public String getBrewInfo(String labelName){
        	return Arrays.asList(this.brewInfo).get(getLabelIndex(labelName));
	}
	public String[] getBrewInfo(){
        	return this.brewInfo;
	}
    
    	public String getId(){
		return Arrays.asList(this.brewInfo).get(getLabelIndex("id"));
	}
	
	public String getName(){
		return Arrays.asList(this.brewInfo).get(getLabelIndex("name"));
	}
	
	public String getCity(){
		return Arrays.asList(this.brewInfo).get(getLabelIndex("city"));
	}
	
	public String getLatitude(){
		return Arrays.asList(this.brewInfo).get(getLabelIndex("latitude"));
	}
	
	public String getLongitude(){
		return Arrays.asList(this.brewInfo).get(getLabelIndex("longitude"));
	}
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

	
	public int computeDistance(Brewery location2){
		int totalDistance = 0;
		double latA = 0.0; double latB = 0.0; double longA = 0.0; double longB = 0.0;
		double sin = 0.0; double cos = 0.0; double distance = 0.0;
		double deltalat = 0.0; double deltalong = 0.0; double earthradmi = 3958.7613;
			
		latA = Math.toRadians(toDecimal(this.getLatitude().replaceAll("\\s","")));		//φ1 remove whitespace before passed to toDecimal method
		longA = Math.toRadians(toDecimal(this.getLongitude().replaceAll("\\s",""))); 	//φ2
		latB = Math.toRadians(toDecimal(location2.getLatitude().replaceAll("\\s","")));		//λ1
		longB = Math.toRadians(toDecimal(location2.getLongitude().replaceAll("\\s","")));	//λ2
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
	//could make array a class variable and compute when constructor is called..
	//could also call the replaceAll("\\s","") in toDecimal()
	public double[] getCoSVGPoint(){
		return scale2CoSVG(toDecimal(this.getLatitude().replaceAll("\\s","")), toDecimal(this.getLongitude().replaceAll("\\s","")));
	}
	//second argument (longitude) must be negative ex -104.9903
	//this is based on the colorado map provided in sprint2
	private double[] scale2CoSVG(double latitude, double longitude){
		int northLong = -109;
		int northLat = 41;
		double scaleH = 176.4625;
		double scaleW = 141.1714286;
		double tempLat = northLat - latitude;
		double tempLong = longitude - northLong;
		double svgH = (tempLat * scaleH) + 38.3;
		double svgW = (tempLong * scaleW) + 38.3;
		double[] svg = {svgH,svgW}; //{lat,long}
		return svg;
	}
	/*public String getElevation(){
		return elevationFt;
	}*/
	
}
