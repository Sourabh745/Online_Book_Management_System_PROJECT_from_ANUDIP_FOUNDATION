package com.bookstore.books.CallingMethods;

import java.util.*;

import com.bookstore.books.controllers.OrderItemController;
import com.bookstore.books.entities.OrderItems;

public class manageOrderItems{
	
	static 	Scanner scanner = new Scanner(System.in);
	private OrderItemController otc = new OrderItemController();

private void manageOrderItem() {
    System.out.println("Order Item Menu:");
    System.out.println("1. Add Order Item");
    System.out.println("2. View All Order Items");
    System.out.println("3. View Order Item by ID");
    System.out.println("4. Update Order Item");
    System.out.println("5. Delete Order Item");
    System.out.println("6. Back to Main Menu");
    System.out.print("Choose an option: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
        case 1:
            addOrderItem();
            break;
        case 2:
            viewAllOrderItems();
            break;
        case 3:
            viewOrderItemById();
            break;
        case 4:
            updateOrderItem();
            break;
        case 5:
            deleteOrderItem();
            break;
        case 6:
            return;
        default:
            System.out.println("Invalid option.");
    }
}

private void addOrderItem() {
    System.out.print("Enter Order ID: ");
    int orderId = scanner.nextInt();
    System.out.print("Enter Book ID: ");
    int bookId = scanner.nextInt();
    System.out.print("Enter Quantity: ");
    int quantity = scanner.nextInt();

    OrderItems orderItem = otc.addOrderItem(orderId, bookId, quantity);
    if (orderItem != null) {
        System.out.println("Order Item added: " + orderItem);
    } else {
        System.out.println("Failed to add order item.");
    }
}

private void viewAllOrderItems() {
	System.out.print("Enter Order ID: ");
    int orderId = scanner.nextInt();
	otc.getAllOrderItemsForOrder(orderId);
    
}

private void viewOrderItemById() {
    System.out.print("Enter Order Item ID: ");
    int orderItemId = scanner.nextInt();
    OrderItems orderItem = otc.getOrderItemById(orderItemId);
    if (orderItem != null) {
        System.out.println(orderItem);
    } else {
        System.out.println("Order item not found.");
    }
}

private void updateOrderItem() {
    System.out.print("Enter Order Item ID to update: ");
    int orderItemId = scanner.nextInt();
    System.out.print("Enter new Quantity: ");
    int newQuantity = scanner.nextInt();

    OrderItems updatedOrderItem = otc.updateOrderItem(orderItemId, newQuantity);
    if (updatedOrderItem != null) {
        System.out.println("Order Item updated: " + updatedOrderItem);
    } else {
        System.out.println("Failed to update order item.");
    }
}

private void deleteOrderItem() {
    System.out.print("Enter Order Item ID to delete: ");
    int orderItemId = scanner.nextInt();
    boolean success = otc.deleteOrderItem(orderItemId);
    if (success) {
        System.out.println("Order item deleted.");
    } else {
        System.out.println("Failed to delete order item.");
    }
}
}
