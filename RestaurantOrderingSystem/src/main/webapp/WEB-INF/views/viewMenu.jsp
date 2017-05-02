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
<title>View Menu</title>
</head>
<body>
	<table>
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
		</tr>
		<c:if test="${not empty lists}">
			<c:forEach var="listValue" items="${lists}">
				<tr>
					<td>${listValue.getMenuItem().getName()}</td>
					<td>${listValue.getMenuItem().getPrice()}</td>
					<td>${listValue.getMenuItem().getDescroption()}</td>
					<td>
						<form action="/RestaurantOrderingSystem/viewMenu/remove" th:object="${item}" method = "post" >
						<input name="submittype" type="submit" value="Remove" />
						<input type="hidden" name="id" value="${listValue.getId()}" />
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	 <form action="/RestaurantOrderingSystem/viewMenu/add" th:object="${addItem}" method="post">
	 	<p>Name:  <input type="text" name="name"/></p>
	 	<p>Price:  <input type="text" name="price"/></p>
	 	<p>Description: <input type="text" name="description"/></p>
	 	<p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
	 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>
