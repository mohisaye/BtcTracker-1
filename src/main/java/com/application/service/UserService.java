package com.application.service;

import com.application.entities.User;
import com.application.repositories.UserRepository;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    User user;
    @Autowired
    UserRepository userRepository;

    

    public void saveUserInfo(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
    }
}
