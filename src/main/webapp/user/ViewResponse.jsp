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
<title>Response</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document)
			.ready(
					function(event) {

						var user_id =<%=userid%>;
						
						$.ajax({
									url : "/SurveyProjectMVC/rest/user/viewCompleteResponse?surveyId=<%=surveyId%>", 
									type : "GET",
									contentType : "application/json",
									success : function(response, status) {
										response.forEach(function(questionAndAnswer,index){
											console.log(index);
											var question=questionAndAnswer.question.questionDescription;
											var answer=questionAndAnswer.response.response;
											var questionText=$('<p>'+question+'</p><br/>');
											var responsetext=$('<p>'+answer+'</p><br/>');
											questionText.append(responsetext);
											 $('#question_answer').append(questionText);
										});

									}

								});

					}); //end of dom function
</script>
</head>
<body> 
	<h1>Hello</h1>
	<div id="question_answer"></div>
</body>
</html>