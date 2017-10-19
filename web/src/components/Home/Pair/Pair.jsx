import React, {Component} from 'react';

function listInfo(values){
   
   let stringTag = [];
   for(let i = 0; i < values.length; i++){
   console.log("values[i]: " , values[i]);
   
   stringTag.push(<li>{values[i]}</li>);

   }
  

   return <ul>{stringTag}</ul>
   
};


let Pair = ({start, end, dist, totalDist, startValues, endValues}) => <tbody //startvalues //end.values
    className="pair">
    <tr>
        <td id="start">
            <h5>{start}</h5>
            {listInfo(startValues)}
          
        </td>
        <td id = "end">
            <h5>{end}</h5>
            {listInfo(endValues)}
         
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
