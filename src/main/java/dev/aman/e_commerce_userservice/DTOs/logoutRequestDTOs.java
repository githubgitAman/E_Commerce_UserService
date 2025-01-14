package dev.aman.e_commerce_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.Token;

@Getter
@Setter
public class logoutRequestDTOs {
    private String token;
    public String getToken(){
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }
}
