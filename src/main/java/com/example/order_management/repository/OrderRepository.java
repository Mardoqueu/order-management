package com.example.order_management.repository;

import com.example.order_management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository provides the mechanism for storage, retrieval, update, and delete
 * operations on Order entities using Spring Data JPA.
 *
 * This interface extends JpaRepository, leveraging its capabilities to interact
 * with the underlying database in a standardized and efficient manner.
 *
 * As a repository interface, it serves as the data access layer component for
 * performing CRUD (Create, Read, Update, Delete) operations and more complex
 * queries on Order entities.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
