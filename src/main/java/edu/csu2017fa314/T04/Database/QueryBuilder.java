package edu.csu2017fa314.T04.Database;
 
 //import edu.csu2017fa314.T04.Server.Location;
 import edu.csu2017fa314.T04.Model.*;
 import edu.csu2017fa314.T04.View.*;
 
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.Statement;
 import java.util.ArrayList;
  import java.util.Arrays;
  import java.sql.ResultSetMetaData;
  import java.io.File;
 
 public class QueryBuilder {
     private String user = "";
     private String pass = "";
 
     public QueryBuilder(String user, String pass) {
         this.user = user;
         this.pass = pass;
     }
 //cahnge back to ArrayList<Destination>
     public ArrayList<distanceObject> query(String query) { // Command line args contain username and password
     File f = new File("web/ColoradoSVGEdited.svg");
		if(f.exists()){
		f.delete();
		}
        ArrayList<Destination> destinations = new ArrayList<>();
        ArrayList<distanceObject> trip = new ArrayList<>();
         String myDriver = "com.mysql.jdbc.Driver"; // Add dependencies in pom.xml
         String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314"; // Use this line if connecting inside CSU's network
         //String myUrl = "jdbc:mysql://localhost/cs314"; // Use this line if tunneling 3307 traffic through shell
         try { // Connect to the database
             Class.forName(myDriver);
             Connection conn = DriverManager.getConnection(myUrl, user, pass);
             try { // Create a statement
                 Statement st = conn.createStatement();
                 try { // Submit a query
                     ResultSet rs = st.executeQuery(query);
                     System.out.println("Submiting Query: " + query);
                     //george added here
                     ResultSetMetaData rsmd = rs.getMetaData();
                     System.out.println("Col Count: " + rsmd.getColumnCount());
                     String[] cols = new String[rsmd.getColumnCount()];
                     for(int i = 1; i <= cols.length; ++i){
                        System.out.println("Col Name: " + rsmd.getColumnName(i));
                        cols[i-1] = rsmd.getColumnName(i);
                     }
                     Info queryCols = new Info(cols);
                     try { // Iterate through the query results and print selected columns
                         while(rs.next()) {
                         ArrayList<String> data = new ArrayList<String>();
                            for(int i = 0; i < cols.length; ++i){
                                data.add(rs.getString(cols[i]));
                            }
                             String id = rs.getString("id");
                             String name = rs.getString("name");
                             System.out.printf("%s,%s\n", id, name);
                             destinations.add(new Destination(data, cols));
                        }
                        Itinerary it = new Itinerary(destinations, queryCols);
                        trip = it.buildItinerary();
                     } finally {
                         rs.close();
                     }
                 } finally {
                     st.close();
                 }
             } finally {
                 conn.close();
             }
         } catch(Exception e) { // Catches all exceptions in the nested try's
             System.err.printf("Exception: ");
             System.err.println(e.getMessage());
         }
         return trip;
     }
 } 

