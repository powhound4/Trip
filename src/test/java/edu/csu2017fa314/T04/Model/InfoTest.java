package edu.csu2017fa314.T04.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfoTest {
    private Info info;
    private String[] i;
    @Before
    public void setUp() throws Exception {
        i = new String[]{"name", "latitude", "longitude", "id", "city"};
        info = new Info(i);
    }

    @Test
    public void getInfo() throws Exception {
        assertTrue(info.getInfo() == i);
    }

    @Test
    public void writeInfo() throws Exception {

    }

    @Test
    public void writeInfoFile() throws Exception {

    }

}
