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

    public void updateUserData(User userToUpdate) {
        var userNotUpdatedOptional = repository.findById(userToUpdate.id());

        var userNotUpdated = userNotUpdatedOptional.get();

        var userToUpdateEntity = new UserEntity(
            userNotUpdated.getId(),
            userToUpdate.name(),
            userToUpdate.age(),
            userToUpdate.position(),
            userToUpdate.salary()
        );

        repository.save(userToUpdateEntity);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    
}
