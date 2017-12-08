package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Model.Destination;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.Assert.assertEquals;

public class ViewTest {
    private View view;
    private distanceObject dist;
    private String[] labels;
    private ArrayList<String> info1;
    private ArrayList<String> info2;
    private ArrayList<distanceObject> list;
    
    /**
    * Sets up the variables needed for the View tests
    */
    @Before
    public void setUp() throws Exception {
        view = new View();
        info1 = new ArrayList<>(Arrays.asList("Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL"));
        info2  = new ArrayList(Arrays.asList("Kit Carson County Airport", "39.24250031", "-102.2850037", "KITR"));
        labels = new String[]{"name", "latitude", "longitude", "id"};
        dist = new distanceObject(new Destination(info1, labels), new Destination(info2, labels));
        list = new ArrayList<>();
        list.add(dist);
    }

    @Test
    public void setGetTotalDistanceM() throws Exception {
        view.setTotalDistanceM(10);
        assertEquals(view.getTotalDistanceM(), 10);
    }
    
    @Test
    public void setGetTotalDistanceK() throws Exception {
        view.setTotalDistanceK(17);
        assertEquals(view.getTotalDistanceK(), 17);
    }

    @Test
    public void writeItinerarySetup() throws Exception {
        view.writeItinerarySetup(list);
    }

    @Test
    public void writeFile() throws Exception {
    }

    @Test
    public void createItinerary() throws Exception {
    }

}
