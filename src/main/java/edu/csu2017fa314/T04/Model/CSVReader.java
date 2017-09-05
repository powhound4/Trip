import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVReader {

	public static void main(String[] args) {

	String csvFile = "brews.csv";
	BufferedReader br = null;
	String line = "";
	String delim = ",";
	String[] labels = null;
	int indexId, indexName, indexCity, indexLatitude, indexLongitude, indexElevationFt; //indexOf

	Brewery brew = null;
	ArrayList<Brewery> brewList = new ArrayList<Brewery>();
	
	try {
		br = new BufferedReader(new FileReader(csvFile));

		
		//get first line with all labels
		line = br.readLine();
		labels = line.split(delim);
		for(int i = 0; i < labels.length; i++)
			labels[i] = labels[i].toLowerCase();
		
		indexId = Arrays.asList(labels).indexOf("id");
		indexName = Arrays.asList(labels).indexOf("name");
		indexCity = Arrays.asList(labels).indexOf("city");
		indexLatitude = Arrays.asList(labels).indexOf("latitude");
		indexLongitude = Arrays.asList(labels).indexOf("longitude");
		indexElevationFt = Arrays.asList(labels).indexOf("elevationft");

		/*
		System.out.println(indexId);
		System.out.println(indexName);
		System.out.println(indexCity);
		System.out.println(indexLatitude);
		System.out.println(indexLongitude);
		System.out.println(indexElevationFt);
		*/
		
		
		while ((line = br.readLine()) != null) {		 
			// use comma as separator
            String[] values = line.split(delim);
            brew = new Brewery(values[indexId], values[indexName], values[indexCity], values[indexLatitude], values[indexLongitude], values[indexElevationFt]);
            brewList.add(brew);
            
            
           /* for(int i = 0; i < values.length; i++)
    			System.out.print(values[i] + " ");
            System.out.println();
            */
		}

	} catch (FileNotFoundException e) {
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
		System.out.println(brewList.size());
		System.out.println(brewList.get(3).getName());
	}
}
	