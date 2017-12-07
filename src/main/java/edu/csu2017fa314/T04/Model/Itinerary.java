package edu.csu2017fa314.T04.Model;

import java.util.ArrayList;

import edu.csu2017fa314.T04.View.distanceObject;

public class Itinerary {
    private Info info;
    private ArrayList<Destination> destinations = new ArrayList<Destination>();
    String optimization;
    
    public Itinerary(){
    }
    
    /**
    * Constructs an Itinerary object
    * Takes a destination ArrayList, a String array of labels
    *     and a user-selected optimization
    */
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
    
    /**
    * Builds an Itinerary by creating a NearestNeighbor object
    * The best trip is recorded into a distanceObject ArrayList
    * Build both the SVG and KML files for the trip
    */
    public ArrayList<distanceObject> buildItinerary(){
        //instantiate new NearestNeighbor object
        NearestNeighbor nn = new NearestNeighbor(this.destinations, this.optimization);
        System.out.println("calling NN trip with this optimization: " + this.optimization);
        ArrayList<distanceObject> trip = nn.getNearestNeighborTrip(); //find the shortest
        BuildSVG bsvg = new BuildSVG(trip,"src/main/java/edu/csu2017fa314/T04/View/World.svg");
        BuildKML bkml = new BuildKML(trip);
        View.writeItinerarySetup(trip);
        return trip;
    }
    
}
