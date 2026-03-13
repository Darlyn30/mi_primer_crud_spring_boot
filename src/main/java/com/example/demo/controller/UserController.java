package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    //i guess this is like DI
    @Autowired
    private UserService userService;

    //types of HTTP methods
    //GET
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/find-by/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        User newUser = userService.setUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        Optional<User> user = userService.getUserById(id);

        if(user != null){
            userService.deleteUser(id);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
