package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.CustomerService;
import controller.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domainmodel.Customer;
import domainmodel.Order;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHistory() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!(checkParameters(request))){
			CustomerService customerService = new CustomerService();
			List<Customer> listOfCustomer = customerService.getCustomerList();

			request.setAttribute("listOfCustomer", listOfCustomer);		
			
			RequestDispatcher view = request.getRequestDispatcher("orderHistory.jsp");
			view.forward(request, response);
		}
	}
	

	private boolean checkParameters(HttpServletRequest request){
		if(request.getAttribute("customer") == null) {
			return false;
		}else return true;		
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
		RequestDispatcher view = request.getRequestDispatcher("viewOrders.jsp");
		view.forward(request, response);
		
	}

}
