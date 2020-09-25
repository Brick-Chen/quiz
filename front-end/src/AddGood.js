import React, {Component} from 'react';
import './addgood.css';

class AddGood extends Component {
  constructor() {
    super();
    this.state = {
      goodsName: '',
      goodsPrice: '',
      goodsUnit: '',
      goodsUrl: '',
    }
  }

  handleInput = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  }

  render() {
    return (
      <div className="addgoods">
        <div className="add-good-title">添加商品</div>
        <div className="goods-info">
          <form>
            <div className="info-piece">
              <label htmlFor="goods-name">名称</label>
              <input type="text" id="goods-name" name="goodsName" value={this.state.goodsName} onChange={this.handleInput}></input>
            </div>
  
            <div className="info-piece">
              <label htmlFor="goods-price">价格</label>
              <input type="number" id="goods-price" name="goodsPrice" value={this.state.goodsPrice} onChange={this.handleInput}></input>
            </div>
            
            <div className="info-piece">
              <label htmlFor="goods-unit">单位</label>
              <input type="text" id="goods-unit" name="goodsUnit" value={this.state.goodsUnit} onChange={this.handleInput}></input>
            </div>
  
            <div className="info-piece">
            <label htmlFor="goods-image">图片</label>
            <input type="text" id="goods-image" name="goodsUrl" value={this.state.goodsUrl} onChange={this.handleInput}></input>
            </div>
  
            <input
            type="button"
            id="submit"
            name="submit"
            value="提交"
            disabled={!(this.state.goodsName && this.state.goodsPrice && this.state.goodsUnit && this.state.goodsUrl)}
            />
          </form>
        </div>
      </div>
    );
  }
}
export default AddGood;