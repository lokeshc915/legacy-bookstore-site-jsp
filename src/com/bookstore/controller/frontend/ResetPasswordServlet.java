package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.CommonUtility;
import com.bookstore.service.CustomerService;
import com.bookstore.util.EmailUtil;

import javax.servlet.ServletContext;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private String host;
	 private String port;
	 private String email;
	 private String name;
	 private String pass;
	 
	 public void init() {
	        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
	 }
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String recipient = request.getParameter("email");
		 String subject = "Your Password has been reset";
 
		 CustomerService customerService = new CustomerService();
		 String newPassword = customerService.resetCustomerPassword(recipient);
 
		 String content = "Hi, this is your new password: " + newPassword;
		 content += "\nNote: for security reason, "
                + "you must change your password after logging in.";
 
		 String message = "";
 
        try {
            EmailUtil.sendEmail(host, port, email, name, pass,
                    recipient, subject, content);
            message = "Your password has been reset. Please check your e-mail.";
        } catch (Exception ex) {
            ex.printStackTrace();
            message = "There were an error: " + ex.getMessage();
        } finally {
            CommonUtility.successMessage(message, request);
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
