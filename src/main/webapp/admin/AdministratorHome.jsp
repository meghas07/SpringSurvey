<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
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
	 var adminid = <%=adminid%>;

	 //create survey function
	 $("#create_survey").click(function() {
	  window.location = "/SurveyProjectMVC/admin/createSurvey";
	 });

	 //view all surveys function call
	 $("#view_all_surveys").click(function() {

	  window.location = "/SurveyProjectMVC/admin/viewAllSurvey";
	 });

	 //logout function call
	 $("#logout").click(function() {
	  $.ajax({
	   url: "../rest/user/logout",
	   success: function(response, status) {
	    alert(response);
	    window.location = "/SurveyProjectMVC/home";
	   }

	  });

	 });
	 //end of ajax call for logout

	}); //end of dom functon
</script>
</head>
<body>
<nav class="[ navbar navbar-fixed-top ][ navbar-bootsnipp animate ]"
		role="navigation">
		<div class="[ container ]">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="[ navbar-header ]">
				<button type="button" class="[ navbar-toggle ]"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="[ sr-only ]">Toggle navigation</span> <span
						class="[ icon-bar ]"></span> <span class="[ icon-bar ]"></span> <span
						class="[ icon-bar ]"></span>
				</button>
				<div class="[ animbrand ]">
					<a class="[ navbar-brand ][ animate ]" href="#">Survey</a>
				</div>
			</div>
				<div class="[ collapse navbar-collapse ]" id="bs-example-navbar-collapse-1">
				<ul class="[ nav navbar-nav navbar-right ]">
					<li><a class="animate" id="logout">Logout</a></li></ul></div>
			
			
			</div></nav>
			

	<div class="box">
		<div class="container">
			<h1>
				Welcome Administrator! </h1>
			<h3>Select an option from the below list</h3>
		</div>
		<div class="row"></div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
			<div class="box-part text-center">
				<i class="fa fa-instagram fa-3x" aria-hidden="true"></i>
				<div class="title">
					<h4>Create a new Survey</h4>
				</div>
				<div class="text">
					<div class="span1">
						<button id="create_survey" class="btn btn-primary">
							<i class="icon-pencil icon-white"></i> <span><strong>Create
									Survey</strong></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
			<div class="box-part text-center">
				<i class="fa fa-instagram fa-3x" aria-hidden="true"></i>
				<div class="title">
					<h4>View All Surveys</h4>
				</div>
				<div class="text">
					<div class="span1">
						<button id="view_all_surveys" class="btn btn-primary">
							<i class="icon-pencil icon-white"></i> <span><strong>View
									All Surveys</strong></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
			<div class="box-part text-center">
				<i class="fa fa-instagram fa-3x" aria-hidden="true"></i>
				<div class="title">
					<h4>View All Responses</h4>
				</div>
				<div class="text">
					<div class="span1">
						<button id="view_all_surveys" class="btn btn-primary">
							<i class="icon-pencil icon-white"></i> <span><strong>View
									Responses</strong></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		
		
	</div>

</body>
</html>