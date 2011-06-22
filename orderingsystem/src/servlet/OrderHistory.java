package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.domainmodel.Customer;
import com.onb.domainmodel.Order;
import com.onb.services.CustomerService;
import com.onb.services.OrderService;


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
		
	}

}
