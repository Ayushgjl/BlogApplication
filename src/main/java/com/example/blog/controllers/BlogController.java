package com.example.blog.controllers;

import com.example.blog.config.CustomUserDetails;
import com.example.blog.entities.Post;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    private UserService userService;


    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/posts")
    public List<Post> posts() {
        return postService.getAllPosts();
    }

    @PostMapping(value = "/post")
    public String publicPost(@RequestBody Post post) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getDateCreated() == null)
            post.setDateCreated(new Date());
        post.setCreator(userService.getUser(userDetails.getUsername()));
        postService.insert(post);
        return "Post was published";
    }

    @GetMapping(value = "/posts/(username)")
    public List<Post> postsByUser(@PathVariable String username) {
        return postService.findByUser(userService.getUser(username));

    }

}
