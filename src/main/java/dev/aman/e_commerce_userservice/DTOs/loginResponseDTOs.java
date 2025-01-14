package dev.aman.e_commerce_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class loginResponseDTOs {
    private String token;
    private ResponseStatus responseStatus;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
