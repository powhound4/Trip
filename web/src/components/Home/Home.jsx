import React, {Component} from 'react';
import ReactModal from 'react-modal';
import Select from 'react-select';
import Dropzone from 'react-dropzone';

const customStyles = {
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};



class Home extends React.Component {
	
	constructor(props) {
    		super(props);
   	 	this.state = {
            modalIsOpen: false,
            inputValue: '',
           
    		};
   	 
    		this.openModal = this.openModal.bind(this);
    		this.afterOpenModal = this.afterOpenModal.bind(this);
    		this.closeModal = this.closeModal.bind(this);
    		
    		this.handleChange = this.handleChange.bind(this);
    		this.handleSubmit = this.handleSubmit.bind(this);
  	}
	
	logChange(val) {
  	  this.setState({value: val}); //dropdown has selected value
  	//  this.state.values.push(val); 
  	  console.log(val);
	}
  	openModal() {
   	  this.setState({modalIsOpen: true});
  	}

  	afterOpenModal() {
    	  this.subtitle.style.color = '#000';
  	}

  	closeModal() {
    	  this.setState({modalIsOpen: false});
  	}
	handleChange(event){
        this.setState({inputValue: event.target.value});
        }
    
    handleSubmit(event){
        console.log(this.state.inputValue);
        console.log('drop down values: ' + this.props.dropdownvalues);
         /*alert('Search for: ' + this.state.inputValue); */
         event.preventDefault(); 
        }
        
    	render() {
        let values = this.props.dropdownvalues;
        let total = this.props.totalDist; //update the total here
        
        return <div className="home-container">
            <div className="inner">
                <h1>T04 - 4TheWin</h1>
                <h3>Brewery Tour</h3>
		    
                <Dropzone className="dropzone-style" onDrop={this.dropInfo.bind(this)}>
                    <center><button type="button" className="btn btn-primary btn-md">Open Information File</button></center>
                </Dropzone>
		    
		<center>
		<form>
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
                
		<div>
         		<center><button type="button" className="btn btn-primary btn-md" onClick={this.openModal}>Open JSON FILE</button></center>
        		<ReactModal
         			isOpen={this.state.modalIsOpen}
         	 		onAfterOpen={this.afterOpenModal}
          			onRequestClose={this.closeModal}
          			style={customStyles}
          			contentLabel="TripCo Modal"
        		>
					<h3>Upload a JSON File:</h3>
          			<form>
            
            		<Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                		<center><button type="button" className="btn btn-primary btn-md">Open JSON File</button></center> 
                	</Dropzone>
            		<center><button type="button" className="btn btn-primary btn-md" onClick={this.closeModal}>Close Window</button></center>
      
          			</form>
        		</ReactModal>
      		</div>
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
		<div className="right">
            		{this.props.svg}
        		<Dropzone className="dropzone-style" onDrop={this.showPhoto.bind(this)}>
        			<center><button className="btn btn-primary btn-md">Display Map</button></center>
        		</Dropzone>
            </div>
            
      
        <center>
        <div>
            <form classname='search-form' onSubmit={this.handleSubmit}>
            <input 
                type="text"
                placeholder="Search Destinations"
               /* value ={this.state.value} */
                onChange={this.handleChange}
                />
                 <input className="btn btn-primary btn-md" type="submit" value="Search" />
            </form>
        </div>
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
        dropInfo(acceptedFiles) {
        console.log("Accepting drop!");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log("json Object: ",JsonObj);
                    this.props.browseInfoFile(JsonObj);
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
}

export default Home
