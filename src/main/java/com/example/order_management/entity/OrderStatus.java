package com.example.order_management.entity;

/**
 * Represents the various stages of an order's lifecycle within the system.
 *
 * This enumeration is used to track the state of an order as it progresses
 * through the different phases from initial placement to being handed off for fulfillment.
 *
 * The different stages include:
 * - PENDING: The order has been created but not yet processed.
 * - PROCESSED: The order has been reviewed and processed.
 * - SENT_TO_B: The order has been sent to a business partner or another system
 *   for further handling or fulfillment.
 */
public enum OrderStatus {
    PENDING, PROCESSED, SENT_TO_B
}
