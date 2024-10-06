package com.bookstore.books.controllers;

import java.util.List;

import com.bookstore.books.dao.OrderItemDAO;
import com.bookstore.books.dao.implementation.OrderItemDAOImplementation;
import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.services.OrderItemService;

public class OrderItemController {
    private OrderItemDAO orderItemDAO;

    public OrderItemController() {
        // Assuming you have an implementation of OrderItemDAO (like OrderItemDAOImplementation)
        this.orderItemDAO = new OrderItemDAOImplementation();
    }

    // Adds an item to an order and returns the created OrderItem object
    public OrderItems addOrderItem(int orderId, String bookId, int quantity) {
        try {
            // Validate the inputs (orderId, bookId, and quantity)
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return null;
            }

            // Call DAO to add the order item
            OrderItems orderItem = orderItemDAO.addOrderItem(orderId, bookId, quantity);
            if (orderItem != null) {
                System.out.println("Order item added successfully: " + orderItem);
            } else {
                System.out.println("Failed to add order item.");
            }

            return orderItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns an OrderItem object by its ID
    public OrderItems getOrderItemById(int id) {
        try {
            // Call DAO to fetch the order item by ID
            OrderItems orderItem = orderItemDAO.getOrderItemById(id);
            if (orderItem != null) {
                System.out.println("Order item found: " + orderItem);
            } else {
                System.out.println("Order item not found.");
            }

            return orderItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Returns a list of all order items for a specific order
    public List<OrderItems> getAllOrderItemsForOrder(int orderId) {
        try {
            // Call DAO to fetch all order items for a specific order
            List<OrderItems> orderItems = orderItemDAO.getAllOrderItemsForOrder(orderId);
            if (orderItems != null && !orderItems.isEmpty()) {
                System.out.println("Order items for order ID " + orderId + ":");
                for (OrderItems item : orderItems) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No order items found for order ID " + orderId);
            }

            return orderItems;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Updates an order item and returns the updated OrderItem object
    public OrderItems updateOrderItem(int id, int newQuantity) {
        try {
            if (newQuantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return null;
            }

            // Call DAO to update the order item
            OrderItems updatedItem = orderItemDAO.updateOrderItem(id, newQuantity);
            if (updatedItem != null) {
                System.out.println("Order item updated successfully: " + updatedItem);
            } else {
                System.out.println("Failed to update order item.");
            }

            return updatedItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deletes an order item by its ID and returns a boolean indicating success or failure
    public boolean deleteOrderItem(int id) {
        try {
            // Call DAO to delete the order item
            boolean isDeleted = orderItemDAO.deleteOrderItem(id);
            if (isDeleted) {
                System.out.println("Order item deleted successfully.");
            } else {
                System.out.println("Failed to delete order item.");
            }

            return isDeleted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
