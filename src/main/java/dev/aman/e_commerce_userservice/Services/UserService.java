package dev.aman.e_commerce_userservice.Services;

import dev.aman.e_commerce_userservice.Models.Token;
import dev.aman.e_commerce_userservice.Models.User;
import dev.aman.e_commerce_userservice.Repositories.TokenRepository;
import dev.aman.e_commerce_userservice.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public Token login(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User userFound = user.get();
        //here we need to check the password
        //we are using matches method because the password we stored in DB is also encrypted
        //so in matches method we match raw password and encoded password
        //using this raw password can we generate encoded password
        if(bCryptPasswordEncoder.matches(password, user.get().getHashPassword())){
            //Generate token
            Token token = generateToken(userFound);
            //after generating save it to DB
            Token savedToken = tokenRepository.save(token);
            return savedToken;
        }

        return null;
    }
    public User signUp(String name, String email, String password){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        //Encrypting the password using BCrypt algorithm and storing in DB
        user.setHashPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }
    public void logout(String token){
        Optional<Token> logoutToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token,
                false, new Date());
        if(logoutToken.isEmpty()){
            throw new RuntimeException("Token not found");
        }
        Token tokenFound = logoutToken.get();
        tokenFound.setDeleted(true);
        tokenRepository.save(tokenFound);
    }
    public User validateToken(String token){
        //first we need to check whether token is valid or not in DB
        //We need to check whether its deleted value is true or false and expiry time
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token
                , false, new Date());
        if(optionalToken.isEmpty()){
            return null;
        }
        return optionalToken.get().getUser();
    }
    private Token generateToken(User user){
        Token token = new Token();
        token.setUser(user);
        //RandomString is part of apache common lang dependency
        //we have added in pom and it is used to generate random string
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        //We use this code to set our expiry date after 30 days
        //take from chatgpt
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromNow = LocalDate.now().plusDays(30);
        Date expiryDate = Date.from(thirtyDaysFromNow.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryDate);
        return token;
    }
}
