package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers(){
        var users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUserById(int id){
        var user = userRepository.findById(id);
        return user;
    }

    public User setUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id){

        userRepository.deleteById(id);
    }
}
