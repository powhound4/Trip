import React, {Component} from 'react';

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
