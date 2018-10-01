/**
 * 
 */
/**
 * 
 */


  $(document).ready(function() {
          
            var i = 1;
           
            $.ajax({
                url: "/SurveyProjectMVC/rest/admin/viewCompleteSurvey?surveyId="+survey_id,
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

                    } // end of for

                }

            });
            // end of ajax for retrieving surveys

            $("#back").click(function() {
                console.log("hello");
                window.location = "/SurveyProjectMVC/user/ViewSurveysShared";
            });

            $("#home").click(function() {
                window.location = "/SurveyProjectMVC/user/userHome";
            });


        }); // end of dom
