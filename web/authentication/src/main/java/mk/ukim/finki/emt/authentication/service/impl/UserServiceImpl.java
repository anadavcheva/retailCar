package mk.ukim.finki.emt.authentication.service.impl;

import mk.ukim.finki.emt.authentication.domain.exceptions.*;
import mk.ukim.finki.emt.authentication.domain.form.LoginForm;
import mk.ukim.finki.emt.authentication.domain.form.RegisterForm;
import mk.ukim.finki.emt.authentication.domain.model.Role;
import mk.ukim.finki.emt.authentication.domain.model.User;
import mk.ukim.finki.emt.authentication.domain.model.UserId;
import mk.ukim.finki.emt.authentication.repository.UserRepository;
import mk.ukim.finki.emt.authentication.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterForm userRegisterForm) {
        if (userRegisterForm.getUsername()==null || userRegisterForm.getUsername().isEmpty()  || userRegisterForm.getPassword()==null)
            throw new InvalidUsernameOrPasswordException();
        if (!userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword()))
            throw new PasswordDoNotMatchException(String.format("Passwords %s and %s do not match!", userRegisterForm.getPassword(), userRegisterForm.getConfirmPassword()));
        if(this.userRepository.findByUsername(userRegisterForm.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException(userRegisterForm.getUsername());
        User user = User.build(userRegisterForm.getUsername(), userRegisterForm.getEmail(), Role.valueOf(userRegisterForm.getRole()), userRegisterForm.getAddress(), userRegisterForm.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User login(LoginForm userLoginForm) {

        if (userLoginForm.getUsername()==null || userLoginForm.getUsername().isEmpty() || userLoginForm.getPassword()==null || userLoginForm.getPassword().isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }
        return this.findByUsernameAndPassword(userLoginForm.getUsername(), userLoginForm.getPassword());

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUsernameOrPasswordException::new);
    }

    @Override
    public User findById(UserId userId) {
        return this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}