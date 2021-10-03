package mk.ukim.finki.emt.authentication.web;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.authentication.domain.model.User;
import mk.ukim.finki.emt.authentication.domain.model.UserId;
import mk.ukim.finki.emt.authentication.domain.valueobjects.Address;
import mk.ukim.finki.emt.authentication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UserId id) {
        return this.userService.findById(id);
    }

    @GetMapping("/user-address/{id}")
    public Address getUserAddress(@PathVariable UserId id) {
        return this.userService.findById(id).getAddress();
    }

}
