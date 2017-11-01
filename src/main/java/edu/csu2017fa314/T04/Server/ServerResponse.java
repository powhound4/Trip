package edu.csu2017fa314.T04.Server;

import edu.csu2017fa314.T04.Model.*;
import edu.csu2017fa314.T04.View.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ServerResponse {
    private String svg = "";
    private String id = "";
    private ArrayList<distanceObject> destinations = new ArrayList<distanceObject>();
    private ArrayList<String> searchResults = new ArrayList<String>();

    
    public ServerResponse(String svg, ArrayList<distanceObject> destinations, String id) {
        this.svg = svg;
        this.destinations = destinations;
        this.id = id;
    }
    
    public ServerResponse(ArrayList<String> destResults, String id) {
        this.searchResults = destResults;
        this.id = id;
    }
    public ServerResponse(){
    }
    //This is only to print what is being returned, the json object still contains {svg: "", destinations: Array(0), searchResults: Array(30)}
    @Override
    public String toString() {
    String ret = "";
        if(!this.destinations.isEmpty()){ 
            ret = "ServerResponse{" + "id="+ id + " svg='" + svg + '\'' + ", locations=" + destinations + '}';
        }
        if(!this.searchResults.isEmpty()){
        ret = "ServerResponse{"+ "id="+ id + " locations=" + searchResults + '}';
        }
        return ret;
    }
}
