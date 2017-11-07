package edu.csu2017fa314.T04.Model;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DestinationTest {

    private Destination d;
    private String[] labels;
    private ArrayList<String> info;

    @Before
    public void setUp() throws Exception {
        info = new ArrayList<>(Arrays.asList("Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL", "FoCo"));
        labels = new String[]{"name", "latitude", "longitude", "id", "city"};
        d = new Destination(info, labels);
    }

    @Test
    public void getLabels() throws Exception {
        assertTrue(d.getLabels() == labels);
    }

    @Test
    public void getInfo() throws Exception {
        assertTrue(d.getInfo() == info);
    }

    @Test
    public void getLabel() throws Exception {
        assertTrue(d.getLabel("id") == "KFNL");
    }

    @Test
    public void getId() throws Exception {
        assertTrue(d.getId() == "KFNL");
    }

    @Test
    public void getName() throws Exception {
        assertEquals(d.getName(), "Fort Collins Loveland Municipal Airport");
    }


    @Test
    public void getLatitude() throws Exception {
        assertEquals(d.getLatitude(), "40.4518013");
    }

    @Test
    public void getLongitude() throws Exception {
        assertEquals(d.getLongitude(), "-105.0110016");
    }

    @Test
    public void computeDistanceM() throws Exception {
        assertEquals(d.computeDistanceM(d), 0);
    }
    
    @Test
    public void computeDistanceK() throws Exception {
        assertEquals(d.computeDistanceK(d), 0);
    }

}
