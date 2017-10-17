package edu.csu2017fa314.T04.View;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BuildSVG {

	private ArrayList<distanceObject> route;
	private String SVGpath;
	private BufferedWriter bw = null;


	//second argument (longitude) must be negative ex -104.9903
	//this is based on the colorado map provided in sprint2
	private int[] scale2CoSVG(double latitude, double longitude){
		int northLong = -109;
		int northLat = 41;
		double scaleH = 176.4625;
		double scaleW = 141.1714286;
		double tempLat = northLat - latitude;
		double tempLong = longitude - northLong;
		double svgH = (tempLat * scaleH) + 38.3;
		double svgW = (tempLong * scaleW) + 38.3;
		int[] svg = {(int) Math.round(svgH),(int) Math.round(svgW)}; //{lat,long}
		return svg;
	}

	public BuildSVG(ArrayList<distanceObject> disOb, String SVGPath){
		this.route = disOb;
		this.SVGpath = SVGPath;
		copySVG();
		writeSVG();
	}

	private void copySVG(){
		FileInputStream instream = null;
		FileOutputStream outstream = null;

		try{
			BufferedReader br = new BufferedReader(new FileReader(this.SVGpath));
			PrintWriter pw = new PrintWriter(new FileWriter("ColoradoSVGEdited.svg"));
			String line = br.readLine();
			pw.println(line);//xml
			pw.println("<svg width=\"1066.6073\" height=\"783.0824\" xmlns=\"http://www.w3.org/2000/svg\">");
			while ((line = br.readLine()) != null) {
				pw.println(line);
			}

			br.close();
			pw.close();

		}catch(IOException ioe){
			ioe.printStackTrace();
		}

	}

	private void writeSVG(){
		//begin writing to new file
		String stroke = "blue";
		String strokeWidth = "4";
		String style = "stroke:blue; stroke-width:2";

		try{

			bw = new BufferedWriter(new FileWriter("ColoradoSVGEdited.svg", true));
			for(int i =0; i< this.route.size(); i++){
				int [] loc1 = scale2CoSVG(route.get(i).toDecimal(route.get(i).lat1), route.get(i).toDecimal(route.get(i).long1));
				int [] loc2 = scale2CoSVG(route.get(i).toDecimal(route.get(i).lat2), route.get(i).toDecimal(route.get(i).long2));

				bw.write("<circle cx=\"" + loc1[1] + "\" cy=\"" + loc1[0] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>" + "\n");
				bw.write("<circle cx=\"" + loc2[1] + "\" cy=\"" + loc2[0] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>"+ "\n");
				bw.write("<line x1=\"" + loc1[1] + "\" y1=\"" + loc1[0] + "\" x2=\"" + loc2[1] + "\" y2=\"" + loc2[0] + "\" style=\"" + style + "\"/>"+ "\n");
			}
			bw.write("</svg>");


			// Close connection
			bw.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}


}

