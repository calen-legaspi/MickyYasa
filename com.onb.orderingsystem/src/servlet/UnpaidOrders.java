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

import controller.CustomerService;
import controller.OrderService;
import domainmodel.Customer;
import domainmodel.Order;

/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/UnpaidOrders")
public class UnpaidOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		if(isPayButtonClicked(request.getParameter("orderIndex"))){
			int selectedOrderNumber = Integer.parseInt(request.getParameter("orderIndex"));
			Order order = OrderService.retrieveOrderFromDB(selectedOrderNumber);
			order.pay();
			OrderService.updateStatusOfOrderInDB(order);
		}
		if(!(checkParameters(request))){
			HttpSession session = request.getSession();
			Customer customer = (Customer) session.getAttribute("customer");
			
			OrderService orderService = new OrderService();		
			List<Order> listOfOrder = orderService.retrieveUnpaidOrders(customer);
			
			request.setAttribute("listOfOrder", listOfOrder);
			session.setAttribute("customer", customer);
			RequestDispatcher view = request.getRequestDispatcher("unpaidOrders.jsp");
			view.forward(request, response);
		}
	}
	
	private boolean isPayButtonClicked(String requestParameter){
		if(requestParameter == null){
			return false;
		}else return true;
	}
	
	private boolean checkParameters(HttpServletRequest request){
		if(request.getAttribute("customer") == null) {
			return false;
		}else return true;		
	}

}
