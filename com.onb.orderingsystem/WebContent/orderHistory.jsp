<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection, domainmodel.Customer, java.util.List, java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<img src = "images/os.png">
	<form method = "POST" action = "ViewOrders">
	<br/>
	<br/>

	<%
	
	List<Customer> customerList = new ArrayList<Customer>();
	if(request.getAttribute("listOfCustomer")!=null){
		customerList = (List<Customer>) request.getAttribute("listOfCustomer");
	}
   	%>
   	
   	<select name = "customer" style="width: 295px; height: 32px">
		
		<% for(Customer customer : customerList){ %>
			<option value="<%= customer.getID() %>"> <%= customer.getLastName() + ", " + customer.getFirstName() %></option>
		<% } %>
		
		</select>    
	<input type="submit" value="OK"><br/>
	<br/>
	
	</form>
	 
	</center>

</body>
</html>