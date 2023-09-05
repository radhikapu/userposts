package com.bignerdranch.userposts.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
public class PostDto {
    @NonNull
    private Long userId;
    @Size(min=4, message="Min length should be 4")
    private String title;
    @Max(256)
    private String body;
}
