package edu.csu2017fa314.T04.Model;

import java.util.ArrayList;
import java.util.Arrays;
import edu.csu2017fa314.T04.View.distanceObject;

public class Destination {

	private ArrayList<String> destInfo;
	private String[] labels;
	private String id;
	private String name; 

	public Destination(String id, String name){
        	this.id = id;
        	this.name = name;
	}
    
    	public Destination(ArrayList<String> info){
        	this.destInfo = info;
    	}
    
	/**
 	* Sets the info and labels for the destination
 	* param info contains destination info
	* param labels contains destination labels
 	*/
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

	/**
 	* Writes destination information to a string
 	* returns a concatenated string of the destination information
 	*/
	public String toString(){
		String res = "";
		for(int i=0; i < destInfo.size(); i++){
			res+=this.labels[i] + ":" + destInfo.get(i) + ", ";
		}
		return res;
	}
	
	/**
 	* Calls the computeDistanceM method of the distanceObject class
 	* param dest2: a Destination object for the second destination
 	* returns the total distance in miles
 	*/
	public int computeDistanceM(Destination dest2){
		distanceObject d = new distanceObject(this, dest2);
		return d.computeDistanceM();
    	}
	
	/**
 	* Calls the computeDistanceK method of the distanceObject class
 	* param dest2: a Destination object for the second destination
 	* returns the total distance in kilomters
 	*/
	public int computeDistanceK(Destination dest2){
		distanceObject d = new distanceObject(this, dest2);
		return d.computeDistanceK();
    	}
	
}
