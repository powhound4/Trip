package edu.csu2017fa314.T04.Server;

public class ServerRequest {
    private String name = "";
    private String id = "";
    private String dests = "";
    
    
    public ServerRequest(String name, String id, String dests){
        this.name = name;
        this.id = id;
        this.dests = dests;
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
    
    public String getDests(){
    String queryDest = "";
    String[] destArr = this.dests.split(",");
        for(int i = 0; i< destArr.length; i++){
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
                '}';
    }
}

