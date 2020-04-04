package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVo;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public UserVo findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        UserVo userVo = new UserVo(0, "", userFeign.count(), userFeign.findAll(index, limit));
        return userVo;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id) {
        userFeign.deleteById(id);
        return "redirect:/menu/redirect/user_manage";
    }

//    @GetMapping("/findTypes")
//    public ModelAndView findTypes() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("user_add");
//        mv.addObject("list", userFeign.findTypes());
//        return mv;
//    }

    @PostMapping("/save")
    public String save(User user) {
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView update(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user_update");
//        mv.addObject("list", userFeign.findTypes());
        mv.addObject("user", userFeign.findById(id));
        return mv;
    }

//    @PostMapping("/update")
//    public String update(User user){
//        userFeign.update(user);
//        return "redirect:/user/redirect/index";
//    }
}
