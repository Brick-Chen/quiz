import React, {Component} from 'react';
import './product.css'
import hero from './assets/product_image_placeholder.png';

class Product extends Component {
  render() {
    return (
      <div className="product">
        <img className="product-image" src={hero} alt="img"></img>
        <div className="product-name">{this.props.name}</div>
        <div className="product-price-unit">{this.props.price + '元/' +this.props.unit}</div>
        <div className="add-product">
          <input type="button" name="add" id="add" value="+"/>
        </div>
      </div>
    );
  }
}

export default Product;