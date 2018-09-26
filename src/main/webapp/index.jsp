<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="css/style.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function() { //anonymous function short for doucment ready or document load

	 $("#login-form")
	  .submit(
	   function(event) {

	    event.preventDefault();
	    if ($.isNumeric($("#id").val())) {
	     var userObj = {
	      "id": $("#id").val(),
	      "password": $("#pass").val()
	     }

	     var userObjAsJSON = JSON.stringify(userObj);
	     console.log(userObjAsJSON);

	     $
	      .ajax({
	       url: "rest/user/login",
	       type: "POST",
	       data: userObjAsJSON,
	       contentType: "application/json",
	       success: function(response, status) {
	        console.log(response, status);

	        if (response == "admin")
	         window.location = "/SurveyProjectMVC/admin/Administratorhome";
	        else if (response == "user") {
	         alert("welcome user");
	         window.location = "/SurveyProjectMVC/user/userHome";
	        } else {
	         alert("Invalid username password");
	         window.location = "/SurveyProjectMVC/home";
	        }

	       }
	      });

	    } else {

	     alert("Invalid username password");
	     window.location = "/SurveyProjectMVC/home";
	    }
	   });

	});

	function showPassword() {

	 var key_attr = $('#pass').attr('type');

	 if (key_attr != 'text') {

	  $('.checkbox').addClass('show');
	  $('#pass').attr('type', 'text');

	 } else {

	  $('.checkbox').removeClass('show');
	  $('#pass').attr('type', 'password');

	 }

	}
</script>
</head>
<body>


	<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="form-wrap">
					<h1>Log in</h1>
					<form id="login-form" autocomplete="off">
						<div class="form-group">
							<label for="userId" class="sr-only">User ID</label> <input
								type="text" name="userid" id="id" class="form-control"
								placeholder="User ID">
						</div>
						<div class="form-group">
							<label for="key" class="sr-only">Password</label> <input
								type="password" name="key" id="pass" class="form-control"
								placeholder="Password">
						</div>
						<div class="checkbox">
							<span class="character-checkbox" onclick="showPassword()"></span>
							<span class="label">Show password</span>
						</div>
						<input type="submit" id="btn-login"
							class="btn btn-custom btn-lg btn-block" value="Log in">
							<input type="reset" 
							class="btn  btn-custom btn-lg btn-block " value="Reset" style="background: #C00000;">
					</form>
					
					<hr>
				</div>
			</div>
			<!-- /.col-xs-12 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container --> </section>
</body>
</html>