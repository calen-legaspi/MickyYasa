<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "java.util.List, domainmodel.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orange and Bronze Ordering System</title>
</head>
<body>
	<form action="CreateOrder" method="post">
		<center><img></center>
		Customer ID: 
		<% 	int credLimit =0;
			if(session.getAttribute("customer")!=null) {
			Customer customer = (Customer)session.getAttribute("customer");
			credLimit = customer.getCreditLimit().intValue();
		%>
			<input type="text" name = "customerid" value = "<%=customer.getID()%>"style="height: 20px; ">
			<br><br>
			<p>Name: <%=customer.getLastName()%>, <%=customer.getFirstName()%> <%=customer.getMiddleName() %></p>
		<%} else {%>
			<input type="text" name = "customerid" style="height: 20px; ">
			<br><br>
		<%} %>
		
		<br><br>
		Product: <select name = "products" style="width: 170px; height: 20px">
			<option value="none">Select Product</option>
		<% if(session.getAttribute("listOfProductsInStock") !=null){ 
			List<InventoryItem> items = (List<InventoryItem>)session.getAttribute("listOfProductsInStock");
			for(InventoryItem product : items){ %>
				<option value="<%=product.getSKUNumber()%>"><%=product.getProductName() %></option>
			<% }
		}%>
		</select>     
		Quantity: <input type="text" name = "quantity" style="height: 20px; ">   
		<input type="submit" name = "addorders" value="Add Order" style="height: 20px; "><br><br><br>
		Orders:
		<br>
		<table border = "1">
			<tr>
				<td width="200" align="center">Name</td>
				<td>Quantity</td>
				<td width = "200" align = "center">Price</td>
				<td width = "200px"></td>
			</tr>
			<% if(session.getAttribute("order") != null){
				Order order = (Order)session.getAttribute("order");
				List<OrderItem> orderItems = order.getItems();
				for(OrderItem orderitem: orderItems){
					%>
					<tr>
						<td width="200" align="center"><%=orderitem.getProductName() %></td>
						<td><%= orderitem.getQuantity() %></td>
						<td width = "200" align = "center"><%=orderitem.getProductPrice().doubleValue() %></td>
						<td width = "200px">
							<input type = "hidden" name = "orderIndex" value = "<%=orderItems.indexOf(orderitem)%>">
							<input type = "submit" name = "deleteitem" value = "Delete">
						</td>
					</tr>
				<%}%>
		</table>
				<p> Total Price: <%=order.computeTotalCost(credLimit).doubleValue()%></p>
			<%}%>
			<input type = "submit" name = "Add" value="Add Order">
		</form>
		 
</body>
</html>