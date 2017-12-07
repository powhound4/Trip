package edu.csu2017fa314.T04.Model;

import edu.csu2017fa314.T04.View.distanceObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NearestNeighborTest {
    NearestNeighbor nn;
    NearestNeighbor nn2;
    private String[] labels;
    private ArrayList<String> info1;
    private ArrayList<String> info2;
    private ArrayList<String> info3;

    private ArrayList <Destination> locations = new ArrayList<>();



    @Before
    public void setUp() throws Exception {
        labels = new String[]{"name", "latitude", "longitude", "id", "country"};

        info1 = new ArrayList<>(Arrays.asList("Papa Westray Airport","59.351699829100006","-2.9002799987800003", "United Kingdom"));
        info2 = new ArrayList<>(Arrays.asList( "Westray Airport", "59.3502998352", "-2.95000004768", "United Kingdom" ));
        info3 = new ArrayList<>(Arrays.asList( "Strayhorn Ranch Airport","26.662799835205078", "-81.77059936523438", "United States"));
        Destination d1 = new Destination(info1, labels);
        Destination d2 = new Destination(info2, labels);
        Destination d3 = new Destination(info3, labels);

        locations.add(d1);
        locations.add(d2);
        locations.add(d3);

    nn = new NearestNeighbor(locations, "Nearest Neighbor");
    nn2 = new NearestNeighbor(locations, "2 Opt");
        //   info = new ArrayList<>(Arrays.asList("Fort Collins Loveland Municipal Airport", "40.4518013", "-105.0110016", "KFNL", "FoCo"));
    }

    @Test public void getOptimization(){

        assert(nn.optimization.equals("Nearest Neighbor"));
        assert(nn2.optimization.equals("2 Opt"));
    }

    @Test
    public void getNearestNeighborTrip() {
        int [] trip =  {0, 1, 2 ,0};

        nn.getNearestNeighborTrip();
        System.out.println(Arrays.toString(nn.getBestTrip()));
        
        for(int i = 0; i < trip.length; i++){
          
            assertEquals(trip[i],nn.getBestTrip()[i]);
        }
        
        System.out.println(Arrays.toString(nn2.getBestTrip()));

        nn2.getNearestNeighborTrip();
        System.out.println(Arrays.toString(nn2.getBestTrip()));
        for(int i = 0; i < trip.length; i++){

            assertEquals(trip[i], nn2.getBestTrip()[i]);
        }
    }

    @Test
    public void twoOpt(){
        int [] trip =  {0, 1, 2 ,0};
        nn.getNearestNeighborTrip();
        nn.twoOpt(nn.getBestTrip());
        for(int i = 0; i < trip.length; i++){
            assert(trip[i] == nn.getBestTrip()[i]);
        }
    }
    
       @Test
    public void threeOpt(){
        int [] trip =  {0, 1, 2 ,0};
        nn.getNearestNeighborTrip();
      
        nn.threeOpt(nn.getBestTrip());
    
        for(int i = 0; i < trip.length; i++){
            assert(trip[i] == nn.getBestTrip()[i]);
        }
    }

    @Test
    public void calcShortestTrip(){

        int [] trip =  {0, 1, 2 ,0};
        int [] testTrip = new int[4];
        testTrip= nn.calcShortestTrip();

        for(int i = 0; i < trip.length; i++){
            assertEquals(testTrip[i], trip[i]);
        }
    }

    @Test
    public void getTotalDistanceM(){
        ArrayList<distanceObject> trip = nn2.getNearestNeighborTrip();
        assertEquals(8527, nn2.getTotalDistanceM());
    }

    @Test
    public void calculateDistance(){
        nn.getNearestNeighborTrip();
        int [] testTrip= nn.getBestTrip();
        assertEquals(8527, nn.calculateDistance(testTrip));

    }

    @Test
    public void fillInMap(){
        int[][] distArray = {{2147483647, 2, 4263}, {2, 2147483647, 4262}, {4263, 4262, 2147483647}};

        nn.fillInMap();

        for(int i = 0; i < distArray.length; i++){
            for(int j =0; j < distArray.length; j++){
                assertEquals(distArray[i][j], nn.getDisTable()[i][j]);
            }
        }
    }

    @Test
    public void twoOpt1(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.twoOpt1(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1]));
    }
    
    @Test
    public void twoOpt2(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.twoOpt2(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1], nn.getBestTrip()[2]));
    }

    @Test
    public void threeOpt1(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.threeOpt1(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1], nn.getBestTrip()[2]));
    }

    @Test
    public void threeOpt2(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.threeOpt2(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1], nn.getBestTrip()[2]));
    }

    @Test
    public void threeOpt3(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.threeOpt3(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1], nn.getBestTrip()[2]));
    }

    @Test
    public void threeOpt4(){
        nn.getNearestNeighborTrip();
        assertEquals(0, nn.threeOpt4(nn.getBestTrip(), nn.getBestTrip()[0], nn.getBestTrip()[1], nn.getBestTrip()[2]));
    }

 /*   @Test

    public void disObjectify(ArrayList<Destination> orderedLocations){
        nn2.getNearestNeighborTrip();
        ArrayList<distanceObject> compare = new ArrayList<distanceObject>(locations.size() +1);
        ArrayList<Destination> orderedDestinations = new ArrayList<>(locations.size());
        for(int i = 0; i < locations.size(); i++){
            orderedDestinations.add(locations.get(nn.getBestTrip()[i]));
        }
        for(int i = 0; i < compare.size(); i++{
            assertEquals(compare.get(i), )
        }
        disObjectify(orderedDestinations);
    }*/
   /* @Test
    public void getNearestNeighborTrip() throws Exception {
        ArrayList<distanceObject> trip = nn.getNearestNeighborTrip();
        assertEquals(926, nn.getTotalDistanceM());
    }*/


}
