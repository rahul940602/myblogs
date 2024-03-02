package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post Rest API",
            description = "Create Post Rest API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get ALL Post Rest API",
            description = "Get ALL Post Rest API is used to fetch all the posts  from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 201 SUCCESS"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam (value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam (value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false)int pageSize,
            @RequestParam (value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false)String sortBy,
            @RequestParam (value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)String sortDir

    ){

        return postService.getAllPosts(pageNo, pageSize, sortBy,sortDir);
    }

    @Operation(
            summary = "Get Post By Id Rest API",
            description = "et Post By Id Rest API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 201 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable (name="id") long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    @Operation(
            summary = "Update Post Rest API",
            description = "Update Post Rest API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 201 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable (name="id") long id){

        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post Rest API",
            description = "Delete Post Rest API is used to delete a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 201 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable (name="id") long id){

        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity Deleted Successfully ",HttpStatus.OK);
    }

    @GetMapping("/{category}/{id}")
    public ResponseEntity<List<PostDto>> getPosyByCategoryId(@PathVariable(value ="id")Long categoryId){

return new ResponseEntity<>(postService.getPostByCategory(categoryId), HttpStatus.OK);


    }
}
