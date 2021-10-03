package mk.ukim.finki.emt.authentication.repository;

import mk.ukim.finki.emt.authentication.domain.model.User;
import mk.ukim.finki.emt.authentication.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);


}

