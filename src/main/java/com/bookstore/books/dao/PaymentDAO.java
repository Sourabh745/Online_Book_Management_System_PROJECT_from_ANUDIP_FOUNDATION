package com.bookstore.books.dao;

import java.util.List;

import com.bookstore.books.entities.Payment;

public interface PaymentDAO {
	
    public Payment getPaymentById(int id);

    public List<Payment> getAllPayments();

    public List<Payment> getPaymentsByUser(int userId);

	public Payment updatePayment(int id, String newPaymentMethod, double newAmount);

    public boolean deletePayment(int id);

	public Payment createPayment(int orderId, String paymentMethod, double amount);
}
