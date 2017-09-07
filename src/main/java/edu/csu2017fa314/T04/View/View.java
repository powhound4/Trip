package edu.csu2017fa314.T04.View;
import edu.csu2017fa314.T04.Model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class View
{
  private int totalDistance;

public void setTotalDistance(int distance) 
   {
       totalDistance = distance; 
   }

   public int getTotalDistance() 
   {
      return totalDistance;
   }
   
   
public static ArrayList<distanceObject> calculateDistance(ArrayList<Brewery> breweries){
	
	ArrayList<distanceObject> trip = new ArrayList<distanceObject>();
	int j = 1;
	
  	for(int i = 0; j < breweries.size(); i++){
  	
  		distanceObject dObj = new distanceObject(breweries.get(i).getId(), breweries.get(j).getId(), breweries.get(i).getLatitude(), 
  									breweries.get(i).getLongitude(), breweries.get(j).getLatitude(), breweries.get(j).getLongitude());
  		trip.add(dObj);
  		
  		j++;
   		
  	}
  	
  	return trip;
}

public static void writeItinerary(ArrayList<distanceObject> itinerary){
	JSONArray trip = new JSONArray();
	for(int i =0; i < itinerary.size(); i++){
		JSONObject temp = new JSONObject();
	
		temp.put("end", itinerary.get(i).endID);
		temp.put("distance",itinerary.get(i).totalDistance);
		temp.put("start" ,itinerary.get(i).startID);
		
		trip.add(temp);
	}
	writeFile(trip);
}



public static void writeFile(JSONArray locations){

	try{
		//System.out.println(new File(".").getAbsolutePath());
		FileWriter file = new FileWriter("web/test.json");
		file.write(locations.toJSONString());
		file.flush();
		file.close();
		//System.out.println("Success");
	}catch (IOException e){
		e.printStackTrace();
	}
	
 }

public static void createItinerary(ArrayList<Brewery> breweries){
	
	//use info from brewery objects to calculate distance, create distanceObjects using constructor and add them to 
	//distanceObject ArrayList
//	
	ArrayList<distanceObject> test = new ArrayList<distanceObject>();
	
	
	writeItinerary(test);
			
        
}

}



