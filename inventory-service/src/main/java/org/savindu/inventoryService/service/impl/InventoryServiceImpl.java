package org.savindu.inventoryService.service.impl;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.savindu.inventoryService.dto.InventoryRequest;
import org.savindu.inventoryService.dto.InventoryResponse;
import org.savindu.inventoryService.dto.SkuRequest;
import org.savindu.inventoryService.model.Inventory;
import org.savindu.inventoryService.repository.InventoryRepository;
import org.savindu.inventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public List<InventoryResponse> isInStock(List<SkuRequest> skuCode) {
        List<Inventory> bySkuCodeIn = inventoryRepository.findBySkuCodeIn(
                skuCode.stream().map(SkuRequest::getSkuCode).toList()
        );

        List<InventoryResponse> result = new ArrayList<>();

        for (SkuRequest skuRequest : skuCode) {
            boolean inStock = bySkuCodeIn.stream()
                    .anyMatch(inventory ->
                            inventory.getSkuCode().equals(skuRequest.getSkuCode())
                                    && inventory.getQuantity() >= skuRequest.getQuantity()
                    );

            result.add(new InventoryResponse(skuRequest.getSkuCode(), inStock));
        }

        return result;
    }

    @Override
    public void createInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
    }

    @Override
    public void updateInventory(String skuCode, Long quantity) {
        Inventory bySkuCode = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new RuntimeException("Inventory not found"));
        bySkuCode.setQuantity(quantity);
        inventoryRepository.save(bySkuCode);

    }
}
