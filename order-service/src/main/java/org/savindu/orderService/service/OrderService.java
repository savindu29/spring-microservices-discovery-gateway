package org.savindu.orderService.service;

import org.savindu.orderService.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);

}
