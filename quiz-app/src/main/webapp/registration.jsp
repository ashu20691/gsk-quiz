<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico" />
<title>GSK - Quiz</title>
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
		<h2>GSK - Quiz | Registration</h2>
	</div>
<div class="container">

    <form:form method="POST" modelAttribute="userForm" >
			<div class="lc-block">

       <spring:bind path="Email">
            <div class="${status.error ? 'has-error' : ''}">
                <form:input type="text" path="fullName" class="style-4" placeholder="Full Name"
                            autofocus="true"></form:input>
                <form:errors path="fullName"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="Email">
            <div class="${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="style-4" placeholder="Email"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="style-4" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="style-4"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <input type="submit" class="button red small" value="Register"/>
		</div> <!-- end lc-block -->
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
