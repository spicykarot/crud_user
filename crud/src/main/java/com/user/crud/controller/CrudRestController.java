package com.user.crud.controller;

import com.user.crud.model.User;
import com.user.crud.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CrudRestController {

    // Injecting the service
    @Autowired
    private CrudService service;

    ArrayList<User> user;

    // Method one, the old way of mapping requests
    // @RequestMapping(path ="/getUserlist",method = RequestMethod.GET)

    // Method 2 => directly straight to the point
    @GetMapping("/getUserlist")
    @CrossOrigin(origins = "http://localhost:4200")
    public ArrayList<User> fetchUserList() {
        // Logic to fetch list from database
        user = new ArrayList<User>();
        user = service.fetchUserFromRepository();
        return user;
    }

    @PostMapping("/addUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public User saveUser(@RequestBody User user) {
        // Logic to save the User into database
        return service.persistUserIntoRepository(user);
    }

//    @GetMapping("/getUserbyid/{id}")
//    @CrossOrigin(origins = "http://localhost:4200")
//    public User fetchUserById(@PathVariable long id) {
//        // Logic to get User by id from database
//        return service.fetchUserFromRepositoryById(id).get();
//    }

    @PostMapping("/editUser")
    @CrossOrigin(origins = "http://localhost:4200")
    public String updateUser(@RequestBody User User) {
        // Logic to get update User from database
        return service.updateUserInRepository(User);
    }

    @DeleteMapping("/deleteUser/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity deleteUser(@PathVariable long id) {
        // Logic to get delete User by id from database

        service.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
