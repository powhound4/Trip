import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';
import Svg from './Home/Svg/Svg.jsx';

export default class App extends React.Component {
    constructor(props) {
        super(props);
        let tDist = 0; //holds the value of total distance
        this.state = {
            svgImage: [],
            allPairs: [],
            sysFile: []
        }
    };

    render() {
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
                    svgImage={this.svgImage.bind(this)} //svgImage can be referd to in home
                    svg={si}
                    browseFile={this.browseFile.bind(this)}
                    pairs={ps}
                    totalDist = {this.tDist} //totalDist can be referenced in Home.jsx via this.props.totalDist
                />
            </div>
        )
    }
    async svgImage(file){
        console.log("Got file:", file);
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
