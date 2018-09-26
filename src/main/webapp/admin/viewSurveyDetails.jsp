<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            var admin_id = <%=adminid%>;
            var i = 1;
            $.ajax({
                url: "/SurveyProjectMVC/rest/admin/viewCompleteSurvey?surveyId=${surveyId}",
                type: "GET",
                contentType: "application/json",
                success: function(response, status) {
                    for (question of response) {
                        console.log(response);
                        var trChild = $('<tr class="child"></tr>');
                        var idTd = $('<td>' + i + '</td>');
                        var questionTd = $('<td>' + question.questionDescription + '</td>');

                        trChild.append(idTd);
                        trChild.append(questionTd);
                        $('#question_tbl tbody').append(trChild);
                        i++;

                    } //end of for

                }

            });
            //end of ajax for retrieving surveys	

            $("#back").click(function() {
                console.log("hello");
                window.location = "/SurveyProjectMVC/admin/viewAllSurvey";
            });

            $("#home").click(function() {
                window.location = "/SurveyProjectMVC/admin/Administratorhome";
            });


        }); //end of dom
    </script>
</head>

<body>
    <h1>Survey Questions</h1>
    <table id="question_tbl">
        <tr>
            <th>Question Number</th>
            <th>Question Name</th>

        </tr>
    </table>
    <button id="back">Back</button>
    <button id="home">Home</button>
</body>

</html>