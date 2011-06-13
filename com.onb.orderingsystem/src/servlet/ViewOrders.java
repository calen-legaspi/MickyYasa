package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CustomerService;
import controller.OrderService;
import domainmodel.Customer;
import domainmodel.Order;

/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrders() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int selectedCustomer = Integer.parseInt(request.getParameter("customer"));
		
		CustomerService customerService = new CustomerService();
		Customer customer = customerService.getCustomer(selectedCustomer);
		
		OrderService orderService = new OrderService();		
		Set<Order> listOfOrder = (HashSet<Order>) orderService.retrieveOrdersFromDB(customer);
		
		request.setAttribute("listOfOrder", listOfOrder);
		request.setAttribute("customer", customer);
		RequestDispatcher view = request.getRequestDispatcher("viewOrders.jsp");
		view.forward(request, response);
	}

}
