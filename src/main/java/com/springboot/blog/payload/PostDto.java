package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {

    private long id;
    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty
    @Size(min = 2, message = "Post Should have at least 2 characters")
    private String title;
    @Schema(
            description = "PostDto Model Description"
    )
    @NotEmpty
    @Size(min = 2, message = "Post Should have at least 2 characters")
    private String description;
    @Schema(
            description = "PostDto Model Content"
    )
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    @Schema(
            description = "PostDto Model Category"
    )
    private Long CategoryId;
}

