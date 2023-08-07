package com.programmingtechie.orderservice.dto.request;

import com.programmingtechie.orderservice.dto.other.OrderLineItemsDTO;
import com.programmingtechie.orderservice.model.OrderLineItems;

import java.util.List;

public class OrderRequest {
    private List<OrderLineItemsDTO> orderLineItemsDTOList;
}
