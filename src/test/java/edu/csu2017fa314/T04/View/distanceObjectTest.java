package edu.csu2017fa314.T04.View;
import edu.csu2017fa314.T04.Model.Destination;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class distanceObjectTest {

    private distanceObject d;
    private String[] labels;
    private ArrayList<String> info1, info2;

    @Before
    public void setUp() throws Exception {
        info1 = new ArrayList<>(Arrays.asList("Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL"));
        info2  = new ArrayList<>(Arrays.asList("Kit Carson County Airport", "39.24250031", "-102.2850037", "KITR"));
        labels = new String[]{"name", "latitude", "longitude", "id"};
        d = new distanceObject(new Destination(info1, labels), new Destination(info2, labels));
    }
    @Test
    public void getB2Info() throws Exception {
        assertTrue(d.getB2Info() == info2);
    }
    @Test
    public void getB1Info() throws Exception {
        assertTrue(d.getB1Info() == info1);
    }
    @Test
    public void getB1Labels() throws Exception {
        assertTrue(d.getB1Labels() == labels);
    }
    @Test
    public void getB2Labels() throws Exception {
        assertTrue(d.getB2Labels() == labels);
    }
    @Test
    public void getStartID() throws Exception {
        assertEquals(d.getStartID(), "KFNL");
    }
    @Test
    public void getEndID() throws Exception {
        assertEquals(d.getEndID(), "KITR");
    }
    @Test
    public void getStartName() throws Exception {
        assertEquals(d.getStartName(), "Fort Collins Loveland Municipal Airport");
    }
    @Test
    public void getEndName() throws Exception {
        assertEquals(d.getEndName(), "Kit Carson County Airport");
    }
    @Test
    public void getDistanceInM() throws Exception {
        assertEquals(167, d.getDistanceInM());
    }
    @Test
    public void getDistanceInK() throws Exception {
        assertEquals(269, d.getDistanceInK());
    }
    @Test
    public void toDecimal() throws Exception {
        assertTrue(distanceObject.toDecimal("0") == 0);
        assertTrue(distanceObject.toDecimal("123.123") == 123.123);
        assertTrue(distanceObject.toDecimal("-4.002") == -4.002);
    }
    @Test
    public void computeDistanceM() throws Exception {
        assertEquals(167, d.computeDistanceM());
    }
    @Test
    publiv void computeDistanceK() throws Exception {
        assertEquals(269, d.computeDistanceK());
    }

}
