package com.example.order_management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * ProductBClient is a Feign client interface for interacting with
 * the external system B. It provides methods for communication
 * with Product B's API services.
 */
@FeignClient(name = "productBClient", url = "${external.system.b.url}")
public interface ProductBClient {
    @PostMapping("/orders")
    ResponseEntity<Map<String, String>> sendOrdersToProductB(@RequestBody Map<String, Object> orderData);
}
