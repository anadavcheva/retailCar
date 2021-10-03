import axios from '../custom-axios/car-axios';

const AuthService = {

    getToken() {
        return localStorage.getItem('USER_KEY');
    },

    registerUser(username, email, role, billingAddress_streetName, billingAddress_streetNumber, billingAddress_streetCity, billingAddress_streetCountry, password, confirmPassword) {
        return axios.post("/auth/register", {
            "username": username,
            "email": email,
            "role": role,
            "address": {
                "street": billingAddress_streetName,
                "streetNumber": billingAddress_streetNumber,
                "city": billingAddress_streetCity,
                "country": billingAddress_streetCountry
            },
            "password": {
                "password": password
            },
            "confirmPassword" : {
                "confirmPassword": confirmPassword
            }

        })
    },

    loginUser(username, password) {
        return axios.post("/auth/login", {
            "username": username,
            "password" : password,
        })
    },

    getBillingAddress(id) {
        return axios.get(`/auth/user-address/${id}`,
            {
                headers:{
                    'Authorization':'Bearer '+ this.getToken()
                }
            }
    )
    }

}


export default AuthService;