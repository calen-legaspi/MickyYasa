<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection, domainmodel.*, java.util.Set, java.util.HashSet, java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<img src = "images/os.png">
	<br/>
	<br/>

	<h2>
	Orders
	</h2>
	 
	<%
	
	Set<Order> orderList = new HashSet<Order>();
	if(request.getAttribute("listOfOrder")!=null){
		orderList = (HashSet<Order>) request.getAttribute("listOfOrder");
	}
   	%>
	<table border = 1>
				<tr>
					<td align = "center">
						Order Date
					</td>
					<td align = "center">
						Total Cost
					</td>
					<td align = "center">
						Action
					</td>
				</tr>
	
		<% for(Order order : orderList){ %>
				<tr>
					<td align = "center">
					<%= order.getDateofOrderCreation().getTime() %>
					</td>
					<td align = "center">
					<%= order.computeTotalCost().doubleValue()%>
					</td>
					<td align = "center">
						<input type="submit" value="View" name = "<%= order.getOrderNumber() %>">
					</td>
				</tr>
		<% } %>
	</table>
	
	 
	</center>

</body>
</html>