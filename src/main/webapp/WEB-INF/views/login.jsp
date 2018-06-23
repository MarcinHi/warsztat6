<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Login</title>
</head>

<body>
	<div>
		<c:if test="${param.loggedout != null}">
			<p>Succesfully logged out!!!</p>
		</c:if>
	</div>
	<div>
		<h2>Login!</h2>
		<c:if test="${param.error != null}">
			<p>Invalid credentials!!!</p>
		</c:if>
		<form method="post" action="/modul6/login">
			<input type="text" name="username" placeholder="Enter username" >
			<input type="password" name="password" placeholder="Enter password"> 
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<input type="submit" value="Log in">
		</form>
	</div>
	
	<div>
		<h2>Register!</h2>	
		<form:form action="/modul6/register" method="post" modelAttribute="user">
			<form:input path="userName" placeholder="Enter username"/>
			<form:input path="password" placeholder="Enter password" type="password"/>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<input type="submit" value="Register">
		</form:form>
	</div>
</body>
</html>