package mk.ukim.finki.emt.authentication.service;

import mk.ukim.finki.emt.authentication.domain.form.LoginForm;
import mk.ukim.finki.emt.authentication.domain.form.RegisterForm;
import mk.ukim.finki.emt.authentication.domain.model.User;
import mk.ukim.finki.emt.authentication.domain.model.UserId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(RegisterForm userRegisterForm);

    User login(LoginForm userLoginForm);

    User findByUsernameAndPassword(String username, String password);

    User findById(UserId userId);
}