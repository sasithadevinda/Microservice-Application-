package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.dto.request.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    public void placeOrder(OrderRequest orderRequest){
        Order order =new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
       // orderRequest.getOrder

    }
}
