package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.OrderItems;

public interface OrderItemService{
    
    public OrderItems addOrderItem(int orderId, int bookId, int quantity);

    public OrderItems getOrderItemById(int id);

    public List<OrderItems> getAllOrderItemsForOrder(int orderId);

    public OrderItems updateOrderItem(int id, int newQuantity);

    public boolean deleteOrderItem(int id);
}
