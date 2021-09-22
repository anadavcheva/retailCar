import React from "react";
import {useHistory} from 'react-router-dom';


const orderItem = (props) => {

    const {carItem, increaseQuantity, decreaseQuantity, deleteOrderItem} = props;
    return (
        <div  className="row">
            <div className="col-2">{carItem.model}</div>
            <div className="col-2">
                <button onClick={() => decreaseQuantity(carItem)} className="remove">
                    -
                </button>{' '}
                <button onClick={() => increaseQuantity(carItem)} className="add">
                    +
                </button>
            </div>
            <div className="col-2 text-right">
                {carItem.quantity} x
                {carItem.itemPrice.amount}{carItem.itemPrice.currency}
            </div>
            <div>
                <button onClick={() => deleteOrderItem(carItem)} className="delete">
                    Delete Order Item
                </button>
            </div>

        </div>
    );

}

export default orderItem;