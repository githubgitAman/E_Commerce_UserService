package dev.aman.e_commerce_userservice.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value;
    private Date expiryAt;
    @ManyToOne
    private User user;
}