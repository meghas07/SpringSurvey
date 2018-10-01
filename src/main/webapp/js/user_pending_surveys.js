/**
 * 
 */

$(document).ready(function(event) {


    $.ajax({
        url: "/SurveyProjectMVC/rest/user/viewSharedSurveysList?userId=" + user_id,
        type: "GET",
        contentType: "application/json",
        success: function(response, status) {
            for (survey of response) {
                var viewBtn = $('<a href="/SurveyProjectMVC/user/viewCompleteSurvey?surveyId=' + survey.surveyId + '" ><button>View Survey</button></a>');
                var respondBtn = $('<a href="/SurveyProjectMVC/user/respondToSurvey?surveyId=' + survey.surveyId + '" ><button>Respond</button></a>');


                var trChild = $('<tr class="child"></tr>');
                var idTd = $('<td>' + survey.surveyId + '</td>');
                var nameTd = $('<td>' + survey.surveyName + '</td>');
                var viewTd = $("<td></td>");
                var respondTd = $("<td></td>");
                respondTd.append(respondBtn);
                viewTd.append(viewBtn);
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


}); // end of dom functon
