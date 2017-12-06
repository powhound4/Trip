package edu.csu2017fa314.T04.Database;

import edu.csu2017fa314.T04.Database.QueryBuilder;
import edu.csu2017fa314.T04.View.distanceObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.ResultSetMetaData;
import java.io.File;

public class QueryBuilderTest {
    QueryBuilder qBuild;
    private String user = "";
    private String pass = "";
    private String qTest = "";
    private ArrayList<String> testNames = new ArrayList<String>();
    private ArrayList<distanceObject> testTrip = new ArrayList<distanceObject>();
    private String optimization = "2Opt";

    @Before
    public void setUp() throws Exception {
        user = "evansalz";
        pass = "830366797";
        qBuild = new QueryBuilder(user, pass);
        testNames.add("29130,Papa Westray Airport");
        testNames.add("29134,Westray Airport");
        testNames.add("11195,Strayhorn Ranch Airport");
        
        qTest += "SELECT airports.name, airports.code, airports.id, airports.municipality, regions.name, countries.name,"
            + "continents.name FROM continents INNER JOIN countries ON continents.code = countries.continent"
            + "INNER JOIN regions ON countries.code = regions.iso_country" 
            + "INNER JOIN airports ON regions.code = airports.iso_region WHERE countries.name"
            + "LIKE '%stray%' OR regions.name LIKE '%stray%' OR airports.name LIKE '%stray%'"
            + "OR airports.municipality LIKE '%stray%'"
            + "ORDER BY continents.name, countries.name, regions.name, airports.municipality, airports.name ASC";
    }

    @Test
    public void queryTerm() throws Exception {
        ArrayList<String> names = new ArrayList<String>();
        names = qBuild.queryTerm("stray");
        
        for (int i = 0; i < names.size(); i++){
            assertEquals(testNames.get(i), names.get(i));
        }
    }

    @Test
    public void query() throws Exception {

    }

}
