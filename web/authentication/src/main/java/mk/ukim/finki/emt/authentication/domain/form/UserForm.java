package mk.ukim.finki.emt.authentication.domain.form;

import lombok.Getter;
import mk.ukim.finki.emt.authentication.domain.model.Role;
import mk.ukim.finki.emt.authentication.domain.model.User;

@Getter
public class UserForm {

    private String username;
    private Role role;

    public static UserForm of(User user) {
        UserForm details = new UserForm();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
