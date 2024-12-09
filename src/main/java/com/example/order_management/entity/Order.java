package com.example.order_management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Represents a customer order in the order management system.
 *
 * The Order class stores information about an individual order, including
 * the product ordered, the quantity, the total price for the order, and
 * the current status of the order. The class is annotated with JPA
 * annotations to map the class to a database table.
 *
 * Key fields include:
 * - id: The unique identifier of the order, which is auto-generated.
 * - productName: The name of the product ordered.
 * - quantity: The quantity of the product ordered.
 * - totalPrice: The total price of the order calculated based on the
 *   quantity and product price.
 * - status: The current status of the order, which uses the OrderStatus
 *   enumeration.
 *
 * Use instances of this class to track and manage order information
 * within the application. The class utilizes Lombok annotations for
 * boilerplate code reduction and JPA annotations for persistence.
 */
@Entity
@Data
@Table(name = "`orders`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int quantity;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
