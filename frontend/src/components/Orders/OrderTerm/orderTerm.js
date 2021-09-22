import React from "react";
import {Link} from "react-router-dom";
import OrderItem from "../OrderTerm/OrderItem/orderItem"

const orderTerm = (props) => {

    const {orderItemList, increaseQuantity, decreaseQuantity, totalPrice, placeOrder, cancelOrder, deleteOrderItem} = props
    return (
        <aside className="block col-5">

            <h2> Order Item List </h2>
            <div>
                {orderItemList.length === 0 && <div>Order is empty!</div>}
                {orderItemList.map((carItem) => {
                    return (
                        <OrderItem carItem={carItem}
                                   increaseQuantity={increaseQuantity}
                                   decreaseQuantity={decreaseQuantity}
                                   deleteOrderItem = {deleteOrderItem} />
                    );
                })}
            </div>
            <div className="row">
                <div className="col-2">
                    <strong>Total Price</strong>
                </div>
                <div className="col-6 text-right">
                    {totalPrice !== null && <strong>${totalPrice.amount} {totalPrice.currency}</strong> }
                </div>
            </div>

            <div className="row">
                <div className="col-2">
                    <strong>Place order</strong>
                </div>

                <div className="col-6 text-right">
                    <button onClick={() => placeOrder()}>Place order</button>
                </div>
            </div>

            <div className="row">
                <div className="col-2">
                    <strong>Cancel order</strong>
                </div>

                <div className="col-6 text-right">
                    <button onClick={() => cancelOrder()}>Cancel order</button>
                </div>
            </div>


        </aside>
    );
}

export default orderTerm;