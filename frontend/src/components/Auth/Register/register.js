import React from "react"

const Register = (props) => {

    const [formData, updateFormData] = React.useState({
        username: "",
        email: "",
        role: "",
        address_street: "",
        address_streetNumber: "",
        address_city: "",
        address_country: "",
        password: "",
        confirmPassword: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const username = formData.username;
        const email = formData.email;
        const role = formData.role;
        const address_street = formData.address_street;
        const address_streetNumber = formData.address_streetNumber;
        const address_city = formData.address_city;
        const address_country = formData.address_country;
        const password = formData.password;
        const confirmPassword = formData.confirmPassword;


        props.registerUser(username, email, role, address_street, address_streetNumber, address_city, address_country, password, confirmPassword)
    }


    return(
        <div className="row mt-5">
            <div className="col-md-5">
                {props.errorMessage !== null && <div className="alert alert-danger" role="alert">
                    {props.errorMessage}
                </div>}
                {props.userId !== null && <div class="alert alert-success" role="alert">
                    REGISTER SUCCESS! Go to <a href="/login"> Login </a>
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
                        <label htmlFor="email">Email</label>
                        <input type="text"
                               className="form-control"
                               id="email"
                               name="email"
                               placeholder="Email"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div onChange={handleChange}>
                        <span>Role</span>
                        <input type="radio"
                               name="role"
                               value="ROLE_USER"
                               required
                               onChange={handleChange}
                        /> ROLE_USER
                        <input type="radio"
                               name="role"
                               value="ROLE_ADMIN"
                               required
                        /> ROLE_ADMIN
                    </div>
                    <div className="form-group">
                        <label htmlFor="address_street">Billing address street:</label>
                        <input type="text"
                               className="form-control"
                               id="address_street"
                               name="address_street"
                               placeholder="Billing address street:"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address_streetNumber">Billing address street number:</label>
                        <input type="number"
                               className="form-control"
                               id="address_streetNumber"
                               name="address_streetNumber"
                               placeholder="Billing address street number:"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address_city">Billing address city</label>
                        <input type="text"
                               className="form-control"
                               id="address_city"
                               name="address_city"
                               placeholder="Billing address city"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="address_country">Billing address country</label>
                        <input type="text"
                               className="form-control"
                               id="address_country"
                               name="address_country"
                               placeholder="Billing address country"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password"
                               className="form-control"
                               id="password"
                               name="password"
                               placeholder="Password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="confirmPassword">Confirm password</label>
                        <input type="password"
                               className="form-control"
                               id="confirmPassword"
                               name="confirmPassword"
                               placeholder="Confirm password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default Register;