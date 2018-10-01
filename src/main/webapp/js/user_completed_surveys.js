/**
 * 
 */
$(document).ready(function(event) {


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
               
                viewTd.append(viewResponseBtn);
                
                trChild.append(idTd);
                trChild.append(nameTd);
                trChild.append(viewTd);
            

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


}); // end of dom function
