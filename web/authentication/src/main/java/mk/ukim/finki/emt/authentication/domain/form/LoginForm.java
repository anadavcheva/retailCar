package mk.ukim.finki.emt.authentication.domain.form;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginForm {

    public String username;

    public String password;
}
