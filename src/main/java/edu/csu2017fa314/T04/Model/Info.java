package edu.csu2017fa314.T04.Model;

import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Info {
protected String [] info = null;

public Info(String[] brewInfo){
	this.info = brewInfo;
}

public String [] getInfo(){
	return this.info;
}

/**
* Starts a JSONArray for writeInfoFile
* Writes info to a JSONArray trip
*/
 public void writeInfo(){
	JSONArray trip = new JSONArray();
	for(int i =0; i < this.info.length; i++){
		JSONObject temp = new JSONObject();
	
		temp.put(i, this.info[i]);
		
		trip.add(temp);
	}
	writeInfoFile(trip);
}


/**
* Prints/flushes the destination info into a file on the web side
* Takes a JSONArray of info labels as input
* Throws an exception if the write results in error
*/
public void writeInfoFile(JSONArray labels){

	try{
		//System.out.println(new File(".").getAbsolutePath());
		FileWriter file = new FileWriter("web/info.json");
		file.write(labels.toJSONString());
		file.flush();
		file.close();
		//System.out.println("Success");
	}catch (IOException e){
		e.printStackTrace();
	}
	
 }

}
