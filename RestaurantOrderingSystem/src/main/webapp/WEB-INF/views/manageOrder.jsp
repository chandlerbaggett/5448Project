<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	table, th, td {
    	border: 1px solid black;
	}
</style>
<title>Order Management</title>
</head>
<body>
    <b>Manage Order</b>
	 <form action="/RestaurantOrderingSystem/manageOrder/submit" th:object="${order}" method="post">
	 	<p><input type="submit" value="Submit Order" /></p>
	 	<input type="hidden" name="id" value="${order.getId()}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>  
	 <form action="/RestaurantOrderingSystem/manageOrder/cancel" th:object="${order}" method="post">
	 	<p><input type="submit" value="Cancel Order" /></p>
	 	<input type="hidden" name="id" value="${order.getId()}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>    
    <form action="/RestaurantOrderingSystem/manageOrder/save" th:object="${order}" method="post">
	 	<p><input type="submit" value="Save Order" /></p>
	 	<input type="hidden" name="id" value="${order.getId()}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>  
    <form action="/RestaurantOrderingSystem/manageOrder/resume" th:object="${order}" method="post">
	 	<p><input type="submit" value="Restore Order to Last Save" /></p>
	 	<input type="hidden" name="id" value="${order.getId()}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>      
	<table>
		<tr>
			<th>Order Id</th>
			<th>Order Date</th>
			<th>Order Status</th>
			<th>Order Total</th>
		</tr>
		<tr>
			<td>${order.getOrderId()}</td>
			<td>${order.getOrderDate()}</td>
			<td>${order.getOrderStatus()}</td>	
			<td>${order.calculateOrderTotal()}</td>						
		</tr>
	</table>
	<table>
		<tr>
			<th>Order Item</th>
			<th>Quantity</th>
		</tr>
		<c:if test="${not empty items}">
			<c:forEach var="listValue" items="${items}">
				<tr>
					<td>${listValue.getMenuItem().getName()}</td>
					<td>${listValue.getQuantity()}</td>
					<td>
						<form action="/RestaurantOrderingSystem/manageOrder/remove" th:object="${orderItem}" method = "post" >
							<input name="submittype" type="submit" value="Remove" />
							<input type="hidden" name="id" value="${listValue.getId()}" />	
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	 <form action="/RestaurantOrderingSystem/manageOrder/add" th:object="${createOrderItem}" method="post">
	 	<p>Add MenuItem to Order:  <input type="text" name="description"/></p>
	 	<p><input type="submit" value="Add" /> <input type="reset" value="Reset" /></p>
	 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>


