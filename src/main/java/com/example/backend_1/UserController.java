package com.example.backend_1;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
        @RequestBody User userToCreate
    ) {
        // userService.createUserFunction(userToCreate);
        // log.info("function createUser was called.");

        return ResponseEntity.ok()
        .header("Create user function", "user created.")
        .body(userService.createUserFunction(userToCreate));

    }

    @GetMapping("/show")
    public ResponseEntity<List<User>> showUsersInfo() {
        return ResponseEntity.ok(userService.showUsersInfo());
    }

    @PutMapping("/update")
    public void updateUserData(User userToUpdate) {
        userService.updateUserData(userToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(
        @PathVariable Long id) {
        userService.deleteUser(id);
    }
}
