package com.bookstore.books.services.implementation;

import java.util.List;

import com.bookstore.books.dao.PaymentDAO;
import com.bookstore.books.dao.implementation.PaymentDAOImplementation;
import com.bookstore.books.entities.Payment;
import com.bookstore.books.services.PaymentService;

public class PaymentServiceImplementation implements PaymentService{

	private PaymentDAO paymentDAO;
	
	public PaymentServiceImplementation() {
		super();
		this.paymentDAO = new PaymentDAOImplementation();
	}

	@Override
	public Payment getPaymentById(int id) {
		// TODO Auto-generated method stub
		return paymentDAO.getPaymentById(id);
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentDAO.getAllPayments();
	}

	@Override
	public List<Payment> getPaymentsByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deletePayment(int id) {
		// TODO Auto-generated method stub
		return paymentDAO.deletePayment(id);
	}

	@Override
	public Payment createPayment(int orderId, String paymentMethod, double amount) {
		// TODO Auto-generated method stub
		return paymentDAO.createPayment(orderId, paymentMethod, amount);
	}

	@Override
	public Payment updatePayment(int id, String newPaymentMethod, double newAmount) {
		// TODO Auto-generated method stub
		return paymentDAO.updatePayment(id, newPaymentMethod, newAmount);
	}

}
