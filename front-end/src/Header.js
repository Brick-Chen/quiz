import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <div className="header">
      <div className="nav-menu">
        <Link to="/" 
        style={{color: 'white', textDecoration: 'none', marginRight: '40px', marginTop: '20px'}}>
          商城
        </Link>
  
        <Link to="/shoplist" 
        style={{color: 'white', textDecoration: 'none', marginRight: '40px'}}>
          订单
        </Link>
  
        <Link to="/addgood" 
        style={{color: 'white', textDecoration: 'none', marginRight: '40px'}}>
          添加商品
        </Link>
      </div>
    </div>);
}

export default Header;