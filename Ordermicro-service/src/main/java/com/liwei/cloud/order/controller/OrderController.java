package com.liwei.cloud.order.controller;



import com.liwei.cloud.order.entity.Order;
import com.liwei.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/order/{orderId}")
    public Order queryOrderByid(@PathVariable("orderId") String orderId){
        return orderService.queryOrderById(orderId);
    }
    @GetMapping(value = "order2/{orderId}")
    public Order queryOrderById2(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderByIdx(orderId);
    }

}
