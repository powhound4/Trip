package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Model.*;
import edu.csu2017fa314.T04.Server.*;
import java.util.ArrayList;
import java.util.Arrays;

public class distanceObject {
	public  String startID ="";
	public  String endID ="";
	public String startName = "";
	public String endName = "";
	public String lat1 ="";
	public String lat2 = "";
	public String long1 = "";
	public String long2 = "";
    	public String units = "";
    	public String kilometers = "kilometers";
    	public String miles = "miles";
    	public double latA = 0.0; 
    	public double latB = 0.0; 
    	public double longA = 0.0; 
    	public double longB = 0.0;
    	public double sin = 0.0; 
    	public double cos = 0.0; 
   	public double distance = 0.0;
    	public double deltalat = 0.0; 
    	public double deltalong = 0.0;
    	public int totalDistanceM = 0;
    	public int totalDistanceK = 0;
	public ArrayList<String> b1Info;
	public ArrayList<String> b2Info;
	public String[] b1Labels = null;
	public String[] b2Labels = null;
    	public static String [] dUnits = {"miles"};

	//Distance object that takes 2 brewery objects
	public distanceObject(Destination b1, Destination b2){
        	this.b1Info = b1.getInfo();
        	this.b2Info = b2.getInfo();
        	this.b1Labels = b1.getLabels();
        	this.b2Labels = b2.getLabels();
        
		this.startID = b1.getId();
		this.endID = b2.getId();
		
		this.startName = b1.getName();
		this.endName = b2.getName();
		
		this.lat1 = b1.getLatitude().replaceAll("\\s","");
		this.lat2 = b2.getLatitude().replaceAll("\\s","");
		this.long1 = b1.getLongitude().replaceAll("\\s","");
		this.long2 = b2.getLongitude().replaceAll("\\s","");
		
		toStringUnits();
		computeDistanceM();
		computeDistanceK();
	}
	
    	public String toString(){
        	String res = "";
    
        	for(int i=0; i < this.b1Info.size(); i++){
            		res+="start_" + this.b1Labels[i] + " : " + this.b1Info.get(i) + ", ";
        	}
        	for(int i=0; i < this.b2Info.size(); i++){
            		res+="end_" + this.b2Labels[i] + " : " + this.b2Info.get(i) + ", ";
        	}
        	if(units.equals(miles)){
            		res += "Total distance in miles: " + this.totalDistanceM;
		}
        	else{
            		res += "Total distance in kilometers: " + this.totalDistanceK;
		}
        	return res;
    	}
	
	public ArrayList<String> getB2Info(){
        	return this.b2Info;
	}
	public ArrayList<String> getB1Info(){
        	return this.b1Info;
	}
	public String[] getB1Labels(){
        	return this.b1Labels;
	}
	public String[] getB2Labels(){
        	return this.b2Labels;
	}
	
	public String getStartID(){
		return startID;
	}
	
	public String getEndID(){
		return endID;
	}
	
	public String getStartName(){
       		return startName;
        }
    
    	public String getEndName(){
        	return endName;
        }
	
	public int getDistanceInM(){
        	return totalDistanceM;
    	}
    
    	public int getDistanceInK(){
        	return totalDistanceK;
    	}
	
	//sets the units for the distanceObject class
	public static void toStringUnits() {
        	Server sDist = new Server();
        	String [] units = sDist.distUnits;
        	if (units == null){
           		String [] startup = {"miles"};
            		units = startup;
        	}
        	dUnits = units;
     	}
	
	//convert lattitude and longitude to a decimal value
	public static double toDecimal(String degree){
		int countmin = 0; int countsec = 0; int countdeg = 0;
		boolean negative = false;

	    	for (int i = 0; i < degree.length(); i++){
			countmin = getCountmin(degree, countmin, i);
			countsec = getCountsec(degree, countsec, i);
			countdeg = getCountdeg(degree, countdeg, i);
			negative = isNegativeDir(degree, negative, i);
		}

		if ((countdeg == 1) && (countmin == 1) && (countsec == 1)){
			return setDMS(degree, negative);
		}
		else if ((countdeg == 1) && (countmin == 1) && (countsec == 0)){
			return setDM(degree, negative);
		}
		else if ((countdeg == 1) && (countmin == 0) && (countsec == 1)){
			return setDS(degree, negative);
		}
		else if ((countdeg == 1) && (countmin == 0) && (countsec == 0)){
			return setDeg(degree, negative);
		}
		else{
			return setDec(degree, negative);
		}
	}
	
	private static double setDec(String degree, boolean negative) {
		String[] decdegree = new String[1];
		decdegree[0] = degree;
		double result = Double.parseDouble(decdegree[0]);
		if (isNegativeDeg(negative, result)) return result * -1;
		return result;
	}

	private static double setDeg(String degree, boolean negative) {
		String[] decdegree = degree.split("[° ]");
		double result = Double.parseDouble(decdegree[0]);
		if (isNegativeDeg(negative, result)) return result * -1;
		return result;
	}

	private static double setDS(String degree, boolean negative) {
		String[] decdegree = degree.split("[° \"]");
		double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/3600;
		if (isNegativeDeg(negative, result)) return result * -1;
		return result;
	}

	private static double setDM(String degree, boolean negative) {
		String[] decdegree = degree.split("[°' ]");
		double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60;
		if (isNegativeDeg(negative, result)) return result * -1;
		return result;
	}

	private static double setDMS(String degree, boolean negative) {
		String[] decdegree = degree.split("[°' \"]");
		double result = Double.parseDouble(decdegree[0])+Double.parseDouble(decdegree[1])/60
                +Double.parseDouble(decdegree[2])/3600;
		if (isNegativeDeg(negative, result)) return result * -1;
		return result;
	}

	private static int getCountdeg(String degree, int countdeg, int i) {
		if (degree.charAt(i) == '°'){
            		countdeg++;
        	}
		return countdeg;
	}

	private static int getCountsec(String degree, int countsec, int i) {
		if (degree.charAt(i) == '"'){
            countsec++;
        }
		return countsec;
	}

	private static int getCountmin(String degree, int countmin, int i) {
		if (degree.charAt(i) == '\''){
            countmin++;
        }
		return countmin;
	}

	private static boolean isNegativeDeg(boolean negative, double result) {
		if (negative == true){
			return true;
		}
		return false;
	}

	private static boolean isNegativeDir(String degree, boolean negative, int i) {
		if (degree.charAt(i) == 'W' || degree.charAt(i) == 'S'){
            negative = true;
    	}
		return negative;
	}	
	
	//set up the variables for the distance calculation
	public void distanceSetup(){
        	distance = 0.0;
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
        
       		deltalat = Math.atan2(sin,cos);
    	}
    
	//compute distance in miles
    	public int computeDistanceM(){
        	distanceSetup();
        	double earthradmi = 3958.7613;
        	distance = earthradmi * deltalat;
        	totalDistanceM = (int)Math.round(distance);
        	totalDistanceM = Math.abs(totalDistanceM);
        	return totalDistanceM;
    	}

	//compute distance in kilometers
    	public int computeDistanceK(){
        	distanceSetup();
        	double earthradkm = 6371.0088;
        	distance = earthradkm * deltalat;
        	totalDistanceK = (int)Math.round(distance);
        	totalDistanceK = Math.abs(totalDistanceK);
        	return totalDistanceK;
    	}
}
