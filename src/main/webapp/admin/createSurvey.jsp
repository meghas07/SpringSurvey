<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int adminid = (Integer) session.getAttribute("userid");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Survey</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript">
$(document)
.ready(
 function() {
  var admin_id = <%=adminid%>;
  var surveyId = $("#survey_id").val();
  var surveyObj;
  var question;
  var i;
  var counter = 2;

  /* function 1 */
  $("#addButton")
   .click(
    function() {

     if (counter > 10) {
      alert("Maximum 10 questions allowed");
      return false;
     }

     var newTextBoxDiv = $(
       document
       .createElement('div'))
      .attr(
       "id",
       'TextBoxDiv' +
       counter);
     newTextBoxDiv
      .after()
      .html(
       '<label>Question #' +
       counter +
       ' : </label>' +
       '<textarea rows="2" cols="100" ' +
       counter + '" id="textbox' +
       counter + '" value="" placeholder = "Enter text here..." class="form-control textBoxClass"></textarea>');

     newTextBoxDiv
      .appendTo("#TextBoxesGroup");
     counter++;
    });
  /*  end of function 1*/

  /* function 2 */
  $("#removeButton").click(function() {
   if (counter == 1) {
    alert("No more textbox to remove");
    return false;
   }

   counter--;

   $("#TextBoxDiv" + counter).remove();

  });
  /*  end of function 2*/

  /* start of function for form details*/
  $("#create_survey_form")
   .submit(
    function(event) {
     event.preventDefault();

    
      alert($("#survey_id").val())
      if ($.isNumeric($("#survey_id").val())) {

       /* stat of creation of survey object */
       surveyObj = {
        "surveyId": $("#survey_id")
         .val(),
        "surveyName": $("#survey_name")
         .val(),
        "user": {
         "id": admin_id
        },

        "setOfQuestionsInSurvey": []
       }
       /*--------- end of survey Object */

       /* start of adding question array of questions  */
       for (i = 0; i < counter - 1; i++) {
        console.log(counter)
        question = {
         "questionDescription": $(
           '#textbox' +
           (i + 1))
          .val(),
         "survey": {
          "surveyId": $(
            "#survey_id")
           .val()
         }
        } /* end of individual  question object  */
        surveyObj.setOfQuestionsInSurvey
         .push(question); /* filling question object into array */
       }

       /* end of for loop for adding questions  */
       console.log(surveyObj);

       /*converting survey object to json format */
       var surveyObjAsJSON = JSON
        .stringify(surveyObj);

       $
        .ajax({
         url: "/SurveyProjectMVC/rest/admin/addSurvey",
         type: "POST",
         data: surveyObjAsJSON,
         contentType: "application/json",
         success: function(
          response,
          status) {
          if (response == "success") {
           window.location = "/SurveyProjectMVC/admin/Administratorhome";
          } else
           alert(response);

         } // end of success function
        }); /*   end of ajax call*/


      } else
       alert("Inavlid SurveyID or Name")

     




    }); //end of form submit function

  //logout function call
  $("#logout").click(function() {
   $.ajax({
    url: "../rest/user/logout",
    success: function(response, status) {
     alert(response);
     window.location = "/SurveyProjectMVC/home";
    }

   });

  }); //logout function end

 }); //end of document.getready function
</script>
</head>
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
				<div class="[ collapse navbar-collapse ]" id="bs-example-navbar-collapse-1">
				<ul class="[ nav navbar-nav navbar-right ]">
					<li><a class="animate" id="logout">Logout</a></li></ul></div>
			
			
			</div></nav>
	<section id="login">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="form-wrap">
					<h1>Create a new Survey</h1>
					<form id="create_survey_form">
						<div class="form-group">
							<label for="surveyid" class="sr-only">Enter survey Id:</label> <input
								type="text" name="userid" id="survey_id" class="form-control"
								placeholder="Survey ID">
						</div>
						<div class="form-group">
							<label for="survey_name" class="sr-only">Password</label> <input
								type="text" name="key" id="survey_name" class="form-control"
								placeholder="Survey Name">
						</div>
						<!-- <div class="form-group"> -->
							<div id='TextBoxesGroup'>
								<div id="TextBoxDiv1">
									<label>Question #1 : </label>
									<!-- <input type='text' id='textbox1'> -->
									<textarea rows="2" cols="100" id='textbox1'
										placeholder="Enter text here..." class="form-control">
</textarea>
							<!-- 	</div> -->
							</div>
						</div>
						<input type='button' value='Add Question' id='addButton'
							class="btn btn-custom btn-lg btn-block"> <input
							type='button' value='Remove Question' id='removeButton'
							class="btn btn-custom btn-lg btn-block">
						<!-- <input type='button' value='Get TextBox Value' id='getButtonValue'> -->
						<input type="submit" id="btn-login"
							class="btn btn-custom btn-lg btn-block" value="Submit">
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