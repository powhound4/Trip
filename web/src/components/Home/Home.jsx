import React, {Component} from 'react';
import Select from 'react-select';
import Dropzone from 'react-dropzone';

class Home extends React.Component {
	
	constructor(props) {
    		super(props);
   	 	this.state = {
           
            inputValue: ''
           
    		};
   	 
    		
    		
    		this.handleChange = this.handleChange.bind(this);
    		this.handleSubmit = this.handleSubmit.bind(this);
  	}
	
	logChange(val) {
  	  this.setState({value: val}); //dropdown has selected value
  	//  this.state.values.push(val); 
  	  console.log(val);
	}
  	
	handleChange(event){
        this.setState({inputValue: event.target.value});
        }
    
    handleSubmit(event){
        console.log(this.state.inputValue);
        console.log('drop down values: ' + this.props.dropdownvalues);
         /*alert('Search for: ' + this.state.inputValue); */
	    this.props.fetch(this.state.inputValue);
         event.preventDefault(); 
        }
        
    	render() {
        let values = this.props.dropdownvalues;
        let total = this.props.totalDist; //update the total here
        
        return <div className="home-container">
           <div className="inner">
                <h1>T04 - 4TheWin</h1>
                <h3>Airport Tour With Beer</h3>
                
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
		<form className="Drop-down-form">
		<div className = "select-control">
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
		</form>
		</center>
                
		
		    <br></br>
                <table className="pair-table">
                    <thead>
                        <tr>
                            <th>Start</th>
                            <th>End</th>
                            <th>Distance</th>
                            <th>Cumulative Distance</th>
                        </tr>
                    </thead>
                
                
                    {this.props.pairs}
                    <tbody>
                        <tr>
                            <td colSpan="2">Total:</td>
                            <td>{total}</td>
                            <td></td>
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
