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
    private String negDir;
    private String dms;
    private String dm;
    private String ds;
    private String degree;
    private String decimal;

    @Before
    public void setUp() throws Exception {
        info1 = new ArrayList<>(Arrays.asList("Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL"));
        info2  = new ArrayList<>(Arrays.asList("Kit Carson County Airport", "39.24250031", "-102.2850037", "KITR"));
        labels = new String[]{"name", "latitude", "longitude", "id"};
        negDir = "104°45'32\" W";
        dms = "104°45'32\" N";  // in degrees, minutes, seconds
        dm = "104°45' N";       // in degrees, minutes
        ds = "104°32\" N";      // in degrees, seconds
        degree = "104° N";
        decimal = "104";
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
        
        //Already in decimal
        assertTrue(distanceObject.toDecimal("0") == 0);
        assertTrue(distanceObject.toDecimal("104") == 104);
        assertTrue(distanceObject.toDecimal("-104") == -104);
        assertTrue(distanceObject.toDecimal("123.123") == 123.123);
        assertTrue(distanceObject.toDecimal("-4.002") == -4.002);
 
        //Need conversion
        assertEquals(104.0, distanceObject.toDecimal("104° N"), 0.1);
        assertEquals(-104.0, distanceObject.toDecimal("104° W"), 0.1);
        assertEquals(104.00889, distanceObject.toDecimal("104°32\" N"), 0.001);
        assertEquals(-104.00889, distanceObject.toDecimal("104°32\" W"), 0.001);
        assertEquals(104.75, distanceObject.toDecimal("104°45' N"), 0.001);
        assertEquals(-104.75, distanceObject.toDecimal("104°45' W"), 0.001);
        assertEquals(104.75889, distanceObject.toDecimal("104°45'32\" N"), 0.001);
        assertEquals(-104.75889, distanceObject.toDecimal("104°45'32\" W"), 0.001);
    }
    @Test
    public void getCounts() throws Exception {
        int countdeg = 0;
        int countmin = 0;
        int countsec = 0;
        for (int i = 0; i < dms.length(); i++){
            if (i == dms.length()-1) {
                assertEquals(true, distanceObject.isNegativeDir(negDir, false, i));
                assertEquals(false, distanceObject.isNegativeDir(dms, false, i));
            }            
            countmin = distanceObject.getCountmin(dms, countmin, i);
            countsec = distanceObject.getCountsec(dms, countsec, i);
            countdeg = distanceObject.getCountdeg(dms, countdeg, i);
        }
        assertEquals(countdeg, 1);
        assertEquals(countmin, 1);
        assertEquals(countsec, 1);
    }
    @Test
    public void isNegativeDeg() throws Exception {
        assertEquals(distanceObject.isNegativeDeg(false, 104), false);
        assertEquals(distanceObject.isNegativeDeg(true, -104), true);
    }
    @Test
    public void inDec() throws Exception {
        assertEquals(104.0, distanceObject.inDec(decimal, false), 0.1);
        assertEquals(-104.0, distanceObject.inDec(decimal, true), 0.1);
    }
    @Test
    public void inDeg() throws Exception {
        assertEquals(104, distanceObject.inDeg(degree, false), 0.1);
        assertEquals(-104, distanceObject.inDeg(degree, true), 0.1);
    }
    @Test
    public void inDegMin() throws Exception {
        assertEquals(104.75, distanceObject.inDegMin(dm, false), 0.001);
        assertEquals(-104.75, distanceObject.inDegMin(dm, true), 0.001);
    }
    @Test
    public void inDegSec() throws Exception {
        assertEquals(104.00889, distanceObject.inDegSec(ds, false), 0.001);
        assertEquals(-104.00889, distanceObject.inDegSec(ds, true), 0.001);
    }
    @Test
    public void inDegMinSec() throws Exception {
        assertEquals(104.75889, distanceObject.inDegMinSec(dms, false), 0.001);
        assertEquals(-104.75889, distanceObject.inDegMinSec(dms, true), 0.001);
    }

    @Test
    public void computeDistanceM() throws Exception {
        assertEquals(167, d.computeDistanceM());
    }
    @Test
    public void computeDistanceK() throws Exception {
            assertEquals(269, d.computeDistanceK());
    }
}
