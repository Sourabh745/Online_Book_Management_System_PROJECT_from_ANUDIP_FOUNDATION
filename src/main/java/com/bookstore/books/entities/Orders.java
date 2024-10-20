package com.bookstore.books.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "OrderDate")
    private Date orderDate;

    @Column(name = "TotalCost")
    private double totalCost;

    @Column(name = "Status", length = 50)
    private String status;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItems> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments;

	public Orders(int orderID, User user, Date orderDate, double totalCost, String status, List<OrderItems> orderItems,
			List<Payment> payments) {
		super();
		this.orderID = orderID;
		this.user = user;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
		this.status = status;
		this.orderItems = orderItems;
		this.payments = payments;
	}
	
	public Orders(User user, double totalCost, String status, List<OrderItems> orderItems, List<Payment> payments, Date orderDate) {
		super();
		this.user = user;
		this.totalCost = totalCost;
		this.status = status;
		this.orderItems = orderItems;
		this.payments = payments;
		this.orderDate = orderDate;
	}
	
	public Orders(User user, double totalCost, String status, List<OrderItems> orderItems, Date orderDate) {
		super();
		this.user = user;
		this.totalCost = totalCost;
		this.status = status;
		this.orderItems = orderItems;
		this.orderDate = orderDate;
	}



	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public void addPayment(Payment payment) {
	    if (this.payments == null) {
	        this.payments = new ArrayList<>();
	    }
	    this.payments.add(payment);
	}
	
	@Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", userID=" + (user != null ? user.getUserID() : "null") +
                ", orderDate=" + orderDate +
                ", totalCost=" + totalCost +
                ", status='" + status + '\'' +
                ", itemCount=" + (orderItems != null ? orderItems.size() : 0) +
                ", paymentCount=" + (payments != null ? payments.size() : 0) +
                '}';
    }
    
    // Getters and Setters
}
