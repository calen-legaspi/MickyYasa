package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.InventoryService;
import controller.ProductService;

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
		HttpSession session = request.getSession(true);
		InventoryService inventory = new InventoryService();
		List<InventoryItem> items = inventory.getAllAvailableProductsInDB();
		session.setAttribute("listOfProductsInStock", items);
		RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
		view.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(checkParameters(request)){
			List<OrderItem> orderitems = getOrderItemsList(request);
			int quantity = Integer.valueOf(request.getParameter("quantity"));
			Product product = ProductService.getProduct(Integer.valueOf(request.getParameter("products")));
			OrderItem orderItem = new OrderItem(quantity,product); 
			orderitems.add(orderItem);
			session.setAttribute("listOfOrderedItems", orderitems);
		}
		RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
		view.forward(request, response);
	}
	
	private List<OrderItem> getOrderItemsList(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<OrderItem> orders = (List<OrderItem>)session.getAttribute("listOfOrderedItems");
		if(orders == null){
			return new ArrayList<OrderItem>();
		}else return orders;	
	}
	
	private boolean checkParameters(HttpServletRequest request){
		if(request.getParameter("customerid") == null){
			return false;
		}else if(request.getParameter("products").equals("none")){
			return false;
		}else if(request.getParameter("quantity") == null){
			return false;
		}else return true;
		
	}

}
