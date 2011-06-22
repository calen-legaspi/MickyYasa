package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onb.domainmodel.*;
import com.onb.services.*;
import com.onb.services.impl.OrderServiceImpl;


/**
 * Servlet implementation class OrderDetails
 */
@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetails() {
        super();
        // TODO Auto-generated constructor stub
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
		int selectedOrderNumber = Integer.parseInt(request.getParameter("orderIndex"));
		OrderServiceImpl orderService = new OrderServiceImpl();
		
		Order order = orderService.retrieveOrderFromDB(selectedOrderNumber);
		
		List<OrderItem> listOfOrderItems = order.getItems();
		
		request.setAttribute("listOfOrderItems", listOfOrderItems);

			
		RequestDispatcher view = request.getRequestDispatcher("orderDetails.jsp");
		view.forward(request, response);
		
	}

}
