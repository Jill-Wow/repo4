package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.entity.OrderVo;
import com.southwind.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        return "order的端口：" + this.port;
    }

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/save")
    public void save(@RequestBody Order order) {
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUId/{index}/{limit}/{uid}")
    public OrderVo findAllByUId(@PathVariable("index") int index, @PathVariable("limit") int limit, @PathVariable("uid") long uid) {
        OrderVo orderVo = new OrderVo();
        orderVo.setMsg("");
        orderVo.setCount(orderRepository.countByUId(uid));
        orderVo.setData(orderRepository.findAllByUId(index, limit, uid));
        return orderVo;
    }

    @GetMapping("/countByUId/{uid}")
    public int countByUId(@PathVariable("uid") long uid) {
        return orderRepository.countByUId(uid);
    }

    @GetMapping("/findAll/{index}/{limit}")
    public OrderVo findAll(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        OrderVo orderVo = new OrderVo();
        orderVo.setMsg("");
        orderVo.setCount(orderRepository.count());
        orderVo.setData(orderRepository.findAll(index, limit));
        return orderVo;
    }

    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") long id) {
        orderRepository.updateState(id);


    }
}
