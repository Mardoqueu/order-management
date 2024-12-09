package com.example.order_management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * ProductAClient is a Feign client interface for communicating with an external system A.
 * It provides methods to interact with the order-related endpoints of the external system.
 * The client is configured to connect to a specific URL as defined in the application properties.
 */
@FeignClient(name = "productAClient", url = "${external.system.a.url}")
public interface ProductAClient {
    @GetMapping("/orders")
    List<Map<String, Object>> fetchOrdersFromProductA();
}
