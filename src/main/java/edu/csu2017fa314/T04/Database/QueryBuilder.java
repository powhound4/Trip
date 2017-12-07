package edu.csu2017fa314.T04.Database;
 
 import edu.csu2017fa314.T04.Model.Destination;
 import edu.csu2017fa314.T04.Model.Info;
 import edu.csu2017fa314.T04.Model.Itinerary;
 import edu.csu2017fa314.T04.View.distanceObject;
 
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

    /**
    * Inputs a search term into a query
    * Returns the name and code of all the destinations, which match the search
    */
    public ArrayList<String> queryTerm(String word){
        ArrayList<String> names = new ArrayList<String>();
        String myDriver = "com.mysql.jdbc.Driver"; // Add dependencies in pom.xml
        String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314"; 
	 // Use this line if connecting inside CSU's network
         //String myUrl = "jdbc:mysql://localhost/cs314"; 
	 // Use this line if tunneling 3307 traffic through shell
         try { // Connect to the database
             Class.forName(myDriver);
             Connection conn = DriverManager.getConnection(myUrl, user, pass);
             try { // Create a statement
                 Statement st = conn.createStatement();
                 try { // Submit a query
                     ResultSet rs = st.executeQuery(word);
                     System.out.println("Submiting Query: " + word);
                    
                     try { // Iterate through the query results and print selected columns
                         while(rs.next()) {
                             names.add(rs.getString("name") + "," + rs.getString("code"));
                             String id = rs.getString("id");
                             String name = rs.getString("name");
                             System.out.printf("%s,%s\n", id, name);
                             
                        }
                        
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
         return names;
    }
 
     /**
     * Creates an ArrayList of distanceObjects for all of the
     * search-matching destinations
     * Takes a query and a user-chosen optimization method as input
     * Returns a filled distanceObject ArrayList to send back to the webpage
     */
     public ArrayList<distanceObject> query(String query, String optimization) { 
	     // Command line args contain username and password
     File queryFile = new File("web/World.svg");
		if(queryFile.exists()){
		queryFile.delete();
		}
        ArrayList<Destination> destinations = new ArrayList<>();
        ArrayList<distanceObject> trip = new ArrayList<>();
         String myDriver = "com.mysql.jdbc.Driver"; // Add dependencies in pom.xml
         String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314"; 
	 // Use this line if connecting inside CSU's network
         //String myUrl = "jdbc:mysql://localhost/cs314"; 
	 // Use this line if tunneling 3307 traffic through shell
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
                        System.out.println("Col Name: " + rsmd.getColumnLabel(i));
                        cols[i-1] = rsmd.getColumnLabel(i);
                     }
                     Info queryCols = new Info(cols);
                     try { // Iterate through the query results and print selected columns
                         while(rs.next()) {
                         ArrayList<String> data = new ArrayList<String>();
                            for(int i = 0; i < cols.length; ++i){
                                data.add(rs.getString(i+1));
                            }
                             String id = rs.getString("id");
                             String name = rs.getString("name");
                             System.out.printf("%s,%s\n", id, name);
                             destinations.add(new Destination(data, cols));
                        }
                        Itinerary it = new Itinerary(destinations, queryCols, optimization);
                        trip = it.buildItinerary();
                        System.out.println("this is in the queryBuilder class: " + optimization);
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

