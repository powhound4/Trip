import React, {Component} from 'react';
import Select from 'react-select';
import Dropzone from 'react-dropzone';


class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            inputValue: '',
            type: '',
            columnValues: [],
            selectedDests: [],
            selectedUnits: ["miles"],
            isCheckedM: true,
            isCheckedK: false,
            destVal:[]
        };

        this.handleFirstSubmit = this.handleFirstSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.logCheckM = this.logCheckM.bind(this);
        this.logCheckK = this.logCheckK.bind(this);
    }

    logChange(val) {
        this.setState({value: val}); //dropdown has selected value
        console.log(val);
        this.state.columnValues.push(val);
        this.props.getColumns(this.state.columnValues);

    }


    logCheckM(event){
        if (this.state.isCheckedM) {
            this.state.isCheckedM = false;
        }
        else {
            this.state.isCheckedM = true;
        }
        this.updateCheck();
    }

    logCheckK(event){
        if (this.state.isCheckedK) {
            this.state.isCheckedK = false;
        }
        else {
            this.state.isCheckedK = true;
        }
        this.updateCheck();
    }

    updateCheck(){
        if (!(this.state.isCheckedM) && (this.state.isCheckedK)){
            console.log("Only kilometers selected");
            this.state.selectedUnits = ["kilometers"];
        }
        else if ((this.state.isCheckedM) && (this.state.isCheckedK)){
            console.log("Kilometers and miles selected");
            this.state.selectedUnits = ["miles", "kilometers"];
        }
        else{
            console.log("Default of miles");
            this.state.selectedUnits = ["miles"];
        }
        this.props.getUnits(this.state.selectedUnits);

    }

    handleChange(event){
        this.setState({inputValue: event.target.value});
    }

    handleSubmit(event){
        console.log("Input Value = " + this.state.inputValue);
        console.log("Selected Dests = " +this.state.selectedDests);
        console.log('drop down values: ' + this.props.dropdownvalues);
        this.props.fetch("query", this.state.inputValue);
        event.preventDefault();
    }
    handleFirstSubmit(event){
        console.log("Input Value = " + this.state.inputValue);
        console.log("Selected Dests = " +this.state.selectedDests);
        console.log('drop down values: ' + this.props.dropdownvalues);
        this.props.fetch("initial", this.state.inputValue);
        event.preventDefault();
    }

    setSelectedDests(event){
        //console.log("State of destVal", this.state.destVal);
        for(let i = 0; i< this.state.selectedDests.length; i++){
            console.log("selected dests i = ",this.state.selectedDests[i]);
            this.props.setDests(this.state.selectedDests[i]);
        }

        //console.log('Selected Destinations' + this.state.selectedDests);
        this.props.fetch("query", this.state.inputValue);
        event.preventDefault();
    }

    logDest(val) {
        this.setState({destVal: val});
        console.log("destVal = ", val);
        this.state.selectedDests.push(val);
        event.preventDefault();
    }
    setOptimization(event){
        this.setState({optimization: event.target.value});
        event.preventDefault();
    }

    logOptimization(val){

        this.props.optimization.pop(0);
        this.props.optimization.push(val);
        console.log("this is the state var ", this.props.optimization);
        event.preventDefault();
    }

    runOptimization(event){
        console.log("State of destVal", this.state.destVal);
        this.props.setDests(this.state.destVal);
        console.log('Selected Destinations' + this.state.selectedDests);
        this.props.fetch("query", this.state.inputValue);
        event.preventDefault();

    }
     logSelectAll(event){
           
                for(let i = 0; i < this.props.allResults.length; i++){
                   console.log("resultList[i] = ", this.props.resultList[i].label);
                   this.state.selectedDests.push(this.props.allResults[i]);
                   this.state.destVal.push(this.props.resultList[i].value);
                }
            
    	}

    

    render() {
        //console.log("Result list = ", this.props.resultList);
        //console.log('Selected Destinations' + this.state.selectedDests);
        let resList = this.props.resultList;
        let values = this.props.dropdownvalues;
        let total = this.props.totalDist; //update the total here

        return <div className="home-container">
            <div className="separator">
            </div>
            <div className="inner">
                <div className="heading">
                    T04 - 4TheWin
                    <h1>Airport Tour With Beer</h1>
                </div>

                <center><div>
                   <h3>Upload a file</h3>
                  <Dropzone className="dropzone-style" onDrop={this.uploadFileClicked.bind(this)}>
                 <input className="btn btn-primary btn-md" type="submit" value="Upload a location file" />
 
                 </Dropzone>
                    <form className='search-form' onSubmit={this.handleFirstSubmit}>
                        <input className="SearchDest"
                               type="text"
                               placeholder="Search Destinations"
                               onChange={this.handleChange}/>
                        <br></br>
                        <input className="btn btn-primary btn-md" type="submit" value="Search" />
                        <br></br>
                    </form>


                    <center>
                        <h3>Selected Destinations</h3>
                        <form className="Drop-down-form" onSubmit={this.setSelectedDests.bind(this)}>
                            <div className = "Select-control">
                                <Select
                                    name="form-
  			field-name"
                                    value={this.state.destVal}
                                    multi={true}
                                    options={resList} //must be labeled label and value to work
                                    onChange={this.logDest.bind(this)}
                                    simpleValue
                                    searchable={false}
                                    placeholder = "Select Destinations"
                                    backspaceToRemoveMessage=""
                                />
                            </div>
                            <input className="btn btn-primary btn-md" type="submit" value="Build Itinerary" style={{margin:'5px', width:'40%'}}/>
                            <input className="btn btn-primary btn-md" type="button" value="Select All" onClick={this.logSelectAll.bind(this)} style={{margin:'5px', width:'40%'}}/>
                            <br></br>
                        </form>
                    </center>


                </div></center>

                <center>
                    <form className="Drop-down-form2" onSubmit={this.runOptimization.bind(this)}>
                        <div className = "Select-control">
                            <Select
                                name="form-
  			field-name"
                                value={this.state.optVal}
                                multi={false}
                                options={this.props.optimizationOptions} //must be labeled label and value to work
                                onChange={this.logOptimization.bind(this)}
                                simpleValue
                                searchable={false}
                                placeholder = "Select Trip Optimization"
                                backspaceToRemoveMessage=""
                            />
                        </div>
                        <input className="btn btn-primary btn-md" type="submit" value="Get Optimized Trip" style={{margin:'5px', width:'98%'}}/>
                        <br></br>
                    </form>
                </center>

                <div className="map">
                    <center>{this.props.svg}</center>

                </div>

                <center>
                    <h3>Additional Information</h3>
                    <form className="Drop-down-form2" onSubmit={this.handleSubmit}>
                        <div className = "Select-control">
                            <Select
                                name="form-
  			field-name"
                                value={this.state.value}
                                multi={true}
                                options={this.props.dropdownvalues}
                                onChange={this.logChange.bind(this)}
                                simpleValue
                                searchable={false}
                                placeholder = "Select trip itinerary information to display..."
                                backspaceToRemoveMessage=""
                            />
                        </div>
                        <input className="btn btn-primary btn-md" type="submit" value="Get Info" style={{margin:'5px', width:'98%'}}/>
                        <br></br>
                    </form>
                </center>

                <center>
                    <div className = "checkbox">
                        <input type="checkbox"
                               defaultChecked={true}
                               onClick={this.logCheckM.bind(this)}
                        />
                        <label>Miles</label>
                    </div>
                    <div className = "checkbox">
                        <input type="checkbox"
                               defaultChecked={false}
                               onClick={this.logCheckK.bind(this)}
                        />
                        <label>Kilometers</label>
                    </div>
                </center>

                <div className="subheading">
                    <h1>Your Itinerary</h1>
                </div>
                <table className="pair-table">
                    <thead>
                    <tr>
                        <th>Leg</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Distance</th>
                        <th>Cumulative Distance</th>
                    </tr>
                    </thead>

                    {this.props.pairs}
                    <tbody>
                    <tr>
                        <td colSpan="3">Total:</td>
                        <td colSpan="2">{total}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <center>

            </center>

        </div>

    }
    // File reading is almost identical how you did it in Sprint 1
     uploadFileClicked(acceptedFiles) {
         console.log("Accepting drop");
         acceptedFiles.forEach(file => {
             console.log("Filename:", file.name, "File:", file);
             console.log(JSON.stringify(file));
             let fr = new FileReader();
             fr.onload = (function () {
                 return function (e) {
                     let JsonObj = JSON.parse(e.target.result);
                     console.log(JsonObj);
                     // Do something with the file:
                     this.props.browseUploadedFile(JsonObj);
                 };
             })(file).bind(this);
 
             fr.readAsText(file);
         });
     }
    
    showPhoto(svgFile){
        console.log("Accepting drop");
        svgFile.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let image = e.target.result;
                    //console.log("Image = ", image);
                    this.props.svgImage(image);
                };
            }) (file).bind(this);
            fr.readAsDataURL(file);
        });
    }
}

export default Home
