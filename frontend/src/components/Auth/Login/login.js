import React from "react";
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import AuthService from "../../../service/authService";

const Login = ({loading,error,...props}) => {

    const history = useHistory()

    const [formData, updateFormData] = React.useState({
        username: "",
        password: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        props.authenticate();
        const username = formData.username;
        const password = formData.password;

        AuthService.loginUser(username, password).then((response)=>{

            console.log("response",response);
            if(response.status===200){
                props.setUser(response.data);
                history.push({pathname: '/cars'})
            }
            else{
                props.loginFailure('Something Wrong!Please Try Again');
            }

        }).catch((err)=>{
            if(err && err.response){
                switch(err.response.status){
                    case 401:
                        console.log("401 status");
                        props.loginFailure("Authentication Failed.Bad Credentials");
                        break;
                    default:
                        props.loginFailure('Something Wrong!Please Try Again');
                }
            }
            else{
                props.loginFailure('Something Wrong!Please Try Again');
            }

        });
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                {props.errorMessage !== null && <div class="alert alert-danger" role="alert">
                    {props.errorMessage}
                </div>}
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text"
                               className="form-control"
                               id="username"
                               name="username"
                               required
                               placeholder="Enter username"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password"
                               className="form-control"
                               id="password"
                               name="password"
                               placeholder="Enter password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    )
}

const mapStateToProps=({auth})=>{
    console.log("state ",auth)
    return {
        loading:auth.loading,
        error:auth.error
    }}


const mapDispatchToProps=(dispatch)=>{

    return {
        authenticate :()=> dispatch(this.authenticate()),
        setUser:(data)=> dispatch(this.authSuccess(data)),
        loginFailure:(message)=>dispatch(this.authFailure(message))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Login);