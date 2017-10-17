import React, {Component} from 'react';
    
let Svg = ({source}) => <img className="image" src={source + '?dc=' + new Date().getTime()} id="map" type="svg+xml"></img>;
    
        

export default Svg;
