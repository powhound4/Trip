import React, {Component} from 'react';
import ReactModal from 'react-modal';
import Dropzone from 'react-dropzone';
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"></link>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"></link>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

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
	
	constructor() {
    		super();
   	 	this.state = {
      			modalIsOpen: false
    		};
   	 
    		this.openModal = this.openModal.bind(this);
    		this.afterOpenModal = this.afterOpenModal.bind(this);
    		this.closeModal = this.closeModal.bind(this);
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
    	render() {
    
        let total = this.props.totalDist; //update the total here
        return <div className="home-container">
            <div className="inner">
                <h1>T04 - 4TheWin</h1>
                <h3>Brewery Tour</h3>
		    
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <center><button type="button" className="btn btn-primary btn-md">Open Information File</button></center>
                </Dropzone>
                
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
                		console.log("Image = ", image);
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
