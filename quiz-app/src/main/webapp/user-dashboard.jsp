<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

<style>
	#quiz_form fieldset:not(:first-of-type) {
    	display: none;
  	}
</style>

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
 
 <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="background-color: #fe8b16;"></div>
  </div>
  <div class="alert alert-success hide"></div>

   	<div id="quiz_form">
 
  	<c:forEach items="${quesList}" var="question" varStatus="loop">
		 <fieldset class="quesSet">
    		<h3>Q ${loop.index + 1} :   ${question.text} </h3>

   		    	<input type="hidden" class="quesHidden" value="${question.quesId}" data-text="${question.text}"/>
	    <div class="btn-group" data-toggle="buttons">
	    	 <c:forEach items="${question.answers}" var="ans">
  					<label class="btn btn-default customRadio" style="border-radius: 20px; margin: 10px;">
    					<input type="radio" name="ans${loop.index}" class="ansCheck" value="${ans.ansId}" data-text="${ans.text}" autocomplete="off" >
    					<span class="ansText">${ans.text}</span>
  					</label>
   		    </c:forEach> 
   		</div>
   		    	    		 <br><br>
   		    	
    		 <c:if test="${loop.index gt 0 }">
    	  		<input type="button" name="previous" class="previous btn btn-basic" style="color:black" value="Previous" />
   			 </c:if>
   			 
   			 <c:choose>
  				<c:when test="${loop.index + 1 eq fn:length(quesList)}">
    				<input type="button" name="submit" id="submitQuiz" class="submit btn btn-success" value="Submit" />
  				</c:when>
  				<c:otherwise>
    				<input type="button" name="password" class="next btn btn-default" value="Next" />
  				</c:otherwise>
			</c:choose>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  		</fieldset>
	</c:forEach>
	</div>

 
</div>  <!-- end container -->

 <script src="https://code.jquery.com/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/app.js"></script>
<script>
$(document).ready(function() {
	$("#submitQuiz").on("click", function() {
		
		userAnswers = [];
		
		$( ".quesSet" ).each(function( index ) {
			userAnswers[index] = {};
			userAnswers[index].userid = ${userid};
			userAnswers[index].quesid = $(this).find('.quesHidden').val();
			var radioButtonName = "input[name=ans"+index+"]:checked";
			userAnswers[index].ansid = $(this).find(radioButtonName).val();
		});
		
		
		loadData(userAnswers);
	});
	
	function loadData(userAnswers) {
		
		var JSONObject = {"userAnswers": userAnswers};
		JSONObject = JSON.stringify(JSONObject);
		
		$.ajax({
			url : "<c:url value="/all/submitUserQuizJson"></c:url>",
			type : 'post',
			data : JSONObject,
		    headers: 
		    {
		        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		    },
			dataType : 'JSON',
			contentType : 'application/json',
			success : function(data) {
				if (data.responseCode == "FAILURE") {
					console.log("fail");
				} else {
					console.log("success");

				}
			}
		});
	}
});



</script>
</body>
</html>
