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
	

	public BuildKML(ArrayList<distanceObject> disOb){
		this.route = disOb;
		writeKML();
	}

	
	private void writeKML(){
		//begin writing to new file
		//check to see if file exists if so delete and craete new
		File f = new File("web/Trip.kml");
		if(f.exists()){
		f.delete();
		}

		try{

			//bw = new BufferedWriter(new FileWriter("web/ColoradoSVGEdited.svg", true));
            bw = new BufferedWriter(new FileWriter("web/Trip.kml", true));
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ "\n");
            bw.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">"+ "\n");
            bw.write("<Document>"+ "\n");
            bw.write("<Placemark>\n");
            bw.write("<LineString>\n");
   			bw.write("<coordinates>\n");
   			for(int i =0; i< this.route.size(); i++){
                bw.write(route.get(i).long1 +","+ route.get(i).lat1 + "," + route.get(i).b1Info.get(9) + "\n");
                if(i == this.route.size()-1){
                    bw.write(route.get(i).long2 +","+ route.get(i).lat2 + "," + route.get(i).b2Info.get(9) + "\n");
                }
			}
            bw.write("</coordinates>" + "\n" + "</LineString>" +"\n");
            bw.write("<Style>" + "\n"+ "<LineStyle>" + "\n"+ "<color>#ff0000ff</color>" + "\n"+ "<width>6</width>" + "\n"+ "</LineStyle>" + "\n"+ "</Style>" + "\n"+ "</Placemark>\n");



            //bw.write("<name>Trip</name>" + "\n");
			for(int i =0; i< this.route.size(); i++){
			//bw.write("<LookAt>\n<longitude>" +  route.get(i).long1 + "</longitude>\n<latitude>" + route.get(i).lat1 + "</latitude>\n<altitude>0</altitude>\n</LookAt>\n");
                bw.write("<Placemark>\n");
                bw.write("<Style>\n<IconStyle>\n<Icon>\n<href>http://maps.google.com/mapfiles/kml/pushpin/pink-pushpin.png\n</href>\n</Icon>\n</IconStyle>\n</Style>\n");
				bw.write("<name>"+ route.get(i).startName + "</name>" + "\n");
				bw.write("<description>Code: " + route.get(i).b1Info.get(2) + "</description>" + "\n");
				bw.write("<Point>" + "\n");
                bw.write("<coordinates>" + route.get(i).long1 +","+ route.get(i).lat1 + "," + 0 + "</coordinates>" + "\n");
                bw.write("</Point>" + "\n");
                bw.write("</Placemark>" + "\n");
				
			}
			
			
            bw.write("</Document>"+ "\n");
			bw.write("</kml>");
            bw.close();
			

        }
			// Close connection
			
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	


}

