import React from "react";


const orders = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Date</th>
                            <th scope={"col"}>State</th>
                            <th scope={"col"}>Total price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.orders.map((term) => {
                            console.log(term)
                            return (
                                <tr>
                                    <td>{term.date}</td>
                                    <td>{term.state}</td>
                                    <td>
                                        {term.total !== null && <strong>${term.total.amount} {term.total.currency}</strong>}
                                    </td>

                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default orders;