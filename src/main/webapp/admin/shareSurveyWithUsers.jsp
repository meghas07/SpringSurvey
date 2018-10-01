<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
	int surveyId = Integer.parseInt(request.getParameter("surveyId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Share Survey</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript">
		var admin_id =<%=adminid%>;
		var survey_id=<%=surveyId%>;
</script>
<script type="text/javascript" src="../js/share_survey.js"></script>
</head>
<body>
	<h1>Survey ID</h1>
	<form id="share_survey_form">
		<table id="tbody">
			<tr>
				<th>User ID:</th>
				<th>User Name:</th>
				<th>Share:</th>
			</tr>
		</table>
		<input type="submit" />
	</form>
</body>
</html>