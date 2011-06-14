<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection, domainmodel.*, java.util.*, java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" /> 
<title>Ordering System</title>
<script type="text/javascript">
	function viewOrderDetails(index){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action","OrderDetails");
		var idField = 	document.createElement("input");
		idField.setAttribute("type", "hidden");
		idField.setAttribute("name", "orderIndex");
		idField.setAttribute("value", index);
		form.appendChild(idField);
		document.body.appendChild(form);
		form.submit();
	}
</script>

</head>
<body>
<div id="bg">
	<div id="wrap">
		<div class="lb fl"></div>
		<div id="content" class="fl">
			<div id="header"> <img src="images/been.png" class="fr logo" />
        		<h1>orderingSystem 1.0</h1>
        		<div></div>
      		</div>
     	
     		<h2>Order History</h2>
     		<center>
			<form method = "POST" action = "OrderDetails" enctype="application/x-www-form-urlencoded">
			
			<%
			List<Order> orderList = new ArrayList<Order>();
			if(request.getAttribute("listOfOrder")!=null){
			orderList = (ArrayList<Order>) request.getAttribute("listOfOrder");
			}
			Customer customer  = (Customer)request.getAttribute("customer");
			
			double paid = 0, unpaid = 0; 
	   		%>
	   	
	   		<h2>Orders of <%= customer.getFirstName() + " " + customer.getLastName() %></h2>
	
			<table border = 1>
				<tr>
					<td align = "center">Order Number</td>
					<td align = "center">Order Date</td>
					<td align = "center">Total Cost</td>
					<td align = "center">Status</td>
					<td align = "center">Action</td>
				</tr>
		
				<% for(Order order : orderList){ 
					int orderNumber = order.getOrderNumber(); 
					double totalCost = order.computeTotalCost(customer.getCreditLimit().intValue()).doubleValue();
				%>
				
				<tr>
					<td align = "center">
						<%= orderNumber%>
					</td>
					<td align = "center">
						<%= order.getDateofOrderCreation().getTime() %>
					</td>
					<td align = "center">
						<%= totalCost %>
					</td>
					<td align = "center">
						<% if(!order.hasPaid()) { unpaid = unpaid + totalCost; %> Unpaid
						<% } else if (order.hasPaid()){ paid = paid + totalCost; %> Paid
						<% } %>
					</td>
					<td align = "center">
						<input type = "button" name = "View" value = "View" onclick = "viewOrderDetails(<%= order.getOrderNumber() %>)" />
					</td>
						
				</tr>
				<% } %>
			</table>
			
			<br />
			<br />
			Total Paid Orders: <%= paid %>
			<br />
			Total Unpaid Orders: <%= unpaid %>
			
			</form>	 
			<center>

		</div>
    	<div id="side" class="sidefl">
      		<div class="sinner">
	        <h2>Categories</h2>
	        <center>
	        <br />
	        <img src = "images/home.jpg" style="height: 78px; width: 90px; ">
	        <br />
	        <a href = "#" > Home </a>
	        <br /><br />
	        <img src="images/createorder.jpg" style="height: 78px; width: 90px; "/>
	        <br />
	        <a href = "CreateOrder" > Create Order </a>
	        <br /><br />
	        <img src="images/payment.jpg" style="height: 78px; width: 90px; "/>
	        <br />
	        <a href = "Payment" > Payment </a>
	        <br /><br />
	        <img src="images/history.gif" style="height: 78px; width: 90px; "/>
	        <br />
	        <a href = "OrderHistory" > Order History </a>
	        <br /><br />
	        </center>
      		</div>
   		 </div>
  		
  		 <div id="sider" class="fl">
  		 	<div class="sdinner"></div>
  		 </div>
  		 
  		 <!-- /content -->
  		 <div class="clearfix"> </div>
  		 <!-- /footer -->
	</div>
</div>
</body>