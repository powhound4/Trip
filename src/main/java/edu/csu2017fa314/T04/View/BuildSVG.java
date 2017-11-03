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
	
	private int[] scale2WorldSVG(double latitude, double longitude){
		int northLong = -180;
		int northLat = 90;
		double scaleH = 2.84444444;
		double scaleW = 2.84444444;
		double tempLat = northLat - latitude;
		double tempLong = longitude - northLong;
		double svgH = (tempLat * scaleH);
		double svgW = (tempLong * scaleW);
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
			//PrintWriter pw = new PrintWriter(new FileWriter("web/ColoradoSVGEdited.svg"));
            PrintWriter pw = new PrintWriter(new FileWriter("web/World.svg"));

			String line = br.readLine();
			pw.println(line);//xml
			pw.println("<svg width=\"1024\" height=\"512\" xmlns=\"http://www.w3.org/2000/svg\">");
			//pw.println("<svg width=\"1066.6073\" height=\"783.0824\" xmlns=\"http://www.w3.org/2000/svg\">");
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

			//bw = new BufferedWriter(new FileWriter("web/ColoradoSVGEdited.svg", true));
            bw = new BufferedWriter(new FileWriter("web/World.svg", true));
			for(int i =0; i< this.route.size(); i++){
				int [] loc1 = scale2WorldSVG(route.get(i).toDecimal(route.get(i).lat1), route.get(i).toDecimal(route.get(i).long1));
				
				int [] loc2 = scale2WorldSVG(route.get(i).toDecimal(route.get(i).lat2), route.get(i).toDecimal(route.get(i).long2));
				
				
				int [] coordinate = toMapWrap(loc1, loc2);
				System.out.println("Coor length = " + coordinate.length);
				
				if(coordinate.length == 4){
				
				//int [] loc1 = scale2CoSVG(route.get(i).toDecimal(route.get(i).lat1), route.get(i).toDecimal(route.get(i).long1));
				//int [] loc2 = scale2CoSVG(route.get(i).toDecimal(route.get(i).lat2), route.get(i).toDecimal(route.get(i).long2));

				bw.write("<circle cx=\"" + coordinate[1] + "\" cy=\"" + coordinate[0] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>" + "\n");
				
				bw.write("<circle cx=\"" + coordinate[3] + "\" cy=\"" + coordinate[2] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>"+ "\n");
				
				bw.write("<line x1=\"" + coordinate[1] + "\" y1=\"" + coordinate[0] + "\" x2=\"" + coordinate[3] + "\" y2=\"" + coordinate[2] + "\" style=\"" + style + "\"/>"+ "\n");
				}
				else{
				
				bw.write("<circle cx=\"" + coordinate[1] + "\" cy=\"" + coordinate[0] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>" + "\n");
				
				//bw.write("<circle cx=\"" + coordinate[3] + "\" cy=\"" + coordinate[2] + "\" stroke=\"" + stroke + "\" stroke-width=\"" + strokeWidth + "\" fill=\"" + stroke + "\"/>"+ "\n");
				
				bw.write("<line x1=\"" + coordinate[1] + "\" y1=\"" + coordinate[0] + "\" x2=\"" + coordinate[5] + "\" y2=\"" + coordinate[2] + "\" style=\"" + style + "\"/>"+ "\n");
				
				bw.write("<line x1=\"" + coordinate[5] + "\" y1=\"" + coordinate[3] + "\" x2=\"" + coordinate[5] + "\" y2=\"" + coordinate[4] + "\" style=\"" + style + "\"/>"+ "\n");
				}
				
				}
				bw.write("</svg>");
				bw.close();
		
			}
			
			
			


			// Close connection
			
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	private int[] toMapWrap(int[] a, int[] b){
        int [] cord1 = a;
        int [] cord2 = b;
        int [] resCord;
        //cord1 is the futhest right coordinate
        if(a[1] < b[1]){
            cord1 = b;
            cord2 = a;
        }
        int distToRightEdge = 1024 - cord1[1];
        int distToLeftEdge = cord2[1];
        
        int distNorm = (int)Math.sqrt(Math.pow(cord1[1]-cord2[1],2) + Math.pow(cord1[0]-cord2[0], 2));
        int distWrapped = (int)Math.sqrt(Math.pow(cord1[1]-cord2[1],2) + Math.pow(distToRightEdge + distToLeftEdge, 2));
        
        if(distNorm <= distWrapped){
        resCord = new int[4];
            resCord[0] = a[0];//a lat
            resCord[1] = a[1];//a long
            resCord[2] = b[0];//b lat
            resCord[3] = b[1];//b long
            return resCord;
        }
        else{
        resCord = new int[6];
            resCord[0] = cord1[0];//furthest right x
            resCord[1] = cord1[1];//y stay same
            resCord[2] = cord1[0] + distToLeftEdge + distToRightEdge;//resCord[4] = point right off map 1024 + distance to left edge
            resCord[3] = cord2[0] - distToLeftEdge - distToRightEdge;// 0 - distToRightEdge
            resCord[4] = cord2[0];//left x
            resCord[5] = cord2[1];// y stay same
            return resCord;
        }
        
	}


}

