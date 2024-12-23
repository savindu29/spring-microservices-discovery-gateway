package org.savindu.orderService.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
@Data
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Foreign key column for Order
    private Order order;
}

