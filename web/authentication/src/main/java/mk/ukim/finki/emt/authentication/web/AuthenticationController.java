package mk.ukim.finki.emt.authentication.web;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authentication.config.filters.JWTTokenHelper;
import mk.ukim.finki.emt.authentication.domain.form.LoginForm;
import mk.ukim.finki.emt.authentication.domain.form.LoginResponse;
import mk.ukim.finki.emt.authentication.domain.form.RegisterForm;
import mk.ukim.finki.emt.authentication.domain.model.User;
import mk.ukim.finki.emt.authentication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=(User)authentication.getPrincipal();
        String jwtToken=jwtTokenHelper.generateToken(user.getUsername());

        LoginResponse response=new LoginResponse();
        response.setToken(jwtToken);

        this.userService.login(loginForm);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterForm registerForm)  {
        return this.userService.register(registerForm);
    }

}
