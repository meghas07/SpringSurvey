<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int userid = (Integer) session.getAttribute("userid");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completed Surveys</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
$(document).ready(function(event) {

    var user_id = <%=userid%>;

    $.ajax({
        url: "/SurveyProjectMVC/rest/user/viewCompletedSurveys?userId=" + user_id,
        type: "GET",
        contentType: "application/json",
        success: function(response, status) {
            for (survey of response) {
                var viewResponseBtn = $('<a href="/SurveyProjectMVC/user/viewResponse?surveyId=' + survey.surveyId + '" ><button>View Response</button></a>');
              

                var trChild = $('<tr class="child"></tr>');
                var idTd = $('<td>' + survey.surveyId + '</td>');
                var nameTd = $('<td>' + survey.surveyName + '</td>');
                var viewTd = $("<td></td>");
                var respondTd = $("<td></td>");
                
                respondTd.append(viewResponseBtn);
                trChild.append(idTd);
                trChild.append(nameTd);
                trChild.append(viewTd);
                trChild.append(respondTd);

                $('#survey_tbl tbody').append(trChild);
            }
        }


    });

    /* search button */
    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#survey_tbl tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#home").click(function() {
        window.location = "/SurveyProjectMVC/user/userHome";
    });


}); //end of dom function
</script>
</head>
<body>
	<h1>
		hello
		<%=userid%></h1>
	<div class="container">
		<div class="row">
			<h2 class="text-center">All Surveys created:</h2>
		</div>
		<input id="myInput" type="text" placeholder="Search.."> <br>
		<br>
		<div class="row">
			<div class="col-md-12">
				<table id="survey_tbl" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Survey Number</th>
							<th>Survey Name</th>
							<th>View Response</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<button id="home">Home</button>
				</div></div></div>
</body>
</html>