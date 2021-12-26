package com.application.controllers;

import com.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return (List<User>) userRepository.findAll();
//    }
//
//    @PostMapping("/users")
//    void addUser(@RequestBody User user) {
//        userRepository.save(user);
//    }

    @GetMapping("/users/{user}/{pass}")
    public ResponseEntity<String> checkAuthorized(@PathVariable String user, @PathVariable String pass) {
        int res=-1;
        if (       !(user.equals("null") || user.trim().equals(""))
                && !(pass.equals("null") || pass.trim().equals(""))) {
            user = user.toLowerCase().trim();
            pass = pass.toLowerCase().trim();        
            service.saveUserInfo(user, pass);
        }else{
            res=320;
        }
        if(res==320){
            return new ResponseEntity<>(
                    String.valueOf(res),
                    HttpStatus.NOT_ACCEPTABLE);
        }else if (res != 200) {
            return new ResponseEntity<>(
                    String.valueOf(res),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
