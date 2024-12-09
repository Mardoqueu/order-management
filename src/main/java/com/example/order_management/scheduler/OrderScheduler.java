package com.example.order_management.scheduler;

import com.example.order_management.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for scheduling tasks related to order processing
 * at fixed intervals. It makes use of the Spring's scheduling capabilities
 * to automatically execute tasks in the background.
 *
 * The OrderScheduler includes tasks for:
 * - Fetching and processing orders from a source at a fixed rate.
 * - Sending processed orders to an external system or completing further processing.
 *
 * It relies on the OrderService to handle the core business logic associated with
 * order operations.
 */
@Component
public class OrderScheduler {
    private final OrderService orderService;

    public OrderScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void fetchAndProcessOrders() {
       // orderService.fetchAndProcessOrders();
    }

    @Scheduled(fixedRate = 60000) // Every 1 minute
    public void sendProcessedOrders() {
        //orderService.sendProcessedOrders();
    }
}
