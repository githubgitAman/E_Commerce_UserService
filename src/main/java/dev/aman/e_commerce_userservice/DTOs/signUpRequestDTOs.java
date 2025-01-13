package dev.aman.e_commerce_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signUpRequestDTOs {
    private String name;
    private String email;
    private String password;
}
