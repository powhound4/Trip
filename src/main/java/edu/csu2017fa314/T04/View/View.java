package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Server.Server;
import edu.csu2017fa314.T04.Model.Destination;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class View{
	
	private int totalDistanceM;
    	private int totalDistanceK;
    	public static String [] vUnits = {"miles"};
    	private static String kilometers = "kilometers";
    	private static String miles = "miles";
    
    	public View(){
        	setViewUnits();
    	}
	
        //sets the units for the view class
    	public static void setViewUnits() {
        	Server serveUnits = new Server();
        	if (serveUnits.distUnits == null){
            		String [] vStart = {"miles"};
            		vUnits = vStart;
        	}
        	else{
            	vUnits = serveUnits.distUnits;
        	}
     	}

    	public void setTotalDistanceM(int distance){
        	totalDistanceM = distance;
    	}
    
    	public void setTotalDistanceK(int distance){
        	totalDistanceK = distance;
    	}
    
    	public int getTotalDistanceM(){
        	return totalDistanceM;
    	}
    
    	public int getTotalDistanceK(){
       		return totalDistanceK;
    	}
	
	//put together the pieces of the itinerary
    	public static JSONArray writeItinerary(ArrayList<distanceObject> itinerary, JSONArray trip){
         	for(int i =0; i < itinerary.size(); i++){
            		JSONObject makeTrip = new JSONObject();
            		makeTrip.put("end", itinerary.get(i).endName);
           		ArrayList<String> endBrew = itinerary.get(i).getB2Info();
    	        	String[] brew2Labels = itinerary.get(i).getB2Labels();
    	        	for(int j=0; j < endBrew.size(); j++){
     	        		String end = "end_" + brew2Labels[j];
        		        makeTrip.put(end, endBrew.get(j));
       	    		}
            		if (vUnits[0].equals(miles)){
       			        makeTrip.put("distance:",itinerary.get(i).totalDistanceM);
          		}else{
               			makeTrip.put("distance:",itinerary.get(i).totalDistanceK);
            		}
            		makeTrip.put("start" ,itinerary.get(i).startName);
            		ArrayList<String> startBrew = itinerary.get(i).getB1Info();
            		String[] brew1Labels = itinerary.get(i).getB1Labels();
            
            		for(int j=0; j < startBrew.size(); j++){
                		String start = "start_" + brew1Labels[j];
         	       		makeTrip.put(start, startBrew.get(j));
            		}
            		trip.add(makeTrip);
        	}
        	return(trip);
   	}
    	
	//set up the itinerary
	public static void writeItinerarySetup(ArrayList<distanceObject> itinerary){
		JSONArray trip = new JSONArray();
        	trip = writeItinerary(itinerary, trip);
        	writeFile(trip);
    	}
	
	//write the json file
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
	//create the arraylist to hold the itinerary
	public static void createItinerary(ArrayList<Destination> breweries){
		//use info from brewery objects to calculate distance, create distanceObjects using constructor and add them to
		//distanceObject ArrayList
		ArrayList<distanceObject> test = new ArrayList<distanceObject>();
		writeItinerarySetup(test);
	}

}
