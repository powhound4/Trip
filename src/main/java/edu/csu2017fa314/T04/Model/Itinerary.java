package edu.csu2017fa314.T04.Model;
import java.util.ArrayList;

public class Itinerary {
    private Info info;
    private ArrayList<Destination> destinations = new ArrayList<Destination>();
    
    public Itinerary(){
    }
    
    public Itinerary(ArrayList<Destination> dests, Info labels){
     this.destinations = dests;
     this.info = new Info(labels);
    }
    
    public void setInfo(Info labels){
        this.info = new Info(labels);
    }
    
    public void addDestination(Destination place){
        this.destinations.add(place);
    }
    
    public ArrayList<Destination> getDestinations(){
        return this.destinations;
    }
    
}
