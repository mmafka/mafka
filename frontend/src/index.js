import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Login from './Login';
import {BrowserRouter as Router,
    Switch,
    Route} from "react-router-dom";
import Home from "./page/Home";
import Clusters from "./page/Clusters";
import Logout from './Logout';


ReactDOM.render((
      <Router>
          <Switch>
          <Route path = "/" exact>
              <Login/>
          </Route>
          <Route path = "/login" exact>
              <Login/>
          </Route>
          <Route path = "/logout" exact>
              <Logout/>
          </Route>
          <Route path = "/home" exact>
              <Home/>
          </Route>
          <Route path = "/clusters" exact>
              <Clusters/>
          </Route>
          </Switch>
      </Router>
), document.getElementById('root'));
