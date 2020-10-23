import React, { Component } from 'react'
 
export default class AddProduct extends Component {
    constructor(props) {
        super(props)
        this.state = {
            flag: false,
        };
    }
    onChange(e) {
        this.setState({
            [e.target.name]:e.target.value
        })
    }
    onSubmit(e) {
       e.preventDefault();
        const post ={
            productName: this.state.productName,
            price: this.state.price,
            quantifier: this.state.quantifier,
            image: this.state.image,
        }
        let myHeaders = new Headers({
            "Access-Control-Allow-Origin": "*",
            "Content-type":"application/json"
          });
        fetch('http://localhost:8088/product',{
            method:"POST",
            headers:myHeaders,
            body:JSON.stringify(post)
        })
        .then(res =>res.json())
        .then(data =>{
            console.log(data)
            alert(data.message)
        })
        .catch(error=>error)
    }
    render() {
        return (
            <div>
                <h1>添加商品</h1>
                <form onSubmit={this.onSubmit.bind(this)}>
                    <div>
                        <label >名称</label>
                        <br />
                        <input type="text" name="productName" onChange={this.onChange.bind(this)} defaultValue={this.state.title} placeholder="名称"/>
                    </div>
                    <div>
                        <label >价格</label>
                        <br />
                        <input type="number" name="price" onChange={this.onChange.bind(this)} defaultValue={this.state.title} placeholder="价格"/>
                    </div>
                    <div>
                        <label >单位</label>
                        <br />
                        <input type="text" name="quantifier" onChange={this.onChange.bind(this)} defaultValue={this.state.title} placeholder="单位"/>
                    </div>
                    <div>
                        <label >图片</label>
                        <br />
                        <input type="text" name="image" onChange={this.onChange.bind(this)} defaultValue={this.state.title} placeholder="URL"/>
                    </div>
                    <br />
                    <button type="submit" disabled={this.state.flag}>添加</button>
                </form>
            </div>
        )
    }
}