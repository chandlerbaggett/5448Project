<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Staff Management</title>
</head>
<body>
	<c:if test="${not empty lists}">
		<ul>
			<c:forEach var="listValue" items="${lists}">
				<li>${listValue.getUser().getDisplayName()}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>

