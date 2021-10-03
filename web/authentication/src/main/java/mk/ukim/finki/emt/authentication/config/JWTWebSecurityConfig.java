package mk.ukim.finki.emt.authentication.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authentication.config.filters.JWTAuthorizationFilter;
import mk.ukim.finki.emt.authentication.config.filters.JWTTokenHelper;
import mk.ukim.finki.emt.authentication.config.filters.JwtAuthenticationFilter;
import mk.ukim.finki.emt.authentication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTTokenHelper jWTTokenHelper;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),userService,passwordEncoder))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(),userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
