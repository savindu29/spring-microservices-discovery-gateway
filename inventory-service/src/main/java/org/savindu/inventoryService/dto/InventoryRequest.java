package org.savindu.inventoryService.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private String skuCode;
    private Long quantity;
}
