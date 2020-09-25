import React from 'react';
import Products from './Products/Products';
import {
  BrowserRouter,
  Route,
  Switch,
  NavLink,
} from "react-router-dom";
import './App.css';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <div className="header">
      <NavLink to="/">商城</NavLink>
      </div>
          <Switch>
            <Route exact path="/" component={Products} />
          </Switch>
        </BrowserRouter>
    </div>
  );
}

export default App;
