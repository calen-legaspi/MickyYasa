package servlet;

import java.io.IOException;
import java.util.Collections;
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
import com.onb.impl.CustomerServiceImpl;
import com.onb.impl.OrderServiceImpl;


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
		if(!(checkParameters(request))){
			int selectedCustomer = Integer.parseInt(request.getParameter("customer"));
			
			Customer customer = CustomerServiceImpl.getCustomer(selectedCustomer);
			
			OrderServiceImpl orderService = new OrderServiceImpl();		
			List<Order> listOfOrder = (List<Order>) orderService.retrieveOrdersFromDB(customer);
			Collections.sort(listOfOrder);
			Collections.reverse(listOfOrder);
			request.setAttribute("listOfOrder", listOfOrder);
			request.setAttribute("customer", customer);
			RequestDispatcher view = request.getRequestDispatcher("viewOrders.jsp");
			view.forward(request, response);
		}
	}
	
	private boolean checkParameters(HttpServletRequest request){
		if(request.getAttribute("customer") == null) {
			return false;
		}else return true;		
	}

}
