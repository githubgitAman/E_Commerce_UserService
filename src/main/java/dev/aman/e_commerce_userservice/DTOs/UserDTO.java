package dev.aman.e_commerce_userservice.DTOs;

import dev.aman.e_commerce_userservice.Models.Roles;
import dev.aman.e_commerce_userservice.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;;
    private String email;
    private List<Roles> roles;
    public static UserDTO from(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
