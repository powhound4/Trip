package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Model.Destination;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ViewTest {
    private View view;
    private distanceObject d;
    private String[] info1, info2, labels;
    private ArrayList<distanceObject> list;

    @Before
    public void setUp() throws Exception {
        view = new View();
        info1 = new String[]{"Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL"};
        info2  = new String[]{"Kit Carson County Airport", "39.24250031", "-102.2850037", "KITR"};
        labels = new String[]{"name", "latitude", "longitude", "id"};
        d = new distanceObject(new Destination(info1, labels), new Destination(info2, labels));
        list = new ArrayList<>();
        list.add(d);
    }

    @Test
    public void setGetTotalDistance() throws Exception {
        view.setTotalDistance(10);
        assertEquals(view.getTotalDistance(), 10);
    }

    @Test
    public void writeItinerary() throws Exception {
        view.writeItinerary(list);
    }

    @Test
    public void writeFile() throws Exception {
    }

    @Test
    public void createItinerary() throws Exception {
    }

}
