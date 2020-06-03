package com.alex.sistemadepedidos.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.alex.sistemadepedidos.security.UserSS;

public class UserService {

	/*Obtendo usuário logado*/
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}