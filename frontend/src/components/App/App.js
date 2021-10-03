import './App.css';
import {Component} from "react";
import {BrowserRouter as Router, Route} from 'react-router-dom'
import Cars from '../Cars/cars'
import OrderTerm from '../Orders/OrderTerm/orderTerm'
import Order from '../Orders/OrderList/orders'
import Header from '../Header/header'
import Register from '../Auth/Register/register'
import Login from '../Auth/Login/login'
import OrderService from "../../service/orderService";
import CarService from "../../service/carService";
import AuthService from "../../service/authService";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            orderId: undefined,
            total: null,
            orderItemList: [],
            orders: [],
            cars: [],
            loggedUser: undefined,
            errorMessage: null,
            userId: null,
            billingAddress: {},
            bool: false
        }
    }

    render() {
        return (
            <Router>
                <Header user={this.state.loggedUser} logout={this.logoutUser}/>
                <div className="row">
                    <Route path={"/register"}>
                        <Register registerUser={this.registerUser} errorMessage={this.state.errorMessage}
                                  userId={this.state.userId}/>
                    </Route>
                    <Route path={"/login"}>
                        <Login loginUser={this.loginUser} errorMessage={this.state.errorMessage}/>
                        <Route path={"/cars"}>
                            <Cars cars={this.state.cars}
                                  orderId={this.state.orderId}
                                  onAddOrderItem={this.addOrderItem}/>
                            {this.state.orderId === undefined &&
                            <button onClick={this.createOrder}> Create New Order </button>}
                            {this.state.orderId !== undefined &&
                            <OrderTerm orderId={this.state.orderId}
                                       orderItemList={this.state.orderItemList}
                                       increaseQuantity={this.increaseQuantity}
                                       decreaseQuantity={this.decreaseQuantity}
                                       deleteOrderItem={this.deleteOrderItem}
                                       totalPrice={this.state.total}
                                       cancelOrder={this.cancelOrder}
                                       placeOrder={this.placeOrder}/>}
                        </Route>
                        <Route path={"/orders"}>
                            <Order orders={this.state.orders}/>
                        </Route>
                    </Route>
                </div>
            </Router>
        );
    }


    componentDidMount() {
        this.loadOrders();
        this.loadCars();
    }

    createOrder = () => {
        OrderService.createOrder()
            .then((data) => {
                this.setState({
                    orderId: data.data
                })
            });
    }

    cancelOrder = () => {
        OrderService.deleteOrder(this.state.orderId.id)
            .then((data) => {
                this.setState({
                    orderId: undefined
                })
            });
    }

    placeOrder = () => {
        OrderService.placeOrder(this.state.orderId.id)
            .then((data) => {
                this.setState({
                    orderId: undefined
                })
                this.loadCars();
                this.loadOrders();
            })
    }

    loadOrders = () => {
        OrderService.getOrders()
            .then((data) => {
                this.setState({
                    orders: data.data
                })
            });
    }

    getOrder = (id) => {
        OrderService.getOrder(id)
            .then((data) => {
                this.setState({
                    selectedOrder: data.data
                })
            });
    }

    getTotalPrice = () => {
        OrderService.getTotalPrice(this.state.orderId.id)
            .then((data) => {
                this.setState({
                    total: data.data
                })
            })
    }

    totalPrice = (id) => {
        OrderService.getTotalPrice(id)
            .then((data) => {
                this.setState({
                    total: data.data
                })
            })
    }

    loadOrderItems = () => {
        OrderService.getItemsById(this.state.orderId.id)
            .then((data) => {
                this.setState({
                    orderItemList: data.data,
                })
                this.getTotalPrice();
            })
    }

    addOrderItem = (car, quantity) => {
        OrderService.addItem(this.state.orderId.id, car, quantity)
            .then((data) => {
                let orderItem = data.data
                let list = [...this.state.orderItemList]
                list.push(orderItem)
                this.setState({
                    orderItemList: list
                })
                this.getTotalPrice();
            });
    }

    deleteOrderItem = (orderItemId) => {
        console.log(orderItemId.id)
        console.log(this.state.orderId.id)
        OrderService.deleteItem(this.state.orderId.id, orderItemId.id)
            .then(() => {
                this.loadOrderItems();
            });
    }
    increaseQuantity = (carItem) => {
        OrderService.increaseQuantity(this.state.orderId.id, carItem.id)
            .then(() => {
                this.loadOrderItems();
            })
    }

    decreaseQuantity = (carItem) => {
        OrderService.decreaseQuantity(this.state.orderId.id, carItem.id)
            .then(() => {
                this.loadOrderItems();
            })
    }

    loadCars = () => {
        CarService.getCars()
            .then((data) => {
                this.setState({
                    cars: data.data
                })
            });
    }

    registerUser = (username, email, role, billingAddress_streetName, billingAddress_streetNumber, billingAddress_streetCity, billingAddress_streetCountry, password, confirmPassword) => {
        AuthService.registerUser(username, email, role, billingAddress_streetName, billingAddress_streetNumber, billingAddress_streetCity, billingAddress_streetCountry, password, confirmPassword)
            .then((data) => {
                this.setState({
                    userId: data.data,
                    errorMessage: null
                });
            })
            .catch(err => {
                    if (err != null) {
                        this.setState({
                            errorMessage: "REGISTER FAILED! Username already exists or Passwords do not match"})};
                }
            )

    }

    loginUser = (username, password) => {
        AuthService.loginUser(username, password)
            .then((data) => {
                this.setState({
                    loggedUser: data.data,
                    errorMessage: null,
                    bool : true
                })
            })
            .catch(err => {
                this.setState({
                    errorMessage: "Invalid credentials. Try again!"})});
    }

    logoutUser = () => {
        this.setState({
            userId: null,
            loggedUser: null,
            bool : false
        });
    }

    getBillingAddress = () => {
        AuthService.getBillingAddress(this.state.loggedUser.id.id)
            .then((data) => {
                this.setState({
                    billingAddress: data.data
                });
            })
    }

}

export default App;
