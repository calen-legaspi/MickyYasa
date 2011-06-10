package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import controller.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domainmodel.Customer;
import domainmodel.Product;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(checkParameters(request)){
			//FIXME fill this up
		}else{
			CustomerService customerS = new CustomerService();
			List<Customer> listOfCustomer = customerS.getCustomerList(); //insert service here
			
			for(Customer customer : listOfCustomer){
				System.out.println(customer.getFirstName()+" "+customer.getLastName());
			}
			
			request.setAttribute("listOfCustomer", listOfCustomer);
			
			RequestDispatcher view = request.getRequestDispatcher("orderHistory.jsp");
			view.forward(request, response);
		}
	}
	

	private boolean checkParameters(HttpServletRequest request){
		if(request.getAttribute("customerid") == null) {
			return false;
		}else if(request.getAttribute("products") ==null){
			return false;
		}else if(request.getAttribute("quantity") ==null){
			return false;
		}else return true;		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
