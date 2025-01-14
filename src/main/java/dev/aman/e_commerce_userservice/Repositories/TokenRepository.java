package dev.aman.e_commerce_userservice.Repositories;

import dev.aman.e_commerce_userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean deleted, Date expiryAtIsGreaterThan);
    Token markTokenAsDeleted(String value, boolean deleted);
}
