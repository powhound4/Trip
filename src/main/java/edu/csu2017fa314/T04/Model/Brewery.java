
public class Brewery {

	protected String id,name,city,latitude,longitude,elevationFt;

	public Brewery(){
	}
	
	public Brewery(String id, String name, String city, String latitude, String longitude, String elevationFt){
		this.id = id;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevationFt = elevationFt;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getLatitude(){
		return latitude;
	}
	
	public String getLongitude(){
		return longitude;
	}
	
	public String getElevation(){
		return elevationFt;
	}
	
	public Brewery getNextBrewery(){
		return this;//FIXME
	}
	
	public static void main(String[] args) {
		

	}

}
