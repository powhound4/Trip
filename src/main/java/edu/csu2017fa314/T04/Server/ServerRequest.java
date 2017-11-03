package edu.csu2017fa314.T04.Server;

public class ServerRequest {
    private String name = "";
    private String id = "";
    private String[] dests;
    private String[] units;
    
    
    public ServerRequest(String name, String id, String[] dests, String [] units){
        this.name = name;
        this.id = id;
        this.dests = dests;
        this.units = units;
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
    
    public String getUnits(){
        String u = "";
        String [] chosenUnits = this.units;

        if(chosenUnits.length > 1){
            u += u + chosenUnits[0] + ", " + chosenUnits[1];
        }
        else{
            u = chosenUnits[0];
        }
        return u;
    }
    
    public String getDests(){
    String queryDest = "";
    String[] destArr = this.dests;
        for(int i = 0; i< destArr.length; i++){
            if(destArr[i].contains("'")){
             //System.out.println("found apostrophe in "+destArr[i]);
                destArr[i]= destArr[i].replaceAll("\\'", "\\'\\'");
              //  System.out.println("found apostrophe in "+destArr[i]);
            }
            if(i<destArr.length-1){
                queryDest+= destArr[i] + "', '";
            }
            else{
                queryDest += destArr[i]; 
            }
        }
        return queryDest;
    }
    @Override
    public String toString() {
        return "Request{" +
                "dests='" + dests + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}

