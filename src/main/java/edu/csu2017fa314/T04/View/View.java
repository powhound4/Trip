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


	public static void writeItinerary(ArrayList<distanceObject> itinerary){
		JSONArray trip = new JSONArray();
		for(int i =0; i < itinerary.size(); i++){
			JSONObject temp = new JSONObject();

			temp.put("end", itinerary.get(i).endName);
			String[] endBrew = itinerary.get(i).getB2Info();
			String[] brew2Labels = itinerary.get(i).getB2Labels();

			for(int j=0; j < endBrew.length; j++){
				String end = "end_" + brew2Labels[j];
				temp.put(end, endBrew[j]);
			}

			temp.put("distance",itinerary.get(i).totalDistance);
			temp.put("start" ,itinerary.get(i).startName);

			String[] startBrew = itinerary.get(i).getB1Info();
			String[] brew1Labels = itinerary.get(i).getB1Labels();

			for(int j=0; j < startBrew.length; j++){
				String start = "start_" + brew1Labels[j];
				temp.put(start, startBrew[j]);
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



