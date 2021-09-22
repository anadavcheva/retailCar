import axios from '../custom-axios/order-axios';

const OrderService = {

    getOrders: () => {
        return axios.get("/orders");
    },

    getOrder: (id) => {
        return axios.get(`/orders/${id}`)
    },

    getTotalPrice: (id) => {
    return axios.get(`/orders/total/${id}`)
    },

    getItemsById: (id) => {
        return axios.get(`/orders/items/${id}`)
    },

    createOrder: () => {
        return axios.get("/orders/new-order")
    },

    deleteOrder: (id) => {
        return axios.post(`/orders/cancel-order/${id}`)
    },

    placeOrder: (id) => {
        return axios.post(`/orders/place-order/${id}`)
    },

    addItem: (id, car, quantity) => {
        return axios.post(`/orders/add/${id}`, {
            "car": car,
            "quantity": quantity
        });
    },

    deleteItem: (id, orderItemId) => {
        return axios.post(`/orders/delete/${id}`, {
            "orderItemId": orderItemId.id
        });
    },

    increaseQuantity: (id, orderItemId) => {
        return axios.post(`/orders/increase/${id}`, {
            "orderItemId": orderItemId.id
        });
    },

    decreaseQuantity: (id, orderItemId) => {
        return axios.post(`/orders/decrease/${id}`, {
            "orderItemId": orderItemId.id
        });
    }



}

export default OrderService;