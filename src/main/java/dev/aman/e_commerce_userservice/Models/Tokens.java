package dev.aman.e_commerce_userservice.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Tokens extends BaseModel {
    private String value;
    private Date expiryAt;
    @ManyToOne
    private User user;
}
