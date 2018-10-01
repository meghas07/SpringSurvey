<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int userid = (Integer) session.getAttribute("userid");
	int surveyId = Integer.parseInt(request.getParameter("surveyId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript">
	var admin_id =
<%=userid%>
	;
	var survey_id =
<%=surveyId%>
	;
</script>
<script type="text/javascript" src="../js/user_view_all_questions.js"></script>
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
			<h1>Survey Questions</h1>
			<table id="question_tbl">
				<tr>
					<th>Question Number</th>
					<th>Question Name</th>

				</tr>
			</table>
			<button id="back">Back</button>
			<button id="home">Home</button>
		</div>
	</div>
</body>

</html>