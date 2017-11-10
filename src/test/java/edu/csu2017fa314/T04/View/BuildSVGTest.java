package edu.csu2017fa314.T04.View;

import edu.csu2017fa314.T04.Model.CSVReader;
import edu.csu2017fa314.T04.Model.Destination;
import edu.csu2017fa314.T04.Model.NearestNeighbor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BuildSVGTest {

    BuildSVG bsvg;
    ArrayList<distanceObject> disOb;
    String SVGPath;
    NearestNeighbor nn;

    @Before
    public void setUp() throws Exception {

        CSVReader csvReader = new CSVReader("sprint2test.csv");
        ArrayList<Destination> breweryList = new ArrayList<Destination>(csvReader.getBreweryList());
        nn = new NearestNeighbor(breweryList, "2 Opt");


        SVGPath = "src/main/java/edu/csu2017fa314/T04/View/ColoradoMap.svg";
        disOb = nn.getNearestNeighborTrip();
        bsvg = new BuildSVG(disOb, SVGPath);
    }

}
