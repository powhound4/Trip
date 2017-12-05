package edu.csu2017fa314.T04.Database;

import edu.csu2017fa314.T04.Database.QueryBuilder;
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
    private ArrayList<String> names = new ArrayList<String>();

    @Before
    public void setUp() throws Exception {
        user = "evansalz";
        pass = "830366797";
        qBuild = new QueryBuilder(user, pass);
    }

    @Test
    public void queryTerm() throws Exception {

    }

    @Test
    public void query() throws Exception {

    }

}
