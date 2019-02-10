package com.alex.cursospringudemy.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.alex.cursospringudemy.domain.Cliente;
import com.alex.cursospringudemy.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj); 
	
	void sendHtmlEmail(MimeMessage msg); 
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}


