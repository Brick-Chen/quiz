import React, {Component} from 'react';
import Product from './Product';

class Shop extends Component {
  constructor() {
    super();
    this.state = {
      products: [],
    };
  }

  componentDidMount() {
    fetch('http://localhost:8080/products',  {
      headers : { 
        'Content-Type': 'application/json',
        'Accept': 'application/json'
       }

    })
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          return Promise.reject(response.statusText);
        }
      })
      .then((result) => {
        this.setState({
          products: result,
        });
      })
      .catch((error) => {
        console.error(error);
      });
  }

  render() {
    if (this.state.products.length === 0) {
        return (
          <div>暂无商品，等待加载或添加</div>
        );
    } else {
      return (
        <div className="products">
          {
            this.state.products.map((product, index) => {
              return (<Product name={product.productName} price={product.price} unit={product.unit} key={index}/>);
            })
          }
        </div>
      );
    }
    
  }
}

export default Shop;