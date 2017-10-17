package edu.csu2017fa314.T04.Server;

import edu.csu2017fa314.T04.Model.*;
import edu.csu2017fa314.T04.View.*;
import java.util.Arrays;
import java.util.ArrayList;

public class ServerResponse {
    private String svg = "";
    private ArrayList<distanceObject> destinations;

    
    public ServerResponse(String svg, ArrayList<distanceObject> destinations) {
        this.svg = svg;
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "svg='" + svg + '\'' +
                ", locations=" + destinations +
                '}';
    }
}
