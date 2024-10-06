package com.bookstore.books.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private int paymentID;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    private Orders order;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "PaymentDate")
    private Date paymentDate;

    @Column(name = "Amount")
    private double amount;

	public Payment(int paymentID, Orders order, String paymentMethod, Date paymentDate, double amount) {
		super();
		this.paymentID = paymentID;
		this.order = order;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}
	
	public Payment(String paymentMethod, double amount) {
	    this.paymentMethod = paymentMethod;
	    this.amount = amount;
	    this.paymentDate = new Date(); // Automatically set to current date
	}


	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
	    return "Payment [paymentID=" + paymentID + ", orderID=" + (order != null ? order.getOrderID() : "N/A") +
	           ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}

    
    // Getters and Setters
}
