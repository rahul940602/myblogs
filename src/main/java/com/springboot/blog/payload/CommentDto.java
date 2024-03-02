package com.springboot.blog.payload;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class CommentDto {

    private long id;
    @NotEmpty(message = "Name Should not be empty or null")
    private String name;
    @NotEmpty(message = "Email Should not be empty or null")
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "comment should be minimum 10 characters")
    private String body;

}
