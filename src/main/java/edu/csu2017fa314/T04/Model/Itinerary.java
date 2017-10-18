package edu.csu2017fa314.T04.Model;
import java.util.ArrayList;
import edu.csu2017fa314.T04.View.*;

public class Itinerary {
    private Info info;
    private ArrayList<Destination> destinations = new ArrayList<Destination>();
    
    public Itinerary(){
    }
    
    public Itinerary(ArrayList<Destination> dests, Info labels){
     this.destinations = dests;
     this.info = labels;
    }
    
    public void setInfo(Info labels){
        this.info = labels;
    }
    
    public Info getInfo(){
        return this.info;
    }
    
    public void addDestination(Destination place){
        this.destinations.add(place);
    }
    
    public ArrayList<Destination> getDestinations(){
        return this.destinations;
    }
    
    public ArrayList<distanceObject> buildItinerary(){
        NearestNeighbor nn = new NearestNeighbor(this.destinations);//instantiate new NearestNeighbor object
        ArrayList<distanceObject> trip = nn.getNearestNeighborTrip(); //find the shortest
        BuildSVG bsvg = new BuildSVG(trip,"src/main/java/edu/csu2017fa314/T04/View/ColoradoMap.svg");
        View.writeItinerary(trip);
        return trip;
    }
    
}
