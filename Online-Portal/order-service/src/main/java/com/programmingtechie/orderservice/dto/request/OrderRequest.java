package com.programmingtechie.orderservice.dto.request;

import com.programmingtechie.orderservice.dto.other.OrderLineItemsDTO;
import com.programmingtechie.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsDTO> orderLineItemsDTOList;
}
