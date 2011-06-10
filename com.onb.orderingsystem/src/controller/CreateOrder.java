package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.*;
import domainmodel.*;
import java.util.*;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet(description = "Servlet for Creating Orders", urlPatterns = { "/CreateOrder" })
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		InventoryDAO inventoryDao = (InventoryDAO)ctx.getBean("InventoryDao");
		List<InventoryItem> items = inventoryDao.retrieveInventoryItemList();
		request.setAttribute("listOfProductsInStock", items);
		if(checkParameters(request)){
			//TO DO fill up later
		}else{
			RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
			view.forward(request, response);
		}
	}

	private boolean checkParameters(HttpServletRequest request){
		if(request.getParameter("customerid") ==null){
			return false;
		}else if(request.getParameter("products") == null){
			return false;
		}else if(request.getParameter("quantity") == null){
			return false;
		}else return true;
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
