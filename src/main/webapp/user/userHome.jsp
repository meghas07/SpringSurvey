<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int userid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function() {
		
		$("#view_surveys_shared").click(function() {
			window.location = "/SurveyProjectMVC/user/ViewSurveysShared";
		});
		
		$("#view_answered_surveys").click(function() { 
			window.location = "/SurveyProjectMVC/user/viewCompletedSurveys";
		});

	}); //end of dom functon
</script>

<title>Home</title>
</head>
<body>
	<h1>
		hello
		<%=userid%>
	</h1>
	<button id="view_surveys_shared" class="btn btn-primary">View
		Shared Surveys</button>
	<button id="view_answered_surveys" class="btn btn-primary">View
		Response for previous Surveys</button>
	<!-- <button id="view_all_surveys" class="btn btn-primary">
<button id="view_all_surveys" class="btn btn-primary">
<button id="view_all_surveys" class="btn btn-primary"> -->
</body>
</html>