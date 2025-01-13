package dev.aman.e_commerce_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class loginResponseDTOs {
    private String token;
    private ResponseStatus responseStatus;
}
