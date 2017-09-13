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
	
	public String getBrewInfo(String labelName){
        	return Arrays.asList(this.brewInfo).get(getLabelIndex(labelName));
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
	
	/*public String getElevation(){
		return elevationFt;
	}*/
	
}
