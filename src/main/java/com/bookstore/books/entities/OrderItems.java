package com.bookstore.books.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemID")
    private int orderItemID;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "ISBN", nullable = false)
    private Book book;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private double price;

	public OrderItems(int orderItemID, Orders order, Book book, int quantity, double price) {
		super();
		this.orderItemID = orderItemID;
		this.order = order;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(int orderItemID) {
		this.orderItemID = orderItemID;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItems [orderItemID=" + orderItemID + ", order=" + order + ", book=" + book + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

    
    // Getters and Setters
}
