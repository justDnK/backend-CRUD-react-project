package com.example.backend_1;

import java.util.List;

import org.springframework.stereotype.Service;

@Service 
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public void createUserFunction(User userToCreate) {
        
        var newUser = new UserEntity(
            null,
            userToCreate.name(),
            userToCreate.info()
        );
        
        repository.save(newUser);

    }

    public List<User> showContent() {
        List<UserEntity> usersListEnteties = repository.findAll();

        List<User> allUsers = usersListEnteties.stream()
            .map(this::converterFunction)
            .toList();
        
        return allUsers;
    }

    public User converterFunction(UserEntity UserNotConverted) {
        var convertedUser = new User(
            UserNotConverted.getId(), 
            UserNotConverted.getName(), 
            UserNotConverted.getInfo()
        );

        return convertedUser;
    }
    
}
