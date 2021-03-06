import React from 'react';
import Products from './Products/Products';
import {
  BrowserRouter,
  Route,
  Switch,
  NavLink,
} from "react-router-dom";
import './App.css';
import AddProduct from './AddProduct/AddProduct';
import Cart from './Cart/Cart';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <div className="header">
      <NavLink to="/">商城</NavLink>
      <NavLink to="/">订单</NavLink>
      <NavLink to="/cart">购物车</NavLink>
      <NavLink to="/addProduct">添加商品</NavLink>
      
      </div>
          <Switch>
            <Route exact path="/" component={Products} />
            <Route exact path="/cart" component={Cart} />
            <Route exact path="/addProduct" component={AddProduct} />
          </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
