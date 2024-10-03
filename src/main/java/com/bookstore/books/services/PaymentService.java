package com.bookstore.books.services;

import java.util.List;

import com.bookstore.books.entities.Payment;

public interface PaymentService {
    
	public Payment createPayment(int orderId, String paymentMethod, double amount);

	public Payment getPaymentById(int id);

    public List<Payment> getAllPayments();

    public List<Payment> getPaymentsByUser(int userId);

    public boolean deletePayment(int id);

	public Payment updatePayment(int id, String newPaymentMethod, double newAmount);

}

