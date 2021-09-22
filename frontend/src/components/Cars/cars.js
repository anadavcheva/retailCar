import React from "react";
import CarTerm from "../Cars/CarTerm/carTerm"

const cars = (props) => {

    return (
        <main className="block col-5">
            <h2>Cars</h2>
            <div className="row">
                {props.cars.map((term) => {
                    return (
                        <CarTerm key={term.id} term={term}
                                 onAddOrderItem={props.onAddOrderItem}
                                 orderId={props.orderId}/>
                    );
                })}
            </div>
        </main>
    );
}

export default cars;