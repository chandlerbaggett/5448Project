<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order history</title>
</head>
<style>
	table, th, td {
    	border: 1px solid black;
	}
</style>
<body>
	<c:if test="${isRestaurant}">
		<h1>Placed Orders</h1>
	</c:if>
	<c:if test="${!isRestaurant}">
		<h1>Your order history</h1>
	</c:if>
	
<table>
		<tr>
			<th>Order id</th>
			<th>order date</th>
			<th>status</th>
			<th>total</th>
		</tr>
		<c:if test="${not empty orders}">
			<c:forEach var="order" items="${orders}">
				<tr>
					<td>${order.getId()}</td>
					<td>${order.getFormattedDate()}</td>
					<td>${order.getOrderStatus()}</td>
					<td>${order.calculateOrderTotal()}</td>
					<c:if test="${isRestaurant}">
						<td>
							<form action="/RestaurantOrderingSystem/order/complete" method = "POST">
								<input name="submittype" type="submit" value="mark complete" />
								<input type="hidden" name="id" value="${order.getId()}" />
							</form>
						</td>
					</c:if>
					<c:if test="${!isRestaurant}">
						<td>
							<form action="/RestaurantOrderingSystem/order/duplicate" method = "POST">
								<input name="submittype" type="submit" value="resubmit" />
								<input type="hidden" name="id" value="${order.getId()}" />
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>