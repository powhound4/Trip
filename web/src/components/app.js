import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';
import Svg from './Home/Svg/Svg.jsx';

export default class App extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
	    allCodes: [],
            route: [],
            sysFile: null,
            svgImage: [],
            allPairs: [],
            sysFile: [],
            vals: [],
            serverReturned:null,
            selectedColumns: "",
            list: [],
            units: ["miles"],
            tDist: 0,
            res: [],
            destList: [],
            allResults: [],
            optimization: ["2 Opt"],
            optimizationOptions: [{label: "No Optimization", value: "In Order"}, {label: "Nearest Neighbor", value: "Nearest Neighbor"}, {label:"2 Opt", value: "2 Opt"}, {label:"3 Opt", value: "3 Opt"}]
            
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
        
        return (
            <div className="app-container">
                <Home
		    allCodes={this.state.allCodes}
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
                    resultList={this.state.res}
                    setDests={this.getDests.bind(this)}
                    allResults={this.state.allResults}
                    optimizationOptions={this.state.optimizationOptions}
                    optimization={this.state.optimization}
                    browseUploadedFile={this.browseUploadedFile.bind(this)}
                    getCords={this.state.route}
                    getDestList={this.state.destList}
                    
                />
            </div>
        )
    }
   async fetch(type, input) {
        // Create object to send to server

        /*  IMPORTANT: This object must match the structure of whatever
            object the server is reading into (in this case Server) */
        console.log("type = "+ type + ", input= "+ input);
        let newMap;
        if(type === "initial"){
            newMap = {
                name: input,
                dests: this.state.destList,
                id: "0",
                units: this.state.units, 
                optimization: this.state.optimization
            };
        }
        
         if(type === "query"){
             console.log("destList state = ", this.state.destList);
            newMap = {
                name: input,
                dests: this.state.destList,
                id: "1",
                units: this.state.units,
                optimization: this.state.optimization                
            };
        }
       if(type === "upload"){
             newMap = {
                 name: "",
                 dests: input,
                 id: "2",
                 units: this.state.units,
                 optimization: this.state.optimization                
             };
         }
        
        console.log("Json to string = " + JSON.stringify(newMap));
        try {
            // Attempt to send `newMap` via a POST request
            // Notice how the end of the url below matches what the server is listening on (found in java code)
            // By default, Spark uses port 4567
		let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":4567/testing";
             console.log("serverURL = ", serverUrl);
            let jsonReturned = await fetch(serverUrl,
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
            if(this.state.serverReturned.id == "0"){
                 this.listResults(this.state.serverReturned.searchResults);
            }
            
            else if(this.state.serverReturned.id == "1" || this.state.serverReturned.id == "2"){
                this.browseFile(this.state.serverReturned.destinations);
                this.buildRoute(this.state.serverReturned.destinations);
                this.svgImage(this.state.serverReturned.svg);
                this.browseInfoFile(this.state.serverReturned.destinations[0].b1Labels);
            }
            
            
            // Print on console what was returned
            // Update the state so we can see it on the web
        } catch (e) {
            console.error("Error talking to server");
            console.error(e);
        }
    }
   async listResults(results){
        console.log("Search Results", results);
        let allTempRes = [];
        let resultNames = [];
        let codes = [];
        for (let i = 0; i < Object.values(results).length; i++) {
            console.log("Name:", results[i]);
            let temp = results[i].split(',');
            let name = temp[0];
            let code = temp[1];
            let r= {
                label : name,
                value : name
            };
           
            allTempRes.push(name);
            console.log("Pushing Name: ", r);
            resultNames.push(r);
            codes.push(code);

        }
        console.log("allTempResults = ", allTempRes);
        console.log("Result Names = ", resultNames);
        
        this.setState({
            res: resultNames,
            allResults: allTempRes,
            allCodes: codes
        });
        
        
    }
    async getDests(list){
        //console.log("DestList item = ", list);
        //console.log("list length = ", list.length);

       let temp = list.split(',');
       let tempDest = temp[temp.length-1];

       //console.log("temp = ", temp);
       //console.log("tempDest = ", tempDest);

       if(temp.length > 0){
           //console.log("temp length greater than 1", temp.length);
         this.state.destList.push(tempDest)
       }
       else{
       this.setState({
           destList: list
    });
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


    async buildRoute(file){
        console.log("Got file:", file);
        let r = [];
        for (let i = 0; i < Object.values(file).length; i++) {
            let lat = parseFloat(file[i].lat1);
            let lng = parseFloat(file[i].long1);
            let line = {
              lat: lat,
              lng: lng
            };
            r.push(line);
		if(i == Object.values(file).length -1 ){
			let lat = parseFloat(file[i].lat2);
       	   		let lng = parseFloat(file[i].long2);
            		let line = {
              			lat: lat,
             			lng: lng
          		};
            		r.push(line);
		}
        }
	console.log("route = ", r);
        this.setState({
            route: r
        });
    }
    async browseUploadedFile(file) {
         console.log("Got file:", file);
         this.setState({
             sysFile: file
         })
         this.fetch("upload", this.state.sysFile.destinations);
     }

    async browseFile(file) {
      
        console.log("Got file:", file);
        //For loop that goes through all pairs,
        let pairs = [];
        let totalDist = 0;
	let dist = 0;
        for (let i = 0; i < Object.values(file).length; i++) {
            let leg = i+1;
            let start = file[i].startName; //get start from file i
            let end = file[i].endName; //get end from file i
		
            if (this.state.units[0] == "miles"){
                dist = file[i].totalDistanceM;
                totalDist+=dist;
            }
            else{
                dist = file[i].totalDistanceK;
                totalDist+=dist;
            }
            
            let startValues= [];
            let endValues = []; 
            let index = 0;
            let columns = [];
            
            if(this.state.list.length > 0){
                columns = this.state.list[this.state.list.length-1].split(',');
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
                           break;
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
            for(let i=0; i < file.length; i++){
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
