import './Login.css';
import TextField from '@material-ui/core/TextField';
import React from 'react';
import { Button } from '@material-ui/core';
import Properties from "../src/const/Properties";
import * as axios from "axios";

class Login extends React.Component {
    constructor(){
        super();
        this.state = {
            userName: '',
            password: '',
            error: 0
        };
           }
    

    clickGiris= () =>{
        axios({
            method: 'post',
            url:  Properties.baseUrl+'/login',
            headers: {
                "Content-Type": "application/json"
            },
            data: {
                userName: this.state.userName,
                password: this.state.password
            }
        })
            .then(res => {
                //console.log(res.data.token);
                localStorage.setItem("token",res.data.token);
                localStorage.setItem("userName", this.state.userName);
                window.location = '/home';


                
            }).catch(res =>{
                window.location = '/login?error=1';
        });
    }

    getStyleLabel = () =>{
        const params = new URLSearchParams(window.location.search);
        let error = 0
        if(params.has("error"))
            error = parseInt(params.get("error"));
        if(error==1)
        return {marginTop:"-5px",color:"rgb(181 63 90)"}
        else
        return {display:"none"}
    }
    onChange = e => {
        this.setState({ [e.target.name]: e.target.value })
    }
    render() {
        return (
            <div className="login-background">
            <div className="login-blur"></div>
            <div className="login-panel">
                <div className="login-panel-op1">
                    <TextField onChange={this.onChange} className="login-context" id="filled-basic" variant="filled" label="Kullanıcı Adı" name="userName"/>
                    <TextField onChange={this.onChange} className="login-context" id="filled-basic" variant="filled" label="Şifre" type="password" name="password"/>
                    <Button onClick={this.clickGiris}  className="login-context" variant="outlined" color="primary">
                        Giriş
                    </Button>
                    <label style={this.getStyleLabel()}>Kullanıcı Adı veya Şifre Hatalı</label>
                </div>
            </div>
        </div>
        )
    }
}
export default Login;