package com.example.backend_1;

import java.util.List;

import org.springframework.stereotype.Service;

@Service 
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User createUserFunction(User userToCreate) {
        
        var newUser = new UserEntity(
            null,
            userToCreate.name(),
            userToCreate.age(),
            userToCreate.position(),
            userToCreate.salary()
        );
        
        var newUserEntity = repository.save(newUser);
        return converteEntity(newUserEntity);

    }


    public User converteEntity(UserEntity userEntity) {
        User userConverted = new User(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getAge(),
            userEntity.getPosition(),
            userEntity.getSalary()
        );

        return userConverted;
    }

    public List<User> showUsersInfo() {
        List<UserEntity> usersData = repository.findAll();
        
        List<User> usersDataConverted = usersData.stream()
            .map(this::converteEntity)
            .toList();

        return usersDataConverted;
    }
    
}
