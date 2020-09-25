import React from 'react';
import './addgood.css';

const AddGood = () => {
  return (
    <div className="addgoods">
      <div className="add-good-title">添加商品</div>
      <div className="goods-info">
        <form>
          <div className="info-piece">
            <label htmlFor="goods-name">名称</label>
            <input type="text" id="goods-name" name="goods-name"></input>
          </div>

          <div className="info-piece">
            <label htmlFor="goods-price">价格</label>
            <input type="text" id="goods-price" name="goods-price"></input>
          </div>
          
          <div className="info-piece">
            <label htmlFor="goods-unit">单位</label>
            <input type="text" id="goods-unit" name="goods-unit"></input>
          </div>

          <div className="info-piece">
          <label htmlFor="goods-image">图片</label>
          <input type="text" id="goods-image" name="image"></input>
          </div>
          
          <input type="button" id="submit" name="submit" value="提交"></input>
        </form>
      </div>
    </div>
  );
};

export default AddGood;