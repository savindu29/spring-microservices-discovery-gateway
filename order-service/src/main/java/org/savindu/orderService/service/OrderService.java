package org.savindu.orderService.service;

import org.savindu.orderService.dto.OrderRequest;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    CompletableFuture<String> placeOrder(OrderRequest orderRequest);

}
