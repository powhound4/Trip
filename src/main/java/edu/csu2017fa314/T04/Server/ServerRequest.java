package edu.csu2017fa314.T04.Server;

public class ServerRequest {
    private String name = "";
    private String id = "";
    private String[] dests;
    private String[] units;
    private String[] optimization;
    
     /**
     * Constructs a ServerRequest from details received by Server.java
     * String name, id: name and id of a destination
     * String [] dests: an array of destinations
     * String [] units: the customer's chosen units of measure
     * String [] opt: the customer's chosen method of trip optimization
     */
    public ServerRequest(String name, String id, String[] dests, String [] units, String[] optimization){
        this.name = name;
        this.id = id;
        this.dests = dests;
        this.units = units;
        this.optimization = optimization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
     /**
     * Gets the user selected units for the ServerRequest
     * returns "miles" or "kilometers" at index 0 in the array"
     */
    public String getUnits(){
        String [] userUnits = this.units;
        return userUnits[0];
    }
    
    public String [] getDistUnits(){
        return this.units;
    }
    
    public String getOptimization(){
        return optimization[0];
    }

     /**
     * Creates a query string of the received destinations from the web
     * returns a string of destinations
     */    
    public String getDests(){
    String queryDest = "";
    String[] destArr = this.dests;
        for(int i = 0; i< destArr.length; i++){
            if(destArr[i].contains("'")){
                destArr[i]= destArr[i].replaceAll("\\'", "\\'\\'");
            }
            queryDest = getQueryDest(queryDest, destArr, i);
        }
        return queryDest;
    }
    
     /**
     * Concatenates a string full of destinations
     * String [] destArr: an array of dests from the web
     * int i: the current index in the destArr array
     * returns a String queryDest, a concatenated String
     */
    private String getQueryDest(String queryDest, String[] destArr, int i) {
        if(i<destArr.length-1){
            queryDest+= destArr[i] + "', '";
        }
        else{
            queryDest += destArr[i];
        }
        return queryDest;
    }
    
    @Override
    public String toString() {
        return "Request{"
                + "dests='" + getDests() + '\''
                + ", name='" + name + '\''
                + ", id='" + id + '\''
                + ", units='" + getUnits() + '\''
                + '}';
    }
}

