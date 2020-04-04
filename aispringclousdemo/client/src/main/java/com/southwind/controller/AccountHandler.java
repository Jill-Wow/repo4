package com.southwind.controller;

import com.southwind.entity.Admin;
import com.southwind.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.southwind.entity.User;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {
        Object object = accountFeign.login(username, password, type);
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) object;
        String result = null;
        String idStr = null;
        long id = 0L;
        if (object == null) {
            result = "login";
        } else {
            switch (type) {
                case "admin":
                    Admin admin = new Admin();
                    idStr = hashMap.get("id").toString();
                    id = Long.parseLong(idStr);
                    String userName = hashMap.get("username").toString();
                    admin.setId(id);
                    admin.setUsername(userName);
                    session.setAttribute("admin", admin);
                    result = "main";
                    break;
                case "user":
                    User user = new User();
                    idStr = hashMap.get("id").toString();
                    id = Long.parseLong(idStr);
                    String nickName = hashMap.get("nickname").toString();
                    user.setId(id);
                    user.setNickname(nickName);
                    session.setAttribute("user", user);
                    result = "index";
                    break;
            }
        }
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
