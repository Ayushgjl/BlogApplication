package com.example.blog.service;


import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.respositories.PostRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRespository postRespository;

    public List<Post> getAllPosts(){
        return postRespository.findAll();
    }

    public void insert(Post post) {
        postRespository.save(post);
    }

    public List<Post> findByUser(User user) {
        return postRespository.findByCreatorId(user.getId());
    }
}
