package com.example.order_management.controller;

import com.example.order_management.entity.Order;
import com.example.order_management.entity.OrderStatus;
import com.example.order_management.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing orders.
 * Provides endpoints for retrieving, creating, updating, and deleting orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Order with ID " + id + " has been deleted."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Order with ID " + id + " not found."));
        }
    }

    @PostMapping("/send-to-product-b")
    public ResponseEntity<?> sendOrdersToProductB() {
        orderService.sendProcessedOrdersToProductB();
        return ResponseEntity.ok(Map.of("message", "Processed orders sent to Product B"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Order>> fetchOrdersFromProductA() {
        List<Order> orders = orderService.fetchOrdersFromProductA();
        return ResponseEntity.ok(orders);
    }


}
