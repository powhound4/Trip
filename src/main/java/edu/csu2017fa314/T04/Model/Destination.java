package edu.csu2017fa314.T04.Model;

import java.util.ArrayList;
import java.util.Arrays;


public class Destination {

	private ArrayList<String> destInfo;
	private String[] labels;
	private String id;
	private String name;
	private int totalDistanceM = 0;
	private int totalDistanceK = 0;
    	private double sin = 0.0; 
    	private double cos = 0.0;
	private double distance = 0.0;
	private double deltalong = 0.0;
	private double deltalat = 0.0; 

	public Destination(String id, String name){
        	this.id = id;
        	this.name = name;
	}
    
    	public Destination(ArrayList<String> info){
        	this.destInfo = info;
    	}
    
	public Destination(ArrayList<String> info, String[] labels){
		this.destInfo = info;
		//ArrayList<String> destInfo = new ArrayList<String>(Arrays.asList(info));
		this.labels = labels;
	}
	
	private int getLabelIndex(String lab){
		return Arrays.asList(this.labels).indexOf(lab);
	}
	public String[] getLabels(){
		return this.labels;
	}
	
	public String getLabel(String labelName){
		return this.destInfo.get(getLabelIndex(labelName));
	}

	public String getInfo(String labelName){
		return this.destInfo.get(getLabelIndex(labelName));
	}
	public ArrayList<String> getInfo(){
		return this.destInfo;
	}

	public String getId(){
		return this.destInfo.get(getLabelIndex("id"));
	}

	public String getName(){
		return this.destInfo.get(getLabelIndex("name"));
	}

	public String getLatitude(){
		return this.destInfo.get(getLabelIndex("latitude"));
	}

	public String getLongitude(){
		return this.destInfo.get(getLabelIndex("longitude"));
	}
	
	public String toString(){
		String res = "";
		for(int i=0; i < destInfo.size(); i++){
			res+=this.labels[i] + ":" + destInfo.get(i) + ", ";
		}
		return res;
	}
	
	private double toDecimal(String degree){
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

    public int computeDistanceM(Destination location2){
	//computes the distance between two destinations in miles
        double latA = Math.toRadians(toDecimal(this.getLatitude().replaceAll("\\s","")));          //φ1 remove whitespace before passed to toDecimal method
        double longA = Math.toRadians(toDecimal(this.getLongitude().replaceAll("\\s","")));        //φ2
        double latB = Math.toRadians(toDecimal(location2.getLatitude().replaceAll("\\s","")));	    //λ1
        double longB = Math.toRadians(toDecimal(location2.getLongitude().replaceAll("\\s","")));   //λ2
        distance = 0.0;
        deltalong = Math.abs(longB - longA); 		//Δλ

        sin = Math.sqrt((Math.cos(latB) * Math.sin(deltalong)) * (Math.cos(latB) * (Math.sin(deltalong)))
                + ((((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB))
                * (Math.cos(deltalong))) * ((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB))
                * (Math.cos(deltalong))))));
        cos = Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(deltalong);
        
        double earthradmi = 3958.7613;
        deltalat = Math.atan2(sin,cos);
        distance = earthradmi * deltalat;
        totalDistanceM = (int)Math.round(distance);
        return Math.abs(totalDistanceM);
    }

    public int computeDistanceK(Destination location2){
    //computes the distance between two destinations in kilometers
        double latA = Math.toRadians(toDecimal(this.getLatitude().replaceAll("\\s","")));          //φ1 remove whitespace before passed to toDecimal method
        double longA = Math.toRadians(toDecimal(this.getLongitude().replaceAll("\\s","")));        //φ2
        double latB = Math.toRadians(toDecimal(location2.getLatitude().replaceAll("\\s","")));	    //λ1
        double longB = Math.toRadians(toDecimal(location2.getLongitude().replaceAll("\\s","")));   //λ2
        distance = 0.0;
        deltalong = Math.abs(longB - longA); 		//Δλ

        sin = Math.sqrt((Math.cos(latB) * Math.sin(deltalong)) * (Math.cos(latB) * (Math.sin(deltalong)))
                + ((((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB))
                * (Math.cos(deltalong))) * ((Math.cos(latA) * Math.sin(latB)) - (Math.sin(latA) * Math.cos(latB))
                * (Math.cos(deltalong))))));
        cos = Math.sin(latA) * Math.sin(latB) + Math.cos(latA) * Math.cos(latB) * Math.cos(deltalong);

        double earthradkm = 6371.0088; 
        deltalat = Math.atan2(sin,cos);
        distance = earthradkm * deltalat;
        totalDistanceK = (int)Math.round(distance);
        return Math.abs(totalDistanceK);
    }

}
