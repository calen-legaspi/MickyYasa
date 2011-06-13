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
		Customer ID: <input name = "customerid" style="height: 33px; ">
		<br>
		<br>
		<br>
		Product: <select name = "products" style="width: 170px; height: 32px">
			<option value="none">Select Product</option>
		<% if(session.getAttribute("listOfProductsInStock") !=null){ 
			List<InventoryItem> items = (List<InventoryItem>)session.getAttribute("listOfProductsInStock");
			for(InventoryItem product : items){ %>
				<option value="<%=product.getSKUNumber()%>"><%=product.getProductName() %></option>
			<% }
		}%>
		</select>     
		Quantity: <input name = "quantity" style="height: 33px; ">   
		<input type="submit" name = "addorders" value="Add Order" style="height: 32px; "><br><br><br>
		</form>
		Orders:
		<br>
		<table border = "1">
			<tr>
				<td width="200" align="center">Name</td>
				<td>Quantity</td>
				<td width = "200" align = "center">Price</td>
				<td width = "200px"></td>
			</tr>
			<% if(session.getAttribute("listOfOrderedItems") != null){
				List<OrderItem> orderitems = (List<OrderItem>) session.getAttribute("listOfOrderedItems");
				for(OrderItem orderitem: orderitems){
					%>
					<tr>
						<td width="200" align="center"><%=orderitem.getProductName() %></td>
						<td><%= orderitem.getQuantity() %></td>
						<td width = "200" align = "center"><%=orderitem.getProductPrice().doubleValue() %></td>
						<td width = "200px">
							<form>
							<input type = "submit" name = "deleteitem" value = "Delete">
							</form>
						</td>
					</tr>
				<%}
			}%>
		</table>
	
</body>
</html>