import React, {Component} from 'react';
import Select from 'react-select';
import Dropzone from 'react-dropzone';

class Home extends React.Component {
	
	constructor(props) {
    		super(props);
   	 	this.state = {
           
            inputValue: '',
            columnValues: []
    		};
   	 
    		
    		
    		this.handleChange = this.handleChange.bind(this);
    		this.handleSubmit = this.handleSubmit.bind(this);
  	}
	
	logChange(val) {
  	  this.setState({value: val}); //dropdown has selected value
  	  console.log(val);
  	  this.state.columnValues.push(val);
  	  this.props.getColumns(this.state.columnValues);

	}
  	
	handleChange(event){
        this.setState({inputValue: event.target.value});
        }
    
    handleSubmit(event){
        console.log(this.state.inputValue);
        console.log('drop down values: ' + this.props.dropdownvalues);
	    this.props.fetch(this.state.inputValue);
        event.preventDefault(); 
        }
        
    	render() {
    	
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
            <form className='search-form' onSubmit={this.handleSubmit}>
            <input className="SearchDest"
                type="text"
                placeholder="Search Destinations"
                onChange={this.handleChange}/>
                <br></br>
                <input className="btn btn-primary btn-md" type="submit" value="Search" />
                <br></br>
            </form>
        </div></center>
                
                <div className="map">
            		<center>{this.props.svg}</center>
        		
            </div>
		    
                
              
       
		    
		<center>
		<form className="Drop-down-form" onSubmit={this.handleSubmit}>
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
                            <th>Add to Itinerary</th>
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

    drop(acceptedFiles) {
        console.log("Accepting drop");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj);
                    this.props.browseFile(JsonObj);
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
        
}

export default Home
