package com.example.backend_1;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

@RestController 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping 
    public void createUser(
        @RequestBody User userToCreate
    ) {
        userService.createUserFunction(userToCreate);
        log.info("function createUser was called.");
    }

    @GetMapping 
    public List<User> showAllUsers() {
        return userService.showContent();
    }
}
