<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>GSK - Quiz</title>
<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico" />
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="${contextPath}/resources/css/gskStyle.css">
  <link href="<c:url value="${contextPath}/resources/css/app.css" />" rel="stylesheet"
	type="text/css">
	
</head>
<body class="text-center">
<nav class="navbar navbar-default tembus">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>

      </div>

      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a class="navbar-brand" href="#" style="padding: 1px;"><img class="logoImg img-responsive" src="${contextPath}/resources/images/logo_gsk.png"></a></li>
        </ul>
      </div>
    </div>
  </nav>
 
	<div class="details">
		<h2>GSK - Quiz | Login</h2>
	</div>

	<form action="${contextPath}/login" method="post">

		<div class="lc-block">
			<div>
				<input type="text" class="style-4" name="username"
					placeholder="Email" autofocus="autofocus"/>
			</div>
			<div>
				<input type="password" class="style-4" name="password"
					placeholder="Password" />
			</div>
			<div>
				<input type="submit" value="Sign In" class="button red small" />
				<a href="/registration" class="button green small">Sign Up</a> 
			</div>
			<c:if test="${param.error ne null}">
				<div class="alert-danger">Invalid Email and password.</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div class="alert-normal">You have been logged out.</div>
			</c:if>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

 <script src="https://code.jquery.com/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/app.js"></script>
</body>
</html>