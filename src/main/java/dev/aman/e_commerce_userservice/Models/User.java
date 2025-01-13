package dev.aman.e_commerce_userservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String hashPassword;
    @ManyToMany
    private List<Roles> roles;
    private boolean isVerified;
}
