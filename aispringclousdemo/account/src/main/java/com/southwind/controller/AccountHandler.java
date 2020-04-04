package com.southwind.controller;

import com.southwind.repository.AdminRepository;
import com.southwind.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("type") String type) {
        Object object = new Object();

//        switch (type) {
//            case "user":
//                object = userRepository.login(username, password);
//
//                break;
//            case "admin:":
//                object = adminRepository.login(username, password);
//                break;
//        }

        if (type.equals("user")) {
            object = userRepository.login(username, password);

        } else if (type.equals("admin")) {
            object = adminRepository.login(username, password);

        }

        return object;
    }


}
