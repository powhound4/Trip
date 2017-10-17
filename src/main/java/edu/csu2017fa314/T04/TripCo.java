package edu.csu2017fa314.T04;
import edu.csu2017fa314.T04.Model.*;
import edu.csu2017fa314.T04.View.*;
import edu.csu2017fa314.T04.Server.*;
import java.util.ArrayList;

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
      Server s = new Server();
      s.serve();
      /*CSVReader csvReader = new CSVReader(args[0]);
      ArrayList<Destination> breweryList = new ArrayList<Destination>(csvReader.getBreweryList());
      NearestNeighbor nn = new NearestNeighbor(breweryList);       //instantiate new NearestNeighbor object
      ArrayList<distanceObject> trip = nn.getNearestNeighborTrip();          //find the shortest
      BuildSVG bsvg = new BuildSVG(trip,args[1]);
      View.writeItinerary(trip);*/
   }

}
