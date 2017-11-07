package edu.csu2017fa314.T04.View;
import edu.csu2017fa314.T04.Server.*;
import edu.csu2017fa314.T04.Model.*;
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
    	public static String [] dUnits = {"miles"};
    	private static String kilometers = "kilometers";
    	private static String miles = "miles";
    
    	public View(){
        	setDistUnits();
    	}
    
    	public static void setDistUnits() {
       		Server s = new Server();
        	String [] units = s.distUnits;
        	if (units == null){
            		String [] startup = {"miles"};
            		units = startup;
        		}
        	dUnits = units;
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
	
	public static void writeItinerary(ArrayList<distanceObject> itinerary){
		JSONArray trip = new JSONArray();
		for(int i =0; i < itinerary.size(); i++){
			JSONObject temp = new JSONObject();

			temp.put("end", itinerary.get(i).endName);
			ArrayList<String> endBrew = itinerary.get(i).getB2Info();
			String[] brew2Labels = itinerary.get(i).getB2Labels();

			for(int j=0; j < endBrew.size(); j++){
				String end = "end_" + brew2Labels[j];
				temp.put(end, endBrew.get(j));
			}
			if (dUnits[0].equals(miles))
                		temp.put("distance:",itinerary.get(i).totalDistanceM);
            		else
                		temp.put("distance:",itinerary.get(i).totalDistanceK);
			
			temp.put("start" ,itinerary.get(i).startName);

			ArrayList<String> startBrew = itinerary.get(i).getB1Info();
			String[] brew1Labels = itinerary.get(i).getB1Labels();

			for(int j=0; j < startBrew.size(); j++){
				String start = "start_" + brew1Labels[j];
				temp.put(start, startBrew.get(j));
			}

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

	public static void createItinerary(ArrayList<Destination> breweries){

		//use info from brewery objects to calculate distance, create distanceObjects using constructor and add them to
		//distanceObject ArrayList
//	
		ArrayList<distanceObject> test = new ArrayList<distanceObject>();


		writeItinerary(test);


	}

}



