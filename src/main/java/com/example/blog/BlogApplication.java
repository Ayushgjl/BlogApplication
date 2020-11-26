package com.example.blog;

import com.example.blog.config.CustomUserDetails;
import com.example.blog.entities.Role;
import com.example.blog.entities.User;
import com.example.blog.respositories.UserRepository;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BlogApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    /**
     *
     * @param builder
     * @param repository
     * @param userService
     * @throws Exception
     */

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
        if (repository.count()==0)
            userService.save(new User("admin", "adminPassword", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ADMIN"))));
        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    /**
     * @param repository
     * @return
     */

    private UserDetailsService userDetailsService(UserRepository repository) {
        return username -> new CustomUserDetails(repository.findByUsername(username));
    }

}
