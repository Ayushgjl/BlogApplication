package com.example.blog.respositories;

import com.example.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
