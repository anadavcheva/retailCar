import React from "react";

const carTerm = (props) => {
    return (
        <div>
            <h3>Model name: {props.term.model}</h3>
            <div>
                <img src={props.term.image} alt="Car image"></img>
            </div>
            <div>Color: {props.term.color}</div>
            <div>Price: {props.term.price.amount}</div>
            <div>Currency: {props.term.price.currency}</div>
            {/*<td>{term.agency.name}</td>*/}
            <div>
                {props.orderId !== undefined && <button onClick={() => props.onAddOrderItem(props.term, 1)}>Add to Cart</button>}
            </div>
        </div>
    );

}

export default carTerm;