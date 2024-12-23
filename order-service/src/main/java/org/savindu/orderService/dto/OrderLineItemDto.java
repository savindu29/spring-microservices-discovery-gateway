package org.savindu.orderService.dto;


import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class OrderLineItemDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
