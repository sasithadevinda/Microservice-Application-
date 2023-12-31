package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.dto.other.InventoryResponse;
import com.programmingtechie.orderservice.dto.other.OrderLineItemsDTO;
import com.programmingtechie.orderservice.dto.request.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest) throws IllegalAccessException {
        Order order =new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsDTOList = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDTO).collect(Collectors.toList());


        order.setOrderLineItems(orderLineItemsDTOList);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());
        System.out.println("#######"+Arrays.toString(skuCodes.toArray()));
        InventoryResponse[] isAvailable = webClient.get().
                uri("http://localhost:8085/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class)
                .block();
        System.out.println(Arrays.stream(isAvailable).toArray()+"???????????");
        boolean allProductInStock = Arrays.stream(isAvailable).allMatch(InventoryResponse::getInStock);
if(allProductInStock){
    orderRepository.save(order);
}else {
    throw new IllegalAccessException("Product is in stock , Please Try Agin");
}

    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems= new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQty(orderLineItemsDTO.getQty());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }
}
