
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Surveys</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script> -->

<script type="text/javascript">
	$(document).ready(function() {
		var admin_id =<%=adminid%>;
	
		 /* delete survey button */
		
		 function deleteSurvey(){
				var button_id= $(this).attr("id"); 			
				 console.log(button_id);			
				 $.ajax({
					 url:"/SurveyProjectMVC/rest/admin/deleteSurvey?surveyId="+survey.surveyId,
					 type : "GET",					
					success : function() {
							window.location="/SurveyProjectMVC/admin/viewAllSurvey";
						}
					 
				 });
				 
				 
			 }
	
		
		
		
		 //end of onclick for change status function
		
		 function changeStatus(){
				var button_id= $(this).attr("id");      
				
				 console.log(button_id);
				
				 $.ajax({
					 url:"/SurveyProjectMVC/rest/admin/changeSurveyStatus?surveyId="+button_id,
					
					 type : "GET",
					
					
					success : function(response, status) {
							console.log(response);
							if(response=="ACTIVE")
							$("#"+button_id).html(response).css("background-color", "#4CAF50");
							else
							$("#"+button_id).html(response).css("background-color", "#f44336");	
						}
					 
				 });
				 
				 
			 }
	
		 //for retrieving surveys from db
		 $.ajax({
			url : "/SurveyProjectMVC/rest/admin/viewAllSurveysCreated?adminId="+admin_id,
			type : "GET",
			contentType: "application/json",
			success : function(response, status) {
			
				for(survey of response){   
				 
					
						var viewBtn = $('<a href="/SurveyProjectMVC/admin/viewCompleteSurvey?surveyId=' + survey.surveyId +'" ><button>View Survey</button></a>');
						if( survey.status=="ACTIVE")
						var statusButton=$('<button class="survey_status" id='+survey.surveyId+' style="background-color: #4CAF50;"> '+ survey.status +'</button>').click(changeStatus);
						else
							var statusButton=$('<button class="survey_status" id='+survey.surveyId+' style="background-color: #f44336;"> '+ survey.status +'</button>').click(changeStatus);
						var trChild = $('<tr class="child"></tr>');
						var idTd = $('<td>'+survey.surveyId+'</td>');
						var nameTd = $('<td>'+survey.surveyName+'</td>');
						var statusTd = $('<td></td>').append(statusButton);
						var viewTd = $("<td></td>");
						var shareButton=$('<a href="/SurveyProjectMVC/admin/shareSurvey?surveyId=' + survey.surveyId +'"><button class="share_survey">Share Survey</button></a>');
						var shareTd = $('<td></td>').append(shareButton);
						var deleteTd= $('<td><button class="btn btn-danger btn-xs" id='+survey.surveyId+'><span class="glyphicon glyphicon-trash"></span></button></a></td>').click(deleteSurvey);
					 	   
						
						
						
						viewTd.append(viewBtn);
						
						trChild.append(idTd);
						trChild.append(nameTd);
						trChild.append(statusTd);
						trChild.append(viewTd);
						trChild.append(shareTd);
						
						trChild.append(deleteTd);
					
						$('#survey_tbl tbody').append(trChild);
				}
				
			}
		});
		
	//end of ajax for retireving surveys
	
	 $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#survey_tbl tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });

		 $("#home").click(function() {
				window.location = "/SurveyProjectMVC/admin/Administratorhome";
			});
	}); //end of dom
</script>
</head>
<body>
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
							<th>Survey Status</th>
							<th>View Survey</th>
							<th>Share Survey</th>
							<th>Delete Survey</th>
						</tr>
					</thead>
					<tbody>

					</tbody>



				</table>
				<button id="home">Home</button>
			</div>
		</div>
	</div>
</body>
</html>