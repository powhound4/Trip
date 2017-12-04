package edu.csu2017fa314.T04.Server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ServerRequestTest {
    ServerRequest req;
    private String name = "Fort Collins Loveland Municipal Airport";
    private String id = "KFNL";
    private String[] dests = {"Fort Collins Loveland Municipal Airport",
            "Denver International Airport"};
    private String[] units = {"miles"};
    private String[] opt = {"2Opt"};

    @Before
    public void setUp() throws Exception {
        req = new ServerRequest(name, id, dests, units, opt);
    }

    @Test
    public void getName() throws Exception {
        assertEquals(name, req.getName());
    }

    @Test
    public void getId() throws Exception {
        assertEquals(id, req.getId());
    }

    @Test
    public void getDests() throws Exception {
        String dests = "Fort Collins Loveland Municipal Airport', 'Denver International Airport";
        assertEquals(dests, req.getDests());
    }

    @Test
    public void getUnits() throws Exception {
        assertEquals("miles", req.getUnits());
    }

    @Test
    public void getOptimization() throws Exception {
        assertEquals("2Opt", req.getOptimization());
    }

    @Test
    public void testString() throws Exception {
        String res = "Request{"
                + "dests='" + req.getDests() + '\''
                + ", name='" + req.getName() + '\''
                + ", id='" + req.getId() + '\''
                + ", units='" + req.getUnits() + '\''
                + '}';
        assertEquals(res,req.toString());
    }

}
