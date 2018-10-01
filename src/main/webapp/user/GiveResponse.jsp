<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int userid = (Integer) session.getAttribute("userid");
	int surveyId=Integer.parseInt(request.getParameter("surveyId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Respond</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
            var user_id = <%=userid%>
            var survey_id=<%=surveyId%>
      
    </script>
    <script type="text/javascript" src="../js/user_response.js"></script>
    
</head>

<body>
	
	<h1>Survey Questions</h1>
	<form id="response_form">
		<div id="question_body"></div>
		<input type="submit" />
	</form>
</body>

</html>