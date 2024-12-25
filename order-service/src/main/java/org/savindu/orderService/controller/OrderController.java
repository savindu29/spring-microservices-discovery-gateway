package org.savindu.orderService.controller;

import lombok.RequiredArgsConstructor;
import org.savindu.orderService.dto.OrderRequest;
import org.savindu.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-service/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping

    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            String s = orderService.placeOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(s);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
