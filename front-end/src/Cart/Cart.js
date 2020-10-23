import React from 'react';
import 'antd/dist/antd.css';
import './Cart.css'
import { Modal, Button } from 'antd';
import {
    ShoppingCartOutlined,
  } from '@ant-design/icons';

export default class Cart extends React.Component {
  state = { visible: false };

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleOk = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  render() {
    return (
      <div>
        <Button type="primary" onClick={this.showModal} className="cart">
        <ShoppingCartOutlined />
        </Button>
        <Modal
          title="购物车"
          style={{ top: 300 }}
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          footer={[
            <Button key="submit" type="primary" onClick={this.handleOk}>
            立即下单
            </Button>,
            <Button key="remove" onClick={this.handleCancel}>
              清空
            </Button>,
          ]}
        >
          
        </Modal>
      </div>
    );
  }
}
