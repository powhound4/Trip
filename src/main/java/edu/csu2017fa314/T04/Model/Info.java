package edu.csu2017fa314.T04.Model;
import java.util.Arrays;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Info {
protected String info[] = null;

public Info(String[] brewInfo){
this.info = brewInfo;
}

public String[] getInfo(){
return this.info;
}


 public void writeInfo(){
	JSONArray trip = new JSONArray();
	for(int i =0; i < this.info.length; i++){
		JSONObject temp = new JSONObject();
	
		temp.put(i, this.info[i]);
		
		trip.add(temp);
	}
	writeInfoFile(trip);
}



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
