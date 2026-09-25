package com.example.backend_1;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend_1.exception.UserDoesntExistException;

@Service 
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User createUserFunction(User userToCreate) {

        if(userToCreate.age() < 18) {
            throw new IllegalArgumentException("error: age is very young.");
        }

        if(userToCreate.salary() < 0) {
            throw new IllegalArgumentException("error: very small salary.");
        }
        
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

    public User updateUserData(User userToUpdate) throws UserDoesntExistException {

        var userNotUpdatedOptional = repository.findById(userToUpdate.id());

        if(userNotUpdatedOptional.isEmpty()) {
            throw new UserDoesntExistException("User doesnt exist. ");
        }

        var userNotUpdated = userNotUpdatedOptional.get();

        var userToUpdateEntity = new UserEntity(
            userNotUpdated.getId(),
            userToUpdate.name(),
            userToUpdate.age(),
            userToUpdate.position(),
            userToUpdate.salary()
        );

        repository.save(userToUpdateEntity);

        return converteEntity(userToUpdateEntity);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User checkIdUser(Long id) {
        var user = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User does not exist."));
        
        var checkedUser = converteEntity(user);
        return checkedUser;
    }

    
}
