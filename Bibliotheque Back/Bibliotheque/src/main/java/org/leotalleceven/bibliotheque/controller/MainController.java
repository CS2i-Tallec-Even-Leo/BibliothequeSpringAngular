package org.leotalleceven.bibliotheque.controller;

import org.leotalleceven.bibliotheque.entity.User;
import org.leotalleceven.bibliotheque.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MainController {

    @RestController
    @RequestMapping("/api/users")
    @CrossOrigin(origins = "http://localhost:4200") // Angular
    public class UserController {

        private final UserRepository repo;

        public UserController(UserRepository repo) {
            this.repo = repo;
        }

        @GetMapping
        public List<User> getAllUsers() {
            return repo.findAll();
        }

        @PostMapping
        public User createUser(@RequestBody User user) {
            return repo.save(user);
        }
    }

}
