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
<title>Staff Management</title>
</head>
<body>
	<table>
		<tr>
			<th>Username</th>
		</tr>
		<c:if test="${not empty lists}">
			<c:forEach var="listValue" items="${lists}">
				<tr>
					<td>${listValue.getUser().getDisplayName()}</td>
					<td>
						<form action="/RestaurantOrderingSystem/manageStaff/remove" method = "POST" th:object="${listValue.getUser()}">
							<input name="submittype" type="submit" value="remove" />
							<input type="hidden" name="id" value="${listValue.getUser().getId()}" />
							<input type="hidden" name="userName" value="${listValue.getUser().getUserName()}" />
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>


