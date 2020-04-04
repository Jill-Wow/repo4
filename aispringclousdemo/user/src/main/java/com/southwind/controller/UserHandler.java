package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/findAll/{page}/{limit}")
//    public UserVo findAll(@PathVariable("page") int page, @PathVariable("limit") int limit){
//        UserVo userVo = new UserVo();
//        userVo.setCode(0);
//        userVo.setMsg("");
//        userVo.setCount(userRepository.count());
//        userVo.setData(userRepository.findAll((page-1)*limit,limit));
//        return userVo;
//    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index")int index,@PathVariable("limit") int limit){
        return userRepository.findAll(index,limit);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return userRepository.count();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
//        user.setRegisterdate(new Date());
        userRepository.save(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        userRepository.update(user);
    }

}
