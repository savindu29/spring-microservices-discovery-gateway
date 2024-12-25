package org.savindu.orderService.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.savindu.orderService.dto.InventoryResponse;
import org.savindu.orderService.dto.OrderLineItemDto;
import org.savindu.orderService.dto.OrderRequest;
import org.savindu.orderService.dto.SkuRequest;
import org.savindu.orderService.model.Order;


import org.savindu.orderService.model.OrderLineItem;
import org.savindu.orderService.repository.OrderRepository;
import org.savindu.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public String placeOrder(OrderRequest orderRequest) throws RuntimeException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList().stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto, order)) // Pass the order reference
                .toList();

        order.setOrderLineItemList(orderLineItems);

        // create a list of SkuRequest
        List<SkuRequest> skuRequests = orderLineItems.stream().map(
                orderLineItem -> new SkuRequest(orderLineItem.getSkuCode(), orderLineItem.getQuantity()
        )).toList();

        // call inventory service to check the stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().post()
                .uri("http://inventory-service/api/inventory-service/inventory")
                .body(Mono.just(skuRequests), List.class)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean b = Arrays.stream(
                inventoryResponses
        ).allMatch(InventoryResponse::isInStock);
        if(!b){
            log.error("Items are not in stock");
            throw new RuntimeException("Items are not in stock");

        }else{
            log.info("Items are in stock and order is placed");
            orderRepository.save(order);
            return "Order placed successfully";
        }

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
