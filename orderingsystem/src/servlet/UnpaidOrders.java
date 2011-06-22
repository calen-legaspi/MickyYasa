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

import com.onb.domainmodel.Customer;
import com.onb.domainmodel.Order;
import com.onb.services.impl.CustomerServiceImpl;
import com.onb.services.impl.OrderServiceImpl;


/**
 * Servlet implementation class ViewOrders
 */
@WebServlet("/UnpaidOrders")
public class UnpaidOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
	CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	
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
			Order order = orderServiceImpl.retrieveOrderFromDB(selectedOrderNumber);
			order.pay();
			orderServiceImpl.updateStatusOfOrderInDB(order);
		}
		HttpSession session = request.getSession();
		Customer customer;
		if(!(checkParameters(request))){
			customer = (Customer) session.getAttribute("customer");
		}else{
			customer = customerServiceImpl.getCustomer(Integer.parseInt(request.getParameter("customer")));
		}
		OrderServiceImpl orderService = new OrderServiceImpl();		
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
