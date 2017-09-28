package edu.csu2017fa314.T04;
import edu.csu2017fa314.T04.Model.*;
import edu.csu2017fa314.T04.View.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TripCo
{

   private String name = "";

   public String getName()
   {
      return name;
   }

   public String getMessage()
   {
      if (name == "")
      {
         return "Hello!";
      }
      else
      {
         return "Hello " + name + "!";
      }
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public static void main(String[] args) {
	  
      System.out.println("Welcome to TripCo");
//       System.out.println(args[0]);
       CSVReader csvReader = new CSVReader(args[0]);
       ArrayList<Brewery> breweryList = new ArrayList<Brewery>(csvReader.getBreweryList());
       ArrayList<distanceObject> test = new ArrayList<distanceObject>();
	   
	ArrrayList<Brewery> trip = new ArrayList<Brewery>(); //create trip of brewery objects
	shortTrip st = new shortTrip(breweryList);       //instantiate new shortTrip object
	trip = st.getShortestTrip(breweryList);          //find the shortest
	   
       	test = View.calculateDistance(breweryList);

    	BuildSVG bsvg = new BuildSVG(test,args[1]);
   	View.writeItinerary(test);
   }

}
