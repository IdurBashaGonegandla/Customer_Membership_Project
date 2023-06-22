package com.palle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.palle.dao.AdminDao;
import com.palle.dao.CustomerDao;
import com.palle.model.Customer;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
    String path = request.getServletPath();

		switch (path) 
		{
		case "/delete":
			deleteCustomer(request, response);
			break;
		case "/edit" :
			editCustomer(request, response);
			break;
		case "/editForm":
			getEditForm(request, response);
			break;
		case "/insertform":
			getInsertForm(request, response);
			break;
		case "/add":
			addCustomer(request, response);
			break;
		case "/list":
			validateAdmin(request, response);
			break;
		case "/table":
			getCustomerListPage(request, response);
			break;
			

		default:
			getStartUpPage(request, response);
			break;

		}

	}
	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			ArrayList<Customer> alCustomer = CustomerDao.getAllCustomers();
	
			RequestDispatcher rd = request.getRequestDispatcher("customer_list.jsp");
			request.setAttribute("al", alCustomer);
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{

			e.printStackTrace();
		}
		catch (IOException e) 
		{

			e.printStackTrace();
		}
		
	}
	private void validateAdmin(HttpServletRequest request, HttpServletResponse response )
	{
		// Read the username and password
		String u = request.getParameter("tbUser");
		String p = request.getParameter("tbPass");
		 
		
		// call the dao method to validate admin
		boolean res = AdminDao.validateAdmin(u , p);
		
		//Condition to redirect admin to list page
		if(res)
		{
			getCustomerListPage(request, response);
		}
		else 
		{
			//getStartUpPage(request, response);
			
			try 
			{
				response.sendRedirect(request.getContextPath()+"/default");
			} catch (IOException e) 
			{
				
				e.printStackTrace();
			}
			
		}
		
		
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		// Read the id from url
		int i = Integer.parseInt(request.getParameter("id"));
		
		// call the dao method to delete the row in dataBase
		CustomerDao.deleteCustomer(i);
		
		getCustomerListPage(request, response);
	}

	private void editCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		int i = Integer.parseInt(request.getParameter("tbId"));
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c = new Customer(i, n, e, m);
		
		CustomerDao.editCustomer(c);
		
		getCustomerListPage(request, response);
		
		
		
		
		
	}

	private void getEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		//Fetch the id from url:
		int i = Integer.parseInt(request.getParameter("id"));
		
		Customer c = CustomerDao.getOneCustomer(i);
	
		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			request.setAttribute("customer", c);
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
	}

	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) 
	{

		try 
		{
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);

		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		// Reading the data from Customer_form page
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));

		// Store the admin given data into model/Object
		Customer c = new Customer(n, e, m);

		// Insert customer data to DataBase
		CustomerDao.addCustomer(c);

		// Redirect Admin to HomePage(customer_list page)
		getCustomerListPage(request, response);
		

		
	}

	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		doGet(request, response);
	}

}
