import './Login.css';
import TextField from '@material-ui/core/TextField';
import React from 'react';
import { Button } from '@material-ui/core';
import Properties from "./const/Properties";
import * as axios from "axios";

class Logout extends React.Component {
    constructor(){
        super();
        localStorage.setItem("token","");
        window.location = '/login';
           }
    render() {
        return (
            <div className="login-background">
        </div>
        )
    }
}
export default Logout;