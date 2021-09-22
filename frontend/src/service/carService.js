import axios from '../custom-axios/car-axios';

const CarService = {

    getCars: () => {
        return axios.get("/car");
    },

    getModel: (id) => {
        return axios.get(`/model/${id}`)
    },

    getCar: (id) => {
        return axios.get(`/car/${id}`)
    },

    addCar: (model, color, price) => {
    return axios.post("/car", {
        "model" : model,
        "color": color,
        "sales" : 0,
        "price" : price
    });
    }

}

export default CarService;