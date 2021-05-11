package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public User checkIfUsernameAlreadyExists(String username){

        return userMapper.getUsername(username);
    }

    public int createUser(User user){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        System.out.println("The username in Userservice is " + user.getUsername());
        System.out.println("The password in Userservice  is " + user.getPassword());
        System.out.println("The firstname in Userservice  is " + user.getFirstname());
        System.out.println("The lastname in Userservice  is " + user.getLastname());

        return userMapper.addUser(new User(null, user.getFirstname(), user.getLastname(), user.getUsername(), hashedPassword, encodedSalt));
    }


}
