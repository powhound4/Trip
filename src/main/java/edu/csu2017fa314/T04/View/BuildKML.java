package edu.csu2017fa314.T04.View;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BuildKML {

	private ArrayList<distanceObject> route;
	private BufferedWriter bw = null;
	

	public BuildKML(ArrayList<distanceObject> disOb) {
		this.route = disOb;
		checkExistance();
		writeKml();
	}

	private void checkExistance() {
		//check to see if file exists if so delete and craete new
		File kmlFile = new File("web/Trip.kml");
		if(kmlFile.exists()) {
			kmlFile.delete();
		}
	}
	private void writeKml() {
		//begin writing to new file
		try{
            		bw = new BufferedWriter(new FileWriter("web/Trip.kml", true));
            		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
            		bw.write("<Document>\n<Placemark>\n<LineString>\n<coordinates>\n");
   			for(int i =0; i< this.route.size(); i++){
                		bw.write(route.get(i).long1+","+route.get(i).lat1+","+route.get(i).b1Info.get(9)+"\n");
                		if(i == this.route.size()-1){
                    			bw.write(route.get(i).long2+","+route.get(i).lat2+","+route.get(i).b2Info.get(9)+"\n");
                		}
			}
           		bw.write("</coordinates>\n</LineString>\n");
            		bw.write("<Style>\n<LineStyle>\n<color>#ff0000ff</color>\n<width>6</width>\n</LineStyle>\n</Style>\n</Placemark>\n");
	    		for(int i =0; i< this.route.size(); i++){
               			bw.write("<Placemark>\n");
                		bw.write("<Style>\n<IconStyle>\n<Icon>\n<href>http://maps.google.com/mapfiles/kml/pushpin/pink-pushpin.png\n</href>\n</Icon>\n</IconStyle>\n</Style>\n");
				bw.write("<name>"+ route.get(i).startName + "</name>\n");
				bw.write("<description>Code: " + route.get(i).b1Info.get(2) + "</description>\n<Point>\n");
                		bw.write("<coordinates>" + route.get(i).long1 +","+ route.get(i).lat1 + ",0</coordinates>\n</Point>\n</Placemark>\n");
	  		 }
            		bw.write("</Document>\n</kml>\n");
            		bw.close();
        	}
		// Close connection		
		catch(Exception e){
			System.out.println(e);
		}
	}
}

