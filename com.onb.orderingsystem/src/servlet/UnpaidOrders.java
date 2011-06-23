package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.CustomerService;
import controller.OrderService;
import controller.impl.CustomerServiceImpl;
import controller.impl.OrderServiceImpl;
import domainmodel.Customer;
import domainmodel.Order;

/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/UnpaidOrders")
public class UnpaidOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnpaidOrders() {
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
		OrderService orderservice = applicationContext.getBean("OrderService", OrderService.class);
		if(isPayButtonClicked(request.getParameter("orderIndex"))){
			int selectedOrderNumber = Integer.parseInt(request.getParameter("orderIndex"));
			Order order = orderservice.retrieveOrderFromDB(selectedOrderNumber);
			order.pay();
			orderservice.updateStatusOfOrderInDB(order);
		}
		HttpSession session = request.getSession();
		Customer customer;
		if(!(checkParameters(request))){
			customer = (Customer) session.getAttribute("customer");
		}else{
			CustomerService customerservice = applicationContext.getBean("CustomerService", CustomerService.class);
			customer = customerservice.getCustomer(Integer.parseInt(request.getParameter("customer")));
		}
		OrderService orderService = new OrderServiceImpl();		
		List<Order> listOfOrder = orderService.retrieveUnpaidOrders(customer);
		
		request.setAttribute("listOfOrder", listOfOrder);
		session.setAttribute("customer", customer);
		RequestDispatcher view = request.getRequestDispatcher("unpaidOrders.jsp");
		view.forward(request, response);	
	}
	
	private boolean isPayButtonClicked(String requestParameter){
		if(requestParameter == null){
			return false;
		}else return true;
	}
	
	private boolean checkParameters(HttpServletRequest request){
		if(request.getParameter("customer") == null) {
			return false;
		}else return true;		
	}

}
