package edu.csu2017fa314.T04.Model;
import java.util.ArrayList;
import edu.csu2017fa314.T04.View.*;

public class Itinerary {
    private Info info;
    private ArrayList<Destination> destinations = new ArrayList<Destination>();
    String optimization;
    
    public Itinerary(){
    }
    
    public Itinerary(ArrayList<Destination> dests, Info labels, String optimization){
     this.destinations = dests;
     this.info = labels;
     this.optimization = optimization;
    }
    
    public void setInfo(Info labels){
        this.info = labels;
    }
    
    public Info getInfo(){
        return this.info;
    }

    public String getOpt(){
        return this.optimization;
    }
    
    public void addDestination(Destination place){
        this.destinations.add(place);
    }
    
    public ArrayList<Destination> getDestinations(){
        return this.destinations;
    }
    
    public ArrayList<distanceObject> buildItinerary(){
        NearestNeighbor nn = new NearestNeighbor(this.destinations, this.optimization);//instantiate new NearestNeighbor object
        System.out.println("calling nearest neighbor trip with this optimization: " + this.optimization);
        ArrayList<distanceObject> trip = nn.getNearestNeighborTrip(); //find the shortest
        BuildSVG bsvg = new BuildSVG(trip,"src/main/java/edu/csu2017fa314/T04/View/World.svg");
        View.writeItinerarySetup(trip);
        return trip;
    }
    
}
