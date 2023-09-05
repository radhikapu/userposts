package com.bignerdranch.userposts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class PostUserDto {
    @NotBlank (message = "Name is required")
    private String name;
    @Email (message = "Invalid email")
    private String email;
    @NotBlank (message = "Expertise is required")
    private String expertise;
}
