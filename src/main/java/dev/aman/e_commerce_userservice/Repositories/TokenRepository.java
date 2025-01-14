package dev.aman.e_commerce_userservice.Repositories;

import dev.aman.e_commerce_userservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    //save method act like a update and insert
    //if incoming token as id then it will update else save
    //also called Upsert i.e Update + Insert
    Token save(Token token);
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean deleted, Date expiryAtIsGreaterThan);

}
