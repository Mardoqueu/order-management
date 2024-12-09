package com.example.order_management.service;


import com.example.order_management.client.ProductAClient;
import com.example.order_management.client.ProductBClient;
import com.example.order_management.entity.Order;
import com.example.order_management.entity.OrderStatus;
import com.example.order_management.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for managing order operations.
 *
 * The OrderService class provides functionalities to:
 * - Retrieve all orders from the database.
 * - Retrieve a specific order by ID.
 * - Create a new order with an initial 'PENDING' status.
 * - Update the status of an existing order.
 * - Delete an order by its ID.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductAClient productAClient;
    private final ProductBClient productBClient;

    public OrderService(OrderRepository orderRepository, ProductAClient productAClient, ProductBClient productBClient) {
        this.orderRepository = orderRepository;
        this.productAClient = productAClient;
        this.productBClient = productBClient;
    }

    // Retrieve all orders from the database
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Retrieve a specific order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Create a new order with an initial 'PENDING' status
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    // Update the status of an existing order
    public Optional<Order> updateOrderStatus(Long id, OrderStatus status) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return orderRepository.save(order);
                });
    }

    // Delete an order by ID
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void sendProcessedOrders() {
        List<Order> processedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == OrderStatus.PROCESSED)
                .toList();

        processedOrders.forEach(order -> {
            // Logic to send to Product B
            Map<String, Object> orderData = Map.of(
                    "productName", order.getProductName(),
                    "quantity", order.getQuantity(),
                    "totalPrice", order.getTotalPrice()
            );
            productBClient.sendOrdersToProductB(orderData);

            // Update order status to SENT_TO_B
            order.setStatus(OrderStatus.SENT_TO_B);
            orderRepository.save(order);
        });
    }

    public List<Order> fetchOrdersFromProductA() {
        // Fetch orders from the Mocky API
        List<Map<String, Object>> externalOrders = productAClient.fetchOrdersFromProductA();

        // Map the external orders to internal Order entities
        return externalOrders.stream().map(orderData -> {
            Order order = new Order();
            order.setProductName(orderData.get("productName").toString());
            order.setQuantity((Integer) orderData.get("quantity"));
            order.setTotalPrice(new BigDecimal(orderData.get("totalPrice").toString()));
            order.setStatus(OrderStatus.PENDING); // Default status
            return orderRepository.save(order);
        }).toList();
    }

    public void sendProcessedOrdersToProductB() {
        // Fetch all processed orders
        List<Order> processedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == OrderStatus.PROCESSED)
                .toList();

        // Send each order to Product B
        processedOrders.forEach(order -> {
            Map<String, Object> orderData = Map.of(
                    "productName", order.getProductName(),
                    "quantity", order.getQuantity(),
                    "totalPrice", order.getTotalPrice()
            );

            ResponseEntity<Map<String, String>> response = productBClient.sendOrdersToProductB(orderData);

            if (response.getStatusCode().is2xxSuccessful()) {
                order.setStatus(OrderStatus.SENT_TO_B);
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Failed to send order to Product B");
            }
        });

    }

}
