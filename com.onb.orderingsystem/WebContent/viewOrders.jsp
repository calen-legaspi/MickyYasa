<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection, domainmodel.*, java.util.*, java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" /> 
<title>Ordering System</title>
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
			<form method = "POST" action = "OrderDetails">
			
			<%
			Set<Order> orderList = new HashSet<Order>();
			if(request.getAttribute("listOfOrder")!=null){
			orderList = (HashSet<Order>) request.getAttribute("listOfOrder");
			}
			Customer customer  = (Customer)request.getAttribute("customer");
	   		%>
	   	
	   		<h2>Orders of <%= customer.getFirstName() + " " + customer.getLastName() %></h2>
	
			<table border = 1>
				<tr>
					<td align = "center">Order Number</td>
					<td align = "center">Order Date</td>
					<td align = "center">Total Cost</td>
					<td align = "center">Action</td>
				</tr>
		
				<% for(Order order : orderList){ int orderNumber = order.getOrderNumber(); %>
				
				<tr>
					<td align = "center">
						<%= orderNumber%>
						
					</td>
					<td align = "center">
						<%= order.getDateofOrderCreation().getTime() %>
					</td>
					<td align = "center">
						<%= order.computeTotalCost(customer.getCreditLimit().intValue()).doubleValue()%>
					</td>
					<td align = "center">
						<input type = "submit" name = "<%= orderNumber %>" value = "View" />
						<input type = "hidden" value = "<%= orderNumber%>" name = "orderNum"/>
						
					</td>
						
				</tr>
				<% } %>
			</table>
			</form>	 
			<center>

		</div>
    	<div id="side" class="fl">
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