<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import = "java.util.List, java.util.Date,com.onb.domainmodel.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" /> 
<title>Orange and Bronze Ordering System</title>
</head>
<script type="text/javascript">
	function deleteItem(index){
		var form = document.createElement("form");
		
		form.setAttribute("method", "post");
		form.setAttribute("action","CreateOrder");
		
		var idField = 	document.createElement("input");
		
		idField.setAttribute("type", "hidden");
		idField.setAttribute("name", "orderIndex");
		idField.setAttribute("value", index);
		
		form.appendChild(idField);
		document.body.appendChild(form);
		
		form.submit();
	}
	
</script>
<body>
<div id="bg">
  <div id="wrap">
    <div class="lb fl">
    </div>
    <div id="content" class="fl">
      <div id="header"> <img src="images/been.png" class="fr logo" />
        <h1>orderingSystem 1.0</h1>
        <div></div>
      </div>
	<form action="CreateOrder" method="post" enctype="application/x-www-form-urlencoded">
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
		<%}
			Order order = (Order)session.getAttribute("order");
		%>
		Date: 
		<% if(order !=null){
				Date date = order.getDateofOrderCreation().getTime();
		%>
			<input type="text" name = "date" value = "<%=date.toString() %>">
		<%} else{%>
			<input type="text" name = "date">
		<%}%>
		<br><br>
		Product: <select name = "products" style="width: 170px; height: 20px">
			<option value="none">Select Product</option>
		<% if(session.getAttribute("inventory") !=null){ 
			Inventory inventory = (Inventory) session.getAttribute("inventory");
			List<InventoryItem> items = inventory.getAllItemsInStock();
			for(InventoryItem product : items){ %>
				<option value="<%=product.getSKUNumber()%>"><%=product.getProductName() %></option>
			<% }
		}%>
		</select>     
		Quantity: <input type="text" name = "quantity" style="height: 20px; ">   
		<input type="submit" name = "addorders" value="Add Order" style="height: 20px; "><br><br><br>
		Orders:
		<br>
		
			<%if( order!= null){
				List<OrderItem> orderItems = order.getItems();
				%>
				<table border = "1">
					<tr>
						<td width="200" align="center">Name</td>
						<td>Quantity</td>
						<td width = "200" align = "center">Price</td>
						<td width = "200px"></td>
					</tr>
				<% for(OrderItem orderitem: orderItems){
					%>
					
					<tr>
						<td width="200" align="center"><%=orderitem.getProductName() %></td>
						<td><%= orderitem.getQuantity() %></td>
						<td width = "200" align = "center"><%=orderitem.getProductPrice().doubleValue() %></td>
						<td  width = "200px">
							<input type = "button" name = "deleteitem" value = "Delete" onclick="deleteItem(<%= orderItems.indexOf(orderitem) %>)">
						</td>
					</tr>
				<%}%>
				</table>
				<p> Total Price: <%=order.computeTotalCost(credLimit).doubleValue()%></p>
			<%}else{%>
				<table border = "1">
					<tr>
						<td width="200" align="center">Name</td>
						<td>Quantity</td>
						<td width = "200" align = "center">Price</td>
						<td width = "200px"></td>
					</tr>
				</table>
			<%} %>
			
		<p><input type = "submit" name = "Add" value="Finalize Order"></p>
		</form>
		<div id="footer">
        <p id="copyright">© 2011. All Rights Reserved. <br/>
          <a href="http://www.templatesold.com/" target="_blank">Website Templates</a> by <a href="http://www.free-css-templates.com/" target="_blank">Free CSS Templates</a></p>
      </div>
    </div>
		  <div id="side" class="sidefl">
      <div class="sinner">
     	<h2>Categories</h2>
      	<center>
        <br />
        <img src = "images/home.jpg" style="height: 78px; width: 90px; ">
        <br />
        <a href = "#" > Home </a>
        <br />
        <br />
        <img src="images/createorder.jpg" style="height: 78px; width: 90px; "/>
        <br />
        <a href = "CreateOrder" > Create Order </a>
        <br />
        <br />
        <img src="images/payment.jpg" style="height: 78px; width: 90px; "/>
        <br />
        <a href = "Payment" > Payment </a>
        <br />
        <br />
        <img src="images/history.gif" style="height: 78px; width: 90px; "/>
        <br />
        <a href = "OrderHistory" > Order History </a>
        <br />
        <br />
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
</html>