package dev.aman.e_commerce_userservice.Controllers;

import dev.aman.e_commerce_userservice.DTOs.*;
import dev.aman.e_commerce_userservice.DTOs.ResponseStatus;
import dev.aman.e_commerce_userservice.Models.Token;
import dev.aman.e_commerce_userservice.Models.User;
import dev.aman.e_commerce_userservice.Services.UserService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public loginResponseDTOs login(@RequestBody loginRequestDTOs requestDTOs){
        loginResponseDTOs responseDTOs = new loginResponseDTOs();
        try{
            Token token =  userService.login(requestDTOs.getEmail(), requestDTOs.getPassword());
            //here we are not returning whole token we are just returning string value
            responseDTOs.setToken(token.getValue());
            responseDTOs.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDTOs.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTOs;
    }
    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody signUpRequestDTOs requestDTOs){
        User user = userService.signUp(requestDTOs.getName(), requestDTOs.getEmail(),
            requestDTOs.getPassword());
        return UserDTO.from(user);
    }
    @PatchMapping("/logout")
    public void logout(@RequestBody logoutRequestDTOs requestDTOs){
        userService.logout(requestDTOs.getToken());
    }
    @GetMapping("/validate/{token}")
    //we are returning User here because we might need to know the roles of user after validating
    public UserDTO validateToken(@PathVariable String token){
        User user = userService.validateToken(token);
        return UserDTO.from(user);
    }
}
