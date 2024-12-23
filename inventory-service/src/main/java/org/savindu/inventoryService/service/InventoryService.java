package org.savindu.inventoryService.service;

import org.savindu.inventoryService.dto.InventoryRequest;
import org.savindu.inventoryService.dto.InventoryResponse;
import org.savindu.inventoryService.dto.SkuRequest;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<SkuRequest> skuCodes);

    void createInventory(InventoryRequest inventoryRequest);

    void updateInventory(String skuCode, Long quantity);
}
