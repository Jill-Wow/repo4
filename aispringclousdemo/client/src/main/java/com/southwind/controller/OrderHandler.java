package com.southwind.controller;

import com.southwind.entity.Menu;
import com.southwind.entity.Order;
import com.southwind.entity.OrderVo;
import com.southwind.entity.User;
import com.southwind.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;


    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        orderFeign.save(order);
        return "order";

    }

    @GetMapping("/findAllByUId")
    @ResponseBody
    public OrderVo findAllByUId(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int index = (page - 1) * limit;
        return orderFeign.findAllByUId(index, limit, user.getId());

    }

    @GetMapping("/findAll")
    @ResponseBody
    public OrderVo findAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        int index = (page - 1) * limit;
        return orderFeign.findAll(index, limit);
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") long id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }


}
