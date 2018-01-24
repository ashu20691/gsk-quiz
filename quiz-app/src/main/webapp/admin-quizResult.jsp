<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">

<head>
<link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico" />
  <title>GSK - Quiz</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="${contextPath}/resources/css/gskStyle.css">

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
          <li><a class="navbar-brand" href="/" style="padding: 1px;"><img class="logoImg img-responsive" src="${contextPath}/resources/images/logo_gsk.png"></a></li>
          <li class="active"><a href="/">Home</a></li>
          <li><a href="/admin/uploadQuiz" class="hvrcenter">Create Quiz</a></li>
          <li><a href="/admin/quizResult" class="hvrcenter">Quiz Result</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li>
      <a href="#"><span class="glyphicon glyphicon-user"></span>Welcome ${pageContext.request.userPrincipal.name}</a>
       </li>
        <li>
      <a id="logoutLink" onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-log-out" ></span> Logout </a>
      </li>
        </ul>
      </div>
    </div>
  </nav>
 <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
   </c:if>

  <div class="container" style="padding:7%">
  <h2>Quiz Result</h2>
  <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr style="font-weight:bold">
        <td>#</td>
        <td>Full name</td>
        <td>Email Id</td>
        <td>Result (Out of 5)</td>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="quizResult" items="${quizResultList}" varStatus="loop">
    <tr>
        <td>${loop.index + 1}</td>
        <td>${quizResult.getUser().getFullName()}</td>
        <td>${quizResult.getUser().getEmail()}</td>
        <td>${quizResult.getResult()}</td>
     </tr>
    </c:forEach>
     
    </tbody>
  </table>
  </div>
</div> <!-- end container -->

  <script src="https://code.jquery.com/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
