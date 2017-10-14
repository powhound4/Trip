package edu.csu2017fa314.T04.Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.io.*;

public class CSVReader {

	private BufferedReader br = null;
	private String csvFile;
	private String line = "";
	private String delim = ",";
	private String[] labels = null;
	//private int indexId, indexName, indexCity, indexLatitude, indexLongitude, indexElevation; //indexOf

	private Destination brew = null;
	private ArrayList<Destination> brewList = new ArrayList<Destination>();
	
	/*public int getLabelIndex(String lab){
        return Arrays.asList(labels).indexOf(lab);
	}*/
	
	public CSVReader(String file){
		try {
			csvFile = file;
			//System.out.println(new File(".").getAbsolutePath());
			br = new BufferedReader(new FileReader(csvFile));
			
			//get first line with all labels
			line = br.readLine();
			labels = line.split(delim);
			for(int i = 0; i < labels.length; i++){
				labels[i] = labels[i].toLowerCase().trim();
			}
			Info labelInfo = new Info(labels);
			labelInfo.writeInfo();
			/*if(indexElevation == -1){
				indexElevation = Arrays.asList(labels).indexOf("elevationft");
			}*/
			
			while ((line = br.readLine()) != null) {		 
				// use comma as separator
	            String[] values = line.split(delim);
	            if(values.length == 1){   //empty line
                   continue;
                }
                    
	            for(int i = 0; i < values.length; i++){
	            	values[i] = values[i].trim();
                }
                brew = new Destination(values, labels);
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
	
	public ArrayList<Destination> getBreweryList(){
		return brewList;
	}
	
	
}
	
