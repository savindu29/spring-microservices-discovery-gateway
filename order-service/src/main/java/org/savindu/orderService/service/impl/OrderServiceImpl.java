package org.savindu.orderService.service.impl;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.savindu.orderService.dto.OrderLineItemDto;
import org.savindu.orderService.dto.OrderRequest;
import org.savindu.orderService.model.Order;


import org.savindu.orderService.model.OrderLineItem;
import org.savindu.orderService.repository.OrderRepository;
import org.savindu.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(orderRequest.getOrderNumber());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList().stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto, order)) // Pass the order reference
                .toList();

        order.setOrderLineItemList(orderLineItems);

        Order save = orderRepository.save(order);
        log.info("Order created: {}", save.getId());
        return "Order created successfully";
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto, Order order) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setOrder(order);
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItem;


    }

}
