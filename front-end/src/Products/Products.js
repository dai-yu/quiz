import React from "react";
import Cart from "../Cart/Cart";
import "./Products.css";

export default class Products extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
    };
  }
  componentDidMount() {
    const url = "http://localhost:8088/product";
    let myHeaders = new Headers({
      "Access-Control-Allow-Origin": "*",
      "Content-Type": "text/plain",
    });
    fetch(url, {
      method: "GET",
      headers: myHeaders,
      mode: "cors",
    })
      .then((res) => res.json())
      .then((data) => {
        this.setState({
          products: data,
        });
      });
  }
  render() {
    const imgUrl = "/img/";
    return (
      <div className="productPage">
        {this.state.products.map((product) => (
          <div key={product.productName} className="oneProduction">
            <img src={imgUrl + product.image} alt={product.productName}></img>
            <p>{product.productName}</p>
            <span>
              {product.price}/{product.quantifier}
            </span>
            <button>add to cart</button>
          </div>
        ))}

      <Cart/>
      </div>
    );
  }
}
