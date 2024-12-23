package org.savindu.inventoryService.controller;



import org.savindu.inventoryService.dto.InventoryResponse;
import org.savindu.inventoryService.dto.InventoryRequest;
import org.savindu.inventoryService.dto.SkuRequest;
import org.savindu.inventoryService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestBody List<SkuRequest> skuCodes){
        return inventoryService.isInStock(skuCodes);
    }

//    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.createInventory(inventoryRequest);
    }
    @PutMapping("/{skuCode}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void updateInventory(@PathVariable String skuCode ,@PathVariable Long quantity) {
        inventoryService.updateInventory(skuCode,quantity);
    }
}
