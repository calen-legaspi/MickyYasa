<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection, domainmodel.*, java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<br/>
	<br/>

	<h3>
		Order History
	</h3>

	<form method = "POST" action = "OrderDetails">
		<%
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		if(request.getAttribute("listOfOrderItems")!=null){
			orderItemList = (ArrayList<OrderItem>) request.getAttribute("listOfOrderItems");
		}

	   	%>

		<table border = 1>
					<tr>
						<td align = "center">
							Order Number
						</td>
						<td align = "center">
							Quantity
						</td>
						<td align = "center">
							Price
						</td>
					</tr>
		
			<% if(orderItemList != null){
				for(OrderItem orderItem : orderItemList){  %>
					
					<tr>
						<td align = "center">
							<%= orderItem.getPriceSKUNumber() %>
						</td>
						<td align = "center">
						<%= orderItem.getQuantity() %>
						</td>
						<td align = "center">
						<%= orderItem.getCost() %>
						</td>
						
					</tr>
			<% }
			}%>
		</table>
	</form>	 
	 
	</center>
</body>
</html>