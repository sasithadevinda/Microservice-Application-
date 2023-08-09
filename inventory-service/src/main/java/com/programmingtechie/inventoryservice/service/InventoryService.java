package com.programmingtechie.inventoryservice.service;

import com.programmingtechie.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {
    private final InventoryRepository  inventoryRepository;

    @Transactional(readOnly = true)
public Boolean isInStock(String skuCode){
return inventoryRepository.findBySkuCode(skuCode).isPresent();
}
}