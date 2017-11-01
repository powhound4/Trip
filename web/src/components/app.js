import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';
import Svg from './Home/Svg/Svg.jsx';

export default class App extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            svgImage: [],
            allPairs: [],
            sysFile: [],
            vals: [],
            serverReturned:null,
            selectedColumns: "",
            list: [],
            units: ["miles"],
            checked: false,
            tDist: 0;
            
            //add all labels
        }
    };

    render() {
        let serverDestinations;
        let dest;
        let svg = this.state.svgImage;
        let si = svg.map((s) => {
            return <Svg {...s}/>;
        });
        let pairs = this.state.allPairs;
        let ps = pairs.map((pp) => {
            return <Pair {...pp}/>;
        });
        if (this.state.serverReturned) { // if this.state.serverReturned is not null
            //for stage 2
            //Get list of numbers
            //serverLocations = this.state.serverReturned.locations;
          
            /*Create an array of HTML list items. The Array.map function in Javascript passes each individual element
            * of an array (in this case serverLocations is the array and "location" is the name chosen for the individual element)
            * through a function and returns a new array with the mapped elements.
            * In this case f: location -> <li>location.name</li>, so the array will look like:
            * [<li>[name1]</li>,<li>[name2]</li>...]
            */
            /*locs = serverLocations.map((location) => {
                return <li>{location.name}</li>;
            });

            // set the local variable scg to this.state.serverReturned.svg
            svg = this.state.serverReturned.svg;*/
        }
        return (
            <div className="app-container">
                <Home
                    fetch={this.fetch.bind(this)}
                    svgImage={this.svgImage.bind(this)} //svgImage can be referd to in home
                    svg={si}
                    browseFile={this.browseFile.bind(this)}
                    browseInfoFile={this.browseInfoFile.bind(this)}
                    pairs={ps}
                    totalDist = {this.tDist} //totalDist can be referenced in Home.jsx via this.props.totalDist
                    options = {this.idVals}
                    dropdownvalues = {this.vals}
                    getColumns = {this.getColumns.bind(this)}
                    getUnits = {this.getUnits.bind(this)}
                    
                />
            </div>
        )
    }
    // This function sends `input` the server and updates the state with whatever is returned
    async fetch(input) {
        // Create object to send to server

        /*  IMPORTANT: This object must match the structure of whatever
            object the server is reading into (in this case Server) */
        let newMap = {
            name: input,
            id: "1",
        };
        try {
            // Attempt to send `newMap` via a POST request
            // Notice how the end of the url below matches what the server is listening on (found in java code)
            // By default, Spark uses port 4567
            let jsonReturned = await fetch(`http://localhost:4567/testing`,
                {
                    method: "POST",
                    body: JSON.stringify(newMap)
                });
            // Wait for server to return and convert it to json.
            let ret = await jsonReturned.json();
            // Log the received JSON to the browser console
            console.log("Got back ", JSON.parse(ret));
            // set the serverReturned state variable to the received json.
            // this way, attributes of the json can be accessed via this.state.serverReturned.[field]
            
            this.setState({
                serverReturned: JSON.parse(ret)
            });
            this.browseFile(this.state.serverReturned.destinations);
            this.svgImage(this.state.serverReturned.svg);
            let infoPath = require('../../info.json');
            this.browseInfoFile(this.state.serverReturned.destinations[0].b1Labels);
            
            
            // Print on console what was returned
            // Update the state so we can see it on the web
        } catch (e) {
            console.error("Error talking to server");
            console.error(e);
        }
    }
    
    async getColumns(list){
        console.log(list); //set to global
        this.setState({
            list: list
        });
    }

    async getUnits(units){
        console.log(units);
        this.setState({
            units: units
        });
    }

    async svgImage(file){
        console.log("Got File: ", file);
        let svg = [];
        let s = {source: file};
        svg.push(s);
        this.setState({
            svgImage: svg
        });
    }

    async browseFile(file) {
      
        console.log("Got file:", file);
        //For loop that goes through all pairs,
        let pairs = [];
        let totalDist = 0;
        for (let i = 0; i < Object.values(file).length; i++) {
            let leg = i+1;
            let start = file[i].startName; //get start from file i
            let end = file[i].endName; //get end from file i
            let dist = file[i].totalDistance;
            totalDist+=dist;
            
            let startValues= [];
            let endValues = []; 
            let index = 0;
            let columns = [];
            
            let checked = false;
            
            if(this.state.list.length > 0){
            columns =
            this.state.list[this.state.list.length-1].split(',');
            console.log("this is columns: ", columns);
            }
         
            let b1 = "";
            let b2 = "";
        
            if(columns.length > 0 && columns[0] != ""){
                for(let j = 0; j < columns.length; j++){
                //loop through selected labels
                        
               let regex = new RegExp((columns[j]));
               
               //regex matching based on label, if it matches the pattern grab that info from the b1 or b2 info array
               
               for(let k = 0; k < file[i].b1Labels.length; k++){
                        //loop through all the labels
                       if(regex.test(file[i].b1Labels[k])){
                           index = k;
                       }
                  
                           let label = file[i].b1Labels[index];
                           label = label.charAt(0).toUpperCase() + label.slice(1) + "\n";
                           if(file[i].b1Info[index] == null){
                           b1 = label + ": Not Available";
                           }
                           else{
                           b1 = label + ": " + file[i].b1Info[index];
                           }
                           if(file[i].b2Info[index] == null){
                               b2 = label + ": Not Available";
                           }else{
                               
                           
                           b2 = label + ": " + file[i].b2Info[index];
                           }

                } 
               startValues.push(b1);
               endValues.push(b2);     
             
                               
                }
            }

            let p = { //create object with start, end, and dist variable
                leg: leg,
                start: start,
                end: end,
                dist: dist,
                totalDist: totalDist,
                startValues: startValues,
                endValues: endValues,
                checked: checked
                //add all values
            };
                       
            pairs.push(p); //add object to pairs array
           
            console.log("Pushing pair: ", p); //log to console
        }
        this.tDist = totalDist; //set tDist to the cummulative distance
        
        //Here we will update the state of app.
        // Anything component (i.e. pairs) referencing it will be re-rendered
        this.setState({
            allPairs: pairs,
            sysFile: file
        });
    }
    
    async browseInfoFile(file){
            let idValues = [];
            console.log("Got file!!:", file);
            for(var i=0; i < file.length; i++){
                console.log("this thing: ",(file[i]));
                let v = {
                    value: file[i],
                    label: file[i]
                };
                idValues.push(v);
            }
        console.log("idValues: ", idValues);
        this.vals = idValues;
            this.setState({
                idValues: this.state.vals});
    }
        
}
