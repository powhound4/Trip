package edu.csu2017fa314.T04.Server;

public class ServerRequest {
    private String name = "";
    private String id = "";

    public ServerRequest(String name, String id) {
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
