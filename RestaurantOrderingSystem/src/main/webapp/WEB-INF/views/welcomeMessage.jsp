<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Welcome</p>
	
	<a href="/RestaurantOrderingSystem/manageStaff">Manage Staff</a> <br/>
	
	<a href="/RestaurantOrderingSystem/viewMenu">View Menu</a><br/>
	
	<a href="/RestaurantOrderingSystem/manageOrder">Start Order</a><br/>
	
	 <form class="form-inline" action="/RestaurantOrderingSystem/logout" method="post">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>