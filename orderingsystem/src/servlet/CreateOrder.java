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

import com.onb.daos.*;
import com.onb.domainmodel.*;
import com.onb.services.*;

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
		List<InventoryItem> items = InventoryService.getAllAvailableProductsInDB();
		Inventory inventory = new Inventory(items);
		session.setAttribute("inventory", inventory);
		RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
		view.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(isDeleteButtonClicked(request.getParameter("orderIndex"))){
			Order order = getOrder(request);
			int itemIndex = Integer.valueOf(request.getParameter("orderIndex"));
			OrderService.deleteOrderItem(order, itemIndex);
			session.setAttribute("order", order);
		}else if(checkParameters(request)){
			Customer customer  = CustomerService.getCustomer(Integer.valueOf(request.getParameter("customerid")));
			if(customer !=null){
				session.setAttribute("customer", customer);
				Order order = getOrder(request);
				int quantity = Integer.valueOf(request.getParameter("quantity"));
				int productNumber = Integer.valueOf(request.getParameter("products"));
				Product product = ProductService.getProduct(productNumber);
				Inventory inventory = (Inventory)session.getAttribute("inventory");
				InventoryItem item = InventoryService.getInventoryItem(inventory, product);
				if(InventoryService.checkQuantity(item, quantity)){
					inventory.deduct(item, quantity);
					OrderItem orderItem = new OrderItem(quantity,product); 
					order.addItem(orderItem);	
					session.setAttribute("order", order);
				}
			}
		}else if(isAddOrderButtonClicked(request.getParameter("Add"))){
			Order order = getOrder(request);	
			OrderService.addOrderToDB(order);
			session.removeAttribute("customer");
			session.removeAttribute("order");
		}
		RequestDispatcher view = request.getRequestDispatcher("createOrder.jsp");
		view.forward(request, response);
		
	}
	
	private Order getOrder(HttpServletRequest request){
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		if(order == null){
			Customer customer = (Customer)session.getAttribute("customer");
			int orderNumber = Integer.valueOf(OrderService.getLastOrderNumber());
			return new Order(customer, orderNumber+1);
		}else return order;	
	}
	
	private boolean checkParameters(HttpServletRequest request){
		if(request.getParameter("customerid")==null){
			return false;
		}else if(request.getParameter("products")==null){
			return false;
		}else if(request.getParameter("quantity")==null){
			return false;
		}else if(request.getParameter("customerid").equals("")){
			return false;
		}else if(request.getParameter("products").equals("none")){
			return false;
		}else if(request.getParameter("quantity").equals("")){
			return false;
		}else return true;	
	}
	
	private boolean isDeleteButtonClicked(String requestParameter){
		if(requestParameter == null){
			return false;
		}else return true;
	}
	
	private boolean isAddOrderButtonClicked(String requestParameter){
		if(requestParameter == null){
			return false;
		}else if(requestParameter.contains("Finalize Order")){
			return true;
		}else return false;
	}

}
