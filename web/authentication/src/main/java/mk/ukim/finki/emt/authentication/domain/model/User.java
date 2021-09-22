package mk.ukim.finki.emt.authentication.domain.model;

import lombok.Data;
import mk.ukim.finki.emt.authentication.domain.valueobjects.Address;
import mk.ukim.finki.emt.authentication.domain.valueobjects.Password;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class User extends AbstractEntity<UserId> implements UserDetails {

    private String username;

    private String email;

    @Column(name = "user_role")
    private Role role;

    @AttributeOverrides({
            @AttributeOverride(name="street", column = @Column(name = "address_street")),
            @AttributeOverride(name="streetNumber", column = @Column(name = "address_street_number")),
            @AttributeOverride(name="city", column = @Column(name = "address_city")),
            @AttributeOverride(name="country", column = @Column(name = "address_country")),
    })
    private Address address;


    private Password password;

    public User(){
        super(UserId.randomId(UserId.class));
    }

    public static User build(String username, String email, Role role, Address address, Password password){
        User user = new User();
        user.username = username;
        user.email = email;
        user.role = role;
        user.address = address;
        user.password = password;
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
