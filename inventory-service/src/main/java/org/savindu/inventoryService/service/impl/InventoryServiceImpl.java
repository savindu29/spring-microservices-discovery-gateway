package org.savindu.inventoryService.service.impl;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.savindu.inventoryService.model.Inventory;
import org.savindu.inventoryService.repository.InventoryRepository;
import org.savindu.inventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public boolean isInStock(String skuCode) {
        Optional<Inventory> bySkuCode = inventoryRepository.findBySkuCode(skuCode);
        if(bySkuCode.isPresent()){
            Inventory inventory = bySkuCode.get();
            log.info("Inventory found: {}", inventory);
            return inventory.getQuantity() > 0;
        }
        log.info("Inventory not found for skuCode: {}", skuCode);
        return false;

    }
}
