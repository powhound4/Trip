package edu.csu2017fa314.T04.Model;
import java.util.ArrayList;

public class Itinerary {
    private Info info;
    private ArrayList<Destination> destinations;
    
    public Itinerary(){
        this.destinations = new ArrayList<Destination>();
    }
    
    public Itinerary(ArrayList<Destination> dests, Info labels){
     this.destinations = dests;
     this.info = labels;

    }
    
    public void setInfo(Info labels){
        this.info = labels;
    }

    public Info getInfo(){ return this.info; }
    
    public void addDestination(Destination place){
        this.destinations.add(place);
    }
    
    public ArrayList<Destination> getDestinations(){
        return this.destinations;
    }
    
}
