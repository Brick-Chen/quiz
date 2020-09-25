import React from 'react';
import {Route, BrowserRouter, Link} from "react-router-dom";
import AddGood from './AddGood';
import './App.css';
import Shop from './Shop';
import ShopList from './ShopList';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <div className="App-header">
        <Link className="App-link" to="/" >
          商城
        </Link>
  
        <Link className="App-link" to="/shoplist" >
          订单
        </Link>
  
        <Link className="App-link" to="/addgood" >
          添加商品
        </Link>
      </div>

      <Route exact path='/' component={Shop}/>
      <Route exact path='/shoplist' component={ShopList}/>
      <Route exact path='/addgood' component={AddGood}/>
      </BrowserRouter>
    </div>
  );
}

export default App;
