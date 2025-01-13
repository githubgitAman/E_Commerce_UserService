package dev.aman.e_commerce_userservice.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Roles extends BaseModel {
    private String values;

}
