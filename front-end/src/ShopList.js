import React, { Component } from 'react'

class ShopList extends Component {
  constructor() {
    super();
    this.state = {
      purchased: [],
    };
  }
  render() {
    if (this.state.purchased.length === 0) {
      return (
        <div className="product-list">
          当前购物为空
        </div>
      );
    } else {
      return (
        <div className="product-list">
          This is product list.
        </div>
      );
    }
  }
}

export default ShopList;