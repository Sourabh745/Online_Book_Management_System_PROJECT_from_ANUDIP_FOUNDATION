package com.bookstore.books.controllers;

import java.util.List;
import java.util.Scanner;

import com.bookstore.books.entities.OrderItems;
import com.bookstore.books.entities.Orders;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.entities.User;
import com.bookstore.books.services.OrderService;
import com.bookstore.books.services.UserService;
import com.bookstore.books.services.implementation.OrderServiceImplementation;
import com.bookstore.books.services.implementation.UserServiceImplementation;

public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private UserController userController;
    private Scanner scanner;
    private User loggedInUser;

    public OrderController() {
        this.orderService = new OrderServiceImplementation();
        this.userService = new UserServiceImplementation();
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null;  // No user logged in initially
    }

    public void showMenu(User loggedInUser) {
    	this.loggedInUser = loggedInUser; // Set logged-in user
        
        while (true) {
            if (this.loggedInUser == null) {
                System.out.println("You need to login first.");
                userController.loginUser(); // After login, set the loggedInUser
                this.loggedInUser = userController.loginUser();  // Assuming this method returns the logged-in user
            }  else {
                System.out.println("Order Menu:");
                System.out.println("1. Create Order");
                System.out.println("2. View All Orders");
                System.out.println("3. View Order by ID");
                System.out.println("4. Update Order");
                System.out.println("5. Delete Order");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        createOrder(loggedInUser);
                        break;
                    case 2:
                        viewAllOrders();
                        break;
                    case 3:
                        viewOrderById();
                        break;
                    case 4:
                        updateOrder();
                        break;
                    case 5:
                        deleteOrder();
                        break;
                 
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
    

    // Create Order
    public void createOrder(User loggedInUser) {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to create an order.");
            return;
        }

        System.out.print("Enter payment method (only Cash on Delievery Now): ");
        String payMethod = scanner.nextLine();
        Payment payments  = new Payment();
        payments.setPaymentMethod(payMethod);

        System.out.println("Enter order items (Enter 0 to stop):");
        List<OrderItems> orderItems = orderService.collectOrderItems();  // Collect order items from the user

        // Calculate total cost 
        double totalCost = orderItems.stream().mapToDouble(item -> item.getPrice()).sum();

        // Set order status (initially pending)
        String status = "Pending";

        // Create the order
        Orders newOrder = orderService.createOrder(loggedInUser, orderItems, payments, totalCost, status);

        if (newOrder != null) {
            System.out.println("Order created successfully: " + newOrder);
        } else {
            System.out.println("Failed to create order.");
        }
    }
    

    // View All Orders (for the logged-in user)
    public void viewAllOrders() {
        if (loggedInUser.getUsername() != "admin") {
            System.out.println("You must be logged in to view your orders.");
            return;
        }

        List<Orders> orders = orderService.getOrdersByUser(loggedInUser.getUserID());
        if (orders != null && !orders.isEmpty()) {
            System.out.println("Your Orders:");
            for (Orders order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("No orders found.");
        }
    }

    // View Order by ID (for the logged-in user)
    public void viewOrderById() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to view your orders.");
            return;
        }

        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Orders order = orderService.getOrderById(orderId);

        if (order != null && order.getUser().getUserID() == loggedInUser.getUserID()) {
            System.out.println("Order details: " + order);
        } else {
            System.out.println("Order not found or you don't have permission to view this order.");
        }
    }


    // Update Order (for the logged-in user)
    public void updateOrder() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to update your orders.");
            return;
        }

        System.out.print("Enter order ID to update: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); 

        //retrieve order that already exist in database
        Orders existingOrder = orderService.getOrderById(orderId);
        
        if (existingOrder == null ) {
            System.out.println("Order not found .");
            return;
        }
        else if(existingOrder.getUser().getUserID() != loggedInUser.getUserID()) {
        	System.out.println("you don't have permission to update this order.");
            return;	
        }

        System.out.print("Enter updated payment method (only Cash on delievery now: ");
        String updatedPaymentMethod = scanner.nextLine();
        
        List<Payment> payments = existingOrder.getPayments();
        if (!updatedPaymentMethod.isEmpty()) {
            Payment updatedPayment = new Payment();
            updatedPayment.setPaymentMethod(updatedPaymentMethod);
            payments.add(updatedPayment);  // Add the new payment to the list
        }

        System.out.println("Enter updated order items (Enter 0 to stop):");
        List<OrderItems> updatedOrderItems = orderService.collectOrderItems();  // Collect updated order items

        // Calculate updated total cost
        double updatedTotalCost = updatedOrderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        // Set updated status (optional, you can prompt for this)
        String updatedStatus = "Updated";  // Or get this from user input

//        Orders updatedOrder = orderService.updateOrder(orderId, updatedOrderItems, payments, updatedTotalCost, updatedStatus);
//        if (updatedOrder != null) {
//            System.out.println("Order updated successfully: " + updatedOrder);
//        } else {
//            System.out.println("Failed to update order.");
//        }
    }

    // Delete Order (for the logged-in user)
    public void deleteOrder() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to delete your orders.");
            return;
        }

        // Step 1: Fetch and display all orders for the logged-in user
        List<Orders> userOrders = orderService.getOrdersByUser(loggedInUser.getUserID());
        if (userOrders.isEmpty()) {
            System.out.println("You have no orders to delete.");
            return;
        }

        System.out.println("Your Orders:");
        for (Orders order : userOrders) {
            System.out.println("Order ID: " + order.getOrderID() + ", Total: " + order.getTotalCost() + ", Date: " + order.getOrderDate());
        }

        // Step 2: Prompt the user to select an order to delete
        System.out.print("Enter the order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Step 3: Check if the order belongs to the user
        Orders orderToDelete = orderService.getOrderById(orderId);
        if (orderToDelete == null || orderToDelete.getUser().getUserID() != loggedInUser.getUserID()) {
            System.out.println("Order not found or you don't have permission to delete this order.");
            return;
        }

        // Step 4: Delete the order
        boolean success = orderService.deleteOrder(orderId);
        if (success) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Failed to delete order.");
        }
    }

}
