package com.bookstore.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


public class CommonUtility {

	public static void errorMessage(String message,
			HttpServletRequest request) 
					throws ServletException, IOException {
		request.setAttribute("message", message);
	}
	
	public static void successMessage(String message,
			HttpServletRequest request) 
					throws ServletException, IOException {
		request.setAttribute("message", message);
		request.setAttribute("success", "success");
	}
	
}

