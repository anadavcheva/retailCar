package mk.ukim.finki.emt.authentication.domain.form;

import lombok.Getter;
import mk.ukim.finki.emt.authentication.domain.valueobjects.Address;
import mk.ukim.finki.emt.authentication.domain.valueobjects.Password;


@Getter
public class RegisterForm {

    public String username;

    public String email;

    public String role;

    public Address address;

    public Password password;

    public Password confirmPassword;
}
