package dev.aman.e_commerce_userservice.Repositories;

import dev.aman.e_commerce_userservice.Models.Token;
import dev.aman.e_commerce_userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findByEmail(String email);
    Token save(Token token);
}
