
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>

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
<script>
	var admin_id =<%=adminid%>	;
</script>
<script type="text/javascript" src="../js/view_all_surveys.js"></script>

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