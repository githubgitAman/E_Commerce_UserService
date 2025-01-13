package dev.aman.e_commerce_userservice.Repositories;

import dev.aman.e_commerce_userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
}
