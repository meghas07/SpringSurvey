<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int userid = (Integer) session.getAttribute("userid");
%>

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

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/user_dashboard.js"></script>

<title>Home</title>
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
		<div class="[ collapse navbar-collapse ]"
			id="bs-example-navbar-collapse-1">
			<ul class="[ nav navbar-nav navbar-right ]">
				<li><a class="animate" id="logout"
					href="/SurveyProjectMVC/user/logout">Logout</a></li>
			</ul>
		</div>


	</div>
</nav>
	<div class="box">
		<div class="container">
			
			<button id="view_surveys_shared" class="btn btn-primary">View
				Shared Surveys</button>
			<button id="view_answered_surveys" class="btn btn-primary">View
				Response for previous Surveys</button>
		</div>
	</div>
</body>
</html>