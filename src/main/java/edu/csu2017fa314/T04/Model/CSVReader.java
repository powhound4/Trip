
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {

	private BufferedReader br = null;
	private String csvFile = "/data/brews.csv";
	private String line = "";
	private String delim = ",";
	private String[] labels = null;
	private int indexId, indexName, indexCity, indexLatitude, indexLongitude, indexElevation; //indexOf

	private Brewery brew = null;
	private ArrayList<Brewery> brewList = new ArrayList<Brewery>();
	
	public CSVReader(){
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			//get first line with all labels
			line = br.readLine();
			labels = line.split(delim);
			for(int i = 0; i < labels.length; i++){
				labels[i] = labels[i].toLowerCase().trim();
			}
			
			indexId = Arrays.asList(labels).indexOf("id");
			indexName = Arrays.asList(labels).indexOf("name");
			indexCity = Arrays.asList(labels).indexOf("city");
			indexLatitude = Arrays.asList(labels).indexOf("latitude");
			indexLongitude = Arrays.asList(labels).indexOf("longitude");
			indexElevation = Arrays.asList(labels).indexOf("elevation");
			if(indexElevation == -1){
				indexElevation = Arrays.asList(labels).indexOf("elevationft");
			}
			
			while ((line = br.readLine()) != null) {		 
				// use comma as separator
	            String[] values = line.split(delim);
	            brew = new Brewery(values[indexId], values[indexName], values[indexCity], values[indexLatitude], values[indexLongitude], values[indexElevation]);
	            brewList.add(brew);
			}
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	    	if (br != null) {
	       		try {
	       			br.close();
	       			} catch (IOException e) {
	       				e.printStackTrace();
	       				}
			    }
			}
	}
	
	public ArrayList<Brewery> getBreweryList(){
		return brewList;
	}
	
	
}
	
