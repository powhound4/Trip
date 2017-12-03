package edu.csu2017fa314.T04.Server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    Server serve;
    private String [] distUnits;

    ServerRequest req;
    private String name = "Fort Collins Loveland Municipal Airport";
    private String id = "KFNL";
    private String[] dests = {"Fort Collins Loveland Municipal Airport",
            "Denver International Airport"};
    private String[] units = {"miles"};
    private String[] opt = {"2Opt"};

    @Before
    public void setUp() throws Exception {
        serve = new Server();
        req = new ServerRequest(name, id, dests, units, opt);
        units[0] = "miles";
    }

    @Test
    public void getDistUnits() throws Exception {
        assertEquals(distUnits, serve.getDistUnits());
    }

}
