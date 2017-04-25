<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Management</title>
</head>
<body>
	<c:if test="${not empty order}">
	
	<table>
	  <tr>
	    <td> Order Id: ${order.getOrderId()}</td>	
	  </tr>
	  <tr>
	    <td> Order Date: ${order.getOrderDate()}</td>
	  </tr>
	  <tr>
	    <td> Order Status: ${order.getOrderStatus()}</td>
	  </tr>	
	  <tr>
	    <td> 
	         Order Items: 
	           Item, Price, Quantity
             <c:forEach var="listValue" items="${items}">
				<li>${listValue.getMenuItem().getName()},  ${listValue.getMenuItem().getPrice()},  ${listValue.getQuantity()}  </li>
			</c:forEach>
		</td>
	  </tr>	

	</table>

	</c:if>
	   <!--  <button type="button" onclick="location = 'saveOrder'">Test</button>
	    <button type="button" name = "button1" onclick="location = '${controller.saveOrder(order)}'">Save Order</button>
	    <button type="submit" name = "button2" onclick="location = '${controller.submitOrder(order)}'">Submit Order</button>  
	  	-->
</body>
</html>


