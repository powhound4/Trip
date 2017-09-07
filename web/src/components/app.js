import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';


export default class App extends React.Component {
    constructor(props) {
        super(props);
        let tDist = 0; //holds the value of total distance
        this.state = {
            allPairs: [],
            sysFile: []
        }
    };

    render() {
        let pairs = this.state.allPairs;
        let totDis = this.tDist; //totDis holds the value of total Distance
        let ps = pairs.map((pp) => {
            return <Pair {...pp}/>;
        });
        return (
            <div className="app-container">
                <Home
                    browseFile={this.browseFile.bind(this)}
                    pairs={ps}
                    totalDist = {totDis} //totalDist can be referenced in Home.jsx via this.props.totalDist
                />
            </div>
        )
    }

    async browseFile(file) {
        console.log("Got file:", file);
        //For loop that goes through all pairs,
        let pairs = [];
        let totalDist = 0;
        for (let i = 0; i < Object.values(file).length; i++) {
            let start = file[i].start; //get start from file i
            let end = file[i].end; //get end from file i
            let dist = file[i].distance;
            totalDist+=dist;
            let p = { //create object with start, end, and dist variable
                start: start,
                end: end,
                dist: dist,
                totalDist: totalDist
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
        
}
