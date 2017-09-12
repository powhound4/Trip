import React, {Component} from 'react';
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"></link>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"></link>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
    
let Pair = ({start, end, dist, totalDist}) => <tbody
    className="pair">
    <tr>
        <td id="start">
            <h5>{start}</h5>
        </td>
        <td id = "end">
            <h5>{end}</h5>
        </td>
        <td id = "distance">
            <h5>{dist}</h5>
        </td>
        <td id = "totalDistance">
            <h5>{totalDist}</h5>
        </td>
    </tr>
</tbody>;

export default Pair;
