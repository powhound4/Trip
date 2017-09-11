import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"></link>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"></link>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
class Home extends React.Component {
    render() {
    
        let total = this.props.totalDist; //update the total here
        return <div className="home-container">
            <div className="inner">
                <h1>T04 - 4TheWin</h1>
                <h3>Brewery Tour</h3>
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <center><button type="button" class="btn btn-primary btn-md">Open JSON File</button></center>
                </Dropzone>
                
                <table className="pair-table">
                    <thead>
                        <tr>
                            <th>Start</th>
                            <th>End</th>
                            <th>Distance</th>
                            <th>Cummulative Distance</th>
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
        </div>
        
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
