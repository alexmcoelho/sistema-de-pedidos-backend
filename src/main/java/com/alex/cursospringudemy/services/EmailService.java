package com.alex.cursospringudemy.services;

import org.springframework.mail.SimpleMailMessage;

import com.alex.cursospringudemy.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}


