package com.example.blog.respositories;

import com.example.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRespository extends JpaRepository<Post, Long> {
    List<Post> findByCreatorId(Long id);
}
