package edu.csu2017fa314.T04.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItineraryTest {
    private Itinerary it1;
    private Itinerary it2;
    private Destination d;
    private String[] info, labels;
    private ArrayList<Destination> ds;
    private Info in;

    @Before
    public void setUp() throws Exception {
        info = new String[]{"Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL", "FoCo"};
        labels = new String[]{"name", "latitude", "longitude", "id", "city"};
        d = new Destination(info, labels);
        ds = new ArrayList<>();
        ds.add(d);
        in = new Info(labels);
        it1 = new Itinerary();
        it2 = new Itinerary(ds, in);
    }

    @Test
    public void setGetInfo() throws Exception {
        it1.setInfo(in);
        assertTrue(it1.getInfo().info == labels);
    }


    @Test
    public void addGetDestination() throws Exception {
        it1.addDestination(d);
        assertTrue(it1.getDestinations().equals(it2.getDestinations()));
    }

}
