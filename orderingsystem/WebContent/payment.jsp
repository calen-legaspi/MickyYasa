<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.util.Collection,com.onb.domainmodel.*, java.util.*, java.util.ArrayList" %>
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
     	
     		<h2>Payment of Orders</h2>
			
			<form method = "POST" action = "UnpaidOrders">
				<br/><br/>
		
				<%
				
				List<Customer> customerList = new ArrayList<Customer>();
				if(request.getAttribute("listOfCustomer")!=null){
					customerList = (List<Customer>) request.getAttribute("listOfCustomer");
				}
			   	%>
			   	
		   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		Select Customer: <br/><br/>
		   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   		
		   		<select name = "customer" style="width: 295px; height: 32px">
				
				<% for(Customer customer : customerList){ %>
					<option value="<%= customer.getID() %>"> <%= customer.getLastName() + ", " + customer.getFirstName() %></option>
				<% } %>
				
				</select>  
				  
				<input type="submit" value="OK"><br/>
				<br/>
			
			</form>
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
  		
  		 <div id="sider" class="sidefl">
  		 	<div class="sdinner"></div>
  		 </div>
  		 
  		 <!-- /content -->
  		 <div class="clearfix"> </div>
  		 <!-- /footer -->
	</div>
</div>
</body>